package Server;


import java.net.*;
import java.util.Scanner;
import java.util.Vector;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
public class Server {


    public static  String getLocalIpAddress() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                    return inetAddress.getHostAddress();
                }
            }
        }
        return null;
    }
    public static void main(String[] args) throws Exception{

        System.out.println(getLocalIpAddress());

        //promt and input number of client allow

        System.out.println("Please input the amount of Client:");

        Scanner a = new Scanner(System.in);

        int client_num = a.nextInt();

        Vector <Thread> threads = new Vector<>();

        for(int i=0;i<client_num;i++){
            //ServerBackend.List_of_processor.add(new ServerProcessor(i));
            ServerProcessor newProcessor = new ServerProcessor(i);
            threads.add(new Thread (newProcessor)) ;
            threads.elementAt(i).start();
        }

        for(int i=0;i<client_num;i++){
            threads.elementAt(i).join();
        }

    }
}
