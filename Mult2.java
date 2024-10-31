
class Mythread implements Runnable{
    public void run(){
        System.out.println("Thread is Running...");
        for(int i=0; i<10; i++){
            System.out.println(i);
            try{
                Thread.sleep(1000);  //sleep the thread for som time
            }
            catch(Exception e){

            }
        }
    }
}

public class Mult2 {
    public static void main(String args[]) throws InterruptedException{
        Runnable a=new Mythread();

        Thread t1=new Thread(a);

        t1.start();
        t1.setDaemon(true);  //will set t1 as daemon thread
        // t1.join();
        for(int i=0; i<5; i++){
            System.out.println("main");
            // try{
            //     Thread.sleep(1500);  //sleep the thread for som time
            // }
            // catch(Exception e){

            // }
        }
        // t1.join();
    }
}
