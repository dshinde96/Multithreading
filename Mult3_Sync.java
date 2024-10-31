class Counter{
    private int count=0;

    public synchronized void increament(){  //to add synchronisation, we need to add synchronised keyword
        count++;
    }

    public int getCount(){
        return count;
    }
}

class Mythread1 extends Thread{
    private Counter counter;
    public Mythread1 (Counter counter){
        this.counter=counter;
    }

    public void run(){
        for(int i=0; i<10000; i++)
        counter.increament();
    }
}
public class Mult3_Sync {
    public static void main(String[] args) {
        //sharable object bet two threads
        Counter counter=new Counter();
        Thread t1=new Mythread1(counter);
        Thread t2=new Mythread1(counter);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println(counter.getCount());  //s both threads increamenting counter by 1000 times, lst value should be 2000, but bcz counter is a shared resource, both increament at the same time, and will not considers, bcz both thread increament 4 to 5, will miss one addition, means both thread read same value at same time and increament it

        //synchronisation-> Only one thread should utilise the resourse at a time and other should wait for one to finish its execution
    }
}
