import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Mult9_ThreadPool {

    public static int facto(int n) {
        int ans = 1;
        for (int i = 2; i <= n; i++) {
            ans *= i;
        }
        try {
            Thread.currentThread().sleep(700);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return ans;
    }

    public static void main(String args[]) {
        try {
            long startTime = System.currentTimeMillis();

            //complicated to manage all the threads
            // Thread threads[]=new Thread[10];
            // for (int i = 1; i < 10; i++) {
            //     int finalI = i;
            //     threads[i-1] = new Thread(() -> {
            //         int result = facto(finalI);
            //         System.out.println(result);
            //     });
            //     threads[i-1].start();
            // }

            // //wait for all threads to finish
            // for(Thread thread:threads){
            //     try {
            //         thread.join();
            //     } catch (Exception e) {
            //         // TODO: handle exception
            //     }
            // }


            //Executors framework for threads management
            // ExecutorService executor=Executors.newFixedThreadPool(10); //created a threadpool of 9 threads, executor will use these 9 threads to perform the task, we need to implement bussiness logic only, rest is taken care by executor
            // //All resource management will be takencare by executor, we just need to implement our bussiness logic
            // for (int i = 1; i < 10; i++) {
            //     int finalI = i;
            //     //when returning the result, the task must be of type callable
            //     Future<Integer> future= executor.submit(() -> {
            //         int result = facto(finalI);
            //         // System.out.println(result);
            //         return result;
            //     });  //pass the runable task to executor. It will use the threads to perform the tasks effectivly
            //     System.out.println(future.get()); //will wait for the return from executor
            // }

            // executor.shutdown(); //now the executor is shutdown, we cannot submit the tasks to executor further more.
            // try {
            //     executor.awaitTermination(100, TimeUnit.SECONDS);  //wait for 100 secs until all initialised tasks is completed. If in 100 sec, not all the task re completed, will throw the exep and main thread move on to next task
            // } catch (Exception e) {
            //     // TODO: handle exception
            // }
            // System.out.println("Total time take: " + (System.currentTimeMillis() - startTime));



            //invokeAll()-> take list of callbale objects as input and invokes all the tasks
            ExecutorService executor=Executors.newFixedThreadPool(2);
            Callable<Integer> t1=()->{
                Thread.sleep(1000);
                System.out.println("Task 1");
                return 1;
            };
            Callable<Integer> t2=()->{
                Thread.sleep(1000);
                System.out.println("Task 2");
                return 2;
            };
            Callable<Integer> t3=()->{
                Thread.sleep(1000);
                System.out.println("Task 3");
                return 3;
            };

            List<Callable<Integer>>tasks=Arrays.asList(t1,t2,t3);
            // List<Future<Integer>> futures=executor.invokeAll(tasks,2,TimeUnit.SECONDS);  //Invoke all the tasks withing time frame of 2 seconds
            // for(Future<Integer>f:futures){
            //     System.out.println(f.get());
            // }

            //invokeAny()->Return the result of the task which is completed first
            Integer res=executor.invokeAny(tasks,2,TimeUnit.SECONDS);
            System.out.println(res);
            executor.shutdown();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
