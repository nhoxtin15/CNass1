package client.client.Backend;

public class timer implements Runnable{

    Thread thread_to_Terminate;
    int sec;

    String name;
    public timer(Thread thread_to_Terminate,int sec,String name){
        this.thread_to_Terminate = thread_to_Terminate;
        this.sec = sec;
        this.name = name;
    }
    @Override
    public void run() {
        boolean status = true;
        System.out.println("The timer for: "+name+" is running.");
        while(status){
            try{
                Thread.sleep(sec* 1000L);
                status = false;
            }
            catch (Exception e){

            }
        }

        this.thread_to_Terminate.interrupt();
    }
}
