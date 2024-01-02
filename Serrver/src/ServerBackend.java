import java.net.ServerSocket;
import java.util.Vector;

public class ServerBackend {
    public static Vector <String> List_of_IP;

    public static Vector <Vector<String>> List_of_file;
    public static  ServerSocket socket_private;
    public static ServerSocket  socket_public;

    static int size;

    public static Vector<ServerProcessor> List_of_processor;

    static public final  Object Socket_lock = new Object();



    static {
        List_of_IP = new Vector<>();
        List_of_file = new Vector<>();

        size = 0;
        List_of_processor = new Vector<>();


        try{
            socket_private = new ServerSocket(4333);
            socket_public  = new ServerSocket(4334);
        }
        catch (Exception e){
            System.out.println("loi serversocket o Server");
            e.printStackTrace();
        }

    }




}
