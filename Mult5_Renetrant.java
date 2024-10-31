import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Rentain_ex{
    ReentrantLock lock=new ReentrantLock();  //reentrantLock allows a thread to lock the same lock multiple times, avoids deadlock-> same thread can renter the same lock multiple times, but lock should be unlock the same number of time it is locked, so that new thread will not access until all locks are open
    public void OuterMethod(){
        try {
            if(lock.tryLock(3000,TimeUnit.MILLISECONDS)){
                System.out.println(Thread.currentThread().getName()+ " aquired lock for Outer Method");
                InnerMethod();
                Thread.sleep(5000);
            }
            else{
                System.out.println(Thread.currentThread().getName()+" canot acquire lock for outer method");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            if(lock.isHeldByCurrentThread()){
                System.out.println("Lock is held by "+ Thread.currentThread().getName() + " releasing the lock");
                lock.unlock();
            }
            else{
                System.out.println("lock is already unlocked");
            }
        }
    }
    public void InnerMethod(){
        try {
            // if(lock.tryLock(3000,TimeUnit.MILLISECONDS)){
            //     System.out.println(Thread.currentThread().getName()+ " aquired lock for Inner Method");
            // }
            // else{
            //     System.out.println(Thread.currentThread().getName()+" canot acquire lock for Inner method");
            // }
            System.out.println("Lock is unlocking in 2nd method");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            lock.unlock();
        }
    }
}
public class Mult5_Renetrant{
    public static void main(String args[]){
        Rentain_ex obj=new Rentain_ex();
        Thread t1=new Thread(()->{
            obj.OuterMethod();
        });
        Thread t2=new Thread(()->{
            obj.OuterMethod();
        });

        t1.start();
        t2.start();
    }
}