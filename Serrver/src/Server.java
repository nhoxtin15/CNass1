

import java.io.DataInputStream;
import java.net.*;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    public static void main(String[] args) throws Exception{
        System.out.println(InetAddress.getLocalHost().getHostAddress());


        //promt and input number of client allow

        System.out.println("Please input the amount of Client:");

        Scanner a = new Scanner(System.in);

        int client_num = a.nextInt();

        Vector <Thread> threads = new Vector<>();

        for(int i=0;i<client_num;i++){
            ServerBackend.List_of_processor.add(new ServerProcessor(i));
            threads.add(new Thread (ServerBackend.List_of_processor.elementAt(i))) ;
            threads.elementAt(i).start();
        }

        for(int i=0;i<client_num;i++){
            threads.elementAt(i).join();
        }







    }
}
