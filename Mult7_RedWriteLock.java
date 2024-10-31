import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteCounter{
    private int count=0;
    //allow any no of thread to read the res if writeLock is unlocked. If writeLock is locked, does not allow any thread to lock the readlock or does not allow any thread to read the resource
    private final ReadWriteLock lock=new ReentrantReadWriteLock();

    private final Lock readLock=lock.readLock();

    private final Lock writeLock=lock.writeLock();

    public void increament(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" increamented");
            count++;
        } finally{
            writeLock.unlock();
        }
    }

    public int getCount(){
        readLock.lock();
        try{
            return count;
        } finally{
            readLock.unlock();
        }
    }
};

public class Mult7_RedWriteLock {
    public static void main(String[] args) {
        ReadWriteCounter obj=new ReadWriteCounter();
        Runnable writeTask=()->{
            for(int i=0; i<10; i++){
                obj.increament();
            }
        };

        Runnable readTask1=()->{
            for(int i=0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+" read value "+obj.getCount());
            }
        };
        Runnable readTask2=()->{
            for(int i=0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+" read value "+obj.getCount());
            }
        };

        Thread t1=new Thread(writeTask);
        Thread t2=new Thread(readTask1);
        Thread t3=new Thread(readTask2);

        t2.start();
        t3.start();
        t1.start();

        try {
            t1.join();
        t2.join();
        t3.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
