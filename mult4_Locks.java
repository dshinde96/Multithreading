
// lock.lock()-> will acquire the lock if its free else wait for it to become free and then acquire the lock
//lock.tryLock(time)-> will acquire lock if its free and return true else will wait for the metioned time and after that if lock is not available, will return false

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class bank {
    private int balance;

    public bank(int balance) {
        this.balance = balance;
    }
    private final Lock lock=new ReentrantLock();
    public void withdrawal(int amount) {
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){  //If any thread does not get unlocked lock, he will wait for 1000 sec to unlock the lock, if still not get, it will terminate
                try {
                    System.out.println(Thread.currentThread().getName() + " attempting to withDraw");
                    Thread.sleep(3000);  //current thread will wait for 3000 ms
                    balance -= amount;
                    System.out.println("Remaining balance " + balance);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();  //we should always call this in catch if exception occurs such that our monitoring code will gt to know that a thread is interupted
                }
                finally{
                    lock.unlock();  //now thread will unlock the lock, completed its execution and stops
                }
            }
            else{
                System.out.println(Thread.currentThread().getName()+" could not acquire lock after waiting for 1000ms");
            }
        } catch (Exception e) {
                // TODO: handle exception
        }
    }
    // public synchronized void withdrawal(int amount){
    // System.out.println(Thread.currentThread().getName()+" attempting to
    // withDraw");
    // try {
    // Thread.sleep(3000);
    // balance-=amount;
    // System.out.println("Remaining balance "+balance);
    // } catch (Exception e) {
    // // TODO: handle exception
    // }
    // }
};

class LocksDemo{
    private final Lock lock=new ReentrantLock();

    public void func(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" acquired lock");
            Thread.sleep(1000);
        } catch(Exception e){

        }
        finally{
            lock.unlock();
        }
    }
}

public class mult4_Locks {
    public static void main(String[] args) {
        LocksDemo obj=new LocksDemo();
        Thread t1=new Thread(()->{
            obj.func();
        });
        Thread t2=new Thread(()->{
            obj.func();
        });

        t1.start();
        t2.start();
        // bank b = new bank(1000);
        // bank b2 = new bank(500);
        // Runnable a1 = () -> {
        //     b.withdrawal(100);
        // };
        // Runnable a2 = () -> {
        //     b.withdrawal(100);
        // };

        // Thread t1 = new Thread(a1);
        // Thread t2 = new Thread(a2);

        // t1.start();
        // t2.start();
    }
}
