package  Server;
import javax.sound.midi.Soundbank;
import javax.xml.catalog.Catalog;
import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class ServerProcessor implements Runnable {
    int ID;
    private Socket socket_private;

    private DataInputStream private_input;
    private DataOutputStream private_output;


    public Socket socket_public; //for sending.

    public DataInputStream public_input;

    public DataOutputStream public_output;

    int count = 0;
    public ServerProcessor(int ID){
        this.ID  = ID;

    }


    @Override
    public void run() {
        //open socket

            try {
                synchronized (ServerBackend.Socket_lock) {
                    System.out.println("Start listening at thread "+this.ID);
                    ServerSocket socket1 = ServerBackend.socket_private;
                    ServerSocket socket2 = ServerBackend.socket_public;
                    this.socket_private = socket1.accept();
                    try{
                        this.private_output = new DataOutputStream( socket_private.getOutputStream());
                        this.private_input = new DataInputStream(socket_private.getInputStream());

                        private_output.writeUTF("doi");
                    }
                    catch (Exception doi){

                    }



                    this.socket_public = socket2.accept();
                    this.public_input = new DataInputStream(socket_public.getInputStream());
                    this.public_output = new DataOutputStream(socket_public.getOutputStream());




                    //extract the IP
                    String ip=(((InetSocketAddress) socket_private.getRemoteSocketAddress()).getAddress()).toString().replace("/","");
                    ServerBackend.List_of_IP.add(ip);
                    //add a new list
                    //ServerBackend.List_of_file.add(new Vector<>());

                    this.ID = ServerBackend.size;
                    System.out.println("Connection sucessfully, the new ID is: "+this.ID);
                    ServerBackend.List_of_processor.add(this);
                    ServerBackend.size++;





                }
            }
            catch (Exception error){
                error.printStackTrace();
            }







            //after open socket
            //wait for message
            int count = 0;
            while(count==0){
                //read
                try{

                    String message = private_input.readUTF();

                    if(message.equals("update")){
                        //check update
                        //read the size
                        //count
                        Vector<String> List_of_file_tosend = new Vector<>();
                        int File_count = 0;

                        for(int Socket_iter = 0 ; Socket_iter < ServerBackend.List_of_file.size();Socket_iter++){
                            //if(Socket_iter == this.ID) continue;
                            for(int File_iter =0 ; File_iter < ServerBackend.List_of_file.elementAt(Socket_iter).size();File_iter++){
                                List_of_file_tosend.add(ServerBackend.List_of_file.elementAt(Socket_iter).elementAt(File_iter));
                                File_count++;
                            }
                        }

                        private_output.writeInt(File_count);
                        for(int i=0;i<File_count;i++){
                            private_output.writeUTF(List_of_file_tosend.elementAt(i));

                        }





                    }
                    else if(message.equals("publish")){

                        int size = this.private_input.readInt();

                        Vector<String> List_of_file_temp = new Vector<>();

                        for(int i=0;i<size;i++){
                            List_of_file_temp.add(private_input.readUTF());
                        }

                        ServerBackend.List_of_file.add(List_of_file_temp);
                    }
                    else if(message.equals("send")){
                        //read file name
                        String fileName = this.private_input.readUTF();

                        System.out.println("The client "+ServerBackend.List_of_IP.elementAt(this.ID)+"want the file: "+ fileName);



                        //find the file
                        int Socket_iter = 0;
                        boolean found = false;

                        for(;Socket_iter<ServerBackend.List_of_file.size();Socket_iter++){

                            if(ServerBackend.List_of_file.elementAt(Socket_iter).contains(fileName) ){


                                found=true;
                                break;
                            }
                        }

                        System.out.println("the file is found at Client: " + ServerBackend.List_of_IP.elementAt(Socket_iter));


                        //Socket iter is the file
                        if(found){
                            try{
                                System.out.println(ServerBackend.List_of_processor.elementAt(Socket_iter).ID);
                                ServerBackend.List_of_processor.elementAt(Socket_iter).fetch(fileName,ServerBackend.List_of_IP.elementAt(this.ID));
                            }
                            catch (Exception loikhifetch){
                                try{
                                    this.private_output.writeUTF("send error");
                                }
                                catch (Exception e){

                                }
                                loikhifetch.printStackTrace();
                            }
                        }
                        else{
                            this.private_output.writeUTF("send error");
                        }

                    }
                }
                catch (Exception eq){

                }



            }

    }

    public synchronized void fetch(String filename,String receiver) throws Exception{
        try{

            DataOutputStream sender = public_output;
            //send the
            public_output.writeUTF("send");
            sender.writeUTF(filename);
            sender.writeUTF(receiver);
        }
        catch (Exception e){
            throw  e;
        }
    }




}
