
//Thread communicaion- wait(), notify(), notifyAll
class SharedRes{
    private int data;
    private boolean hasdata;
    public SharedRes(){
        hasdata=false;
        data=0;
    }
    public synchronized void produce(int value){
        while(hasdata){
            try{
                wait();
            } catch(Exception e){
                Thread.currentThread().interrupt();
            }
        }
        data=value;
        hasdata=true;
        System.out.println("Produced: "+value);
        notify();  //notify other threads that the data is produced
    }

    public synchronized int consume(){
        while(!hasdata){
            try{
                wait();
            } catch(Exception e){
                Thread.currentThread().interrupt();
            }
        }
        hasdata=false;
        System.out.println("Consumed: "+data);
        notify();
        return data;

    }
}

class Producer implements Runnable{
    private SharedRes res;

    public Producer(SharedRes res){
        this.res=res;
    }

    @Override
    public void run() {
        for(int i=10; i<19; i++){
            res.produce(i);
        }
    }
}

class Consumer implements Runnable{
    private SharedRes res;

    public Consumer(SharedRes res){
        this.res=res;
    }

    @Override
    public void run() {
        for(int i=0; i<9; i++){
            int v=res.consume();
        }
    }
}
public class Mult8_ThreadCommunication {
    public static void main(String args[]){
        SharedRes res=new SharedRes();

        Thread t1=new Thread(new Producer(res));
        Thread t2=new Thread(new Consumer(res));

        t1.start();
        t2.start();
    }
}
