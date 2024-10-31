import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class mult5 {
    public static long factorial(int n){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int a=0,b=1;
        int i=2;
        while(i<=n){
            int c=a+b;
            a=b;
            b=c;
            i++;
        }
        return b;
    }
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();

        //taking long, let introduce multithreading, one thread responsible for each i
        //here each thread will individually perform his task, parallaly
        //here , thread bhi hum hi bana rahe and bussiness logic bhi ham, excecutors-> handle threads, will have to manage bussiness logic
        // Thread[] t=new Thread[10];
        // for(int i=1; i<10; i++){
        //     int finali=i;
        //     t[i-1]=new Thread(
        //         ()->{
        //             long result=factorial(finali);
        //             System.out.println(result);
        //         }
        //     );
        //     t[i-1].start();
        // }
        // for(int i=0; i<9; i++){
        //     try {
        //         t[i].join();
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }
        // for(int i=1; i<10; i++){
        //     System.out.println(factorial(i));
        // }


        //Will create thread pool using executors
        ExecutorService executor=Executors.newFixedThreadPool(2);  //we are creating a thread pool of 9 threads  //jitne threads kum, kaam utna slow
        for(int i=1; i<10; i++){
            int finali=i;
            executor.submit(()->{
                long result=factorial(finali);
                System.out.println(result);
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(100, TimeUnit.SECONDS);  //will wait for executor to complete its task in 100 sec, else throw exception
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        System.out.println("Total time "+ (endTime-startTime));
    }

}
