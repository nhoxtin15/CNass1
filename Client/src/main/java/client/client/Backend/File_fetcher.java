package client.client.Backend;

import client.client.Exception_Handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class File_fetcher implements Runnable{
    String filename;

    public File_fetcher(String filename){
        this.filename = filename;
    }


    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(5666);
            System.out.println("Open Socket ready for fetching");
            Socket receiver = serverSocket.accept();
            System.out.println("Sucessfully connected to the sender");

            DataInputStream receiver_Stream = new DataInputStream(receiver.getInputStream());

            System.out.println("The file is: "+this.filename);

            int size = receiver_Stream.readInt();
            System.out.println("The size of the file: "+size);
            byte[] buffer = new byte[size];

            receiver_Stream.read(buffer,0,size);


            try(FileOutputStream file_Writer = new FileOutputStream(Backend_Client.fileStoringdir+"/" + this.filename)){
                //write to the file
                file_Writer.write(buffer,0,size);
                System.out.println("write file: "+Backend_Client.fileStoringdir+"/" + this.filename);
            }
            catch (Exception e){
                System.out.println("Cannot write file");
                new Exception_Handler().new Cannot_write_file().Display(true);
            }
            serverSocket.close();
        }
        catch (Exception cannot_fetch){
            System.out.println("Cannot fetch file");
            try{
                new Exception_Handler().new Cannot_fetch_file().Display(true);
            }
            catch(Exception e1){

            }

        }
    }
}
