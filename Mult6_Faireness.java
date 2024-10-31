import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class UnfaireRes {
    Lock lock = new ReentrantLock();

    public void accessRes() {
        try {
            if (lock.tryLock(4000, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " aquired the lock");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                } finally {
                    System.out.println(Thread.currentThread().getName() + " releasing the lock");
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " cannot aquired the lock");
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
};

class FaireRes {
    Lock lock = new ReentrantLock(true); // will mark fairness as true for this lock

    public void accessRes() {
        try {
            if (lock.tryLock(4000, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " aquired the lock");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                } finally {
                    System.out.println(Thread.currentThread().getName() + " releasing the lock");
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " cannot aquired the lock");
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
};

public class Mult6_Faireness {
    public static void main(String args[]) {
        try {
            UnfaireRes res1 = new UnfaireRes();
            Runnable task = () -> {
                res1.accessRes();
            };

            Thread t1 = new Thread(task);
            Thread t2 = new Thread(task);
            Thread t3 = new Thread(task);

            System.out.println("Unfair Resources\n\n");
            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();
            // Fair res
            FaireRes res2 = new FaireRes();
            Runnable task1 = () -> {
                res2.accessRes();
            };

            Thread t4 = new Thread(task1);
            Thread t5 = new Thread(task1);
            Thread t6 = new Thread(task1);

            System.out.println("Fair Resources\n\n"); // threads will access lock in the order they are initiated
            t4.start();
            t5.start();
            t6.start();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
