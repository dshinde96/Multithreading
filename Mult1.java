class World extends Thread{
    public void run(){
        for(int i=0; i<100; i++)
        // System.out.println("World");
        System.out.println(Thread.currentThread().getName());
    }
}

class World1 implements Runnable{
    public void run(){
        for(int i=0; i<100; i++)
        // System.out.println("World");
        System.out.println(Thread.currentThread().getName());
    }
}
public class Mult1{
    public static void main(String args[]) throws InterruptedException{
        // Thread t1=new World();
        Runnable w=new World1();

        Thread t1=new Thread(w);
        t1.start();
        // t1.join();
        for(int i=0; i<100; i++){
            // System.out.println("Hello");
            System.out.println(Thread.currentThread().getName());
        }
        System.out.println(Thread.currentThread().getName());  //when a program start, one thread begins running immedeatily called main thread whihc is responsible for running main method
    }
}