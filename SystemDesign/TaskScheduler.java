/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wei Wang
 */


// Task Schedular Design
public class TaskScheduler {
    public static void main(String[] args){
        DelayQueue<Task> que = new DelayQueue<>();
        new Thread(new TaskProducer(que) , "producer").start();
        new Thread(new TaskConsumer(que) , "comsumer").start();
    }
}

// java.util.concurrent.DelayQueue 来实现task scheduler
// 如果有分布式的需求可以使用Redis来实现消息的分发，如果对消息的可靠性有非常高的要求可以使用消息中间
// 加一个消息队列分发 task 任务. 注意添加 wal 来防止内存中priority Queue 丢失 或者 MQ通常有 反馈机制 ，如果没有收到反馈再次发送，

// Task design
class Task implements Delayed{
    private String name;
    private long startTime;
    
    Task(String name , long delay){
        this.name = name;
        this.startTime = delay + System.currentTimeMillis();
    }
    
    
    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.startTime - ((Task)o).startTime);
    }
    
    public String getName(){
        return this.name;
    }
}

//生产者很简单，就是一个死循环，不断地产生一些是时间随机的任务。
class TaskProducer implements Runnable{
    private final Random random  = new Random();
    private DelayQueue<Task> q;
    private String task = "task";
    private int num = 0;
    public TaskProducer(DelayQueue<Task> q){
        this.q = q;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                int delay = random.nextInt(10000);
                Task curTask = new Task(task + ++num, delay);
                System.out.println("put   " + curTask.getName());
                q.put(curTask);
                Thread.sleep(3000);
            }
            catch(Exception ex){
            }
        }
    }
}



class TaskConsumer implements Runnable{
    private DelayQueue<Task> q;
    
    public TaskConsumer(DelayQueue<Task> q){
        this.q = q;
    }
    
    @Override
    public void run() {
        while(true){
            try{
                Task task = q.take();
                System.out.println("Take   " + task.getName());
            
            } catch (InterruptedException ex) {
                Logger.getLogger(TaskConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
    }
    


}




// Task Schedular Design
public class TaskScheduler {
    public static void main(String[] args){
        DelayQueue<Task> que = new DelayQueue();
        ExecutorService producerPool  = Executors.newFixedThreadPool(5);
        for(int i = 0 ; i < 5 ; i++){
            producerPool.execute(new Producer(que));
        }
        ExecutorService consumerPool  = Executors.newFixedThreadPool(5);
        for(int i = 0 ; i < 5 ; i++){
            consumerPool.execute(new Consumer(que));
        }
        
    }
}

// java.util.concurrent.DelayQueue 来实现task scheduler
// 如果有分布式的需求可以使用Redis来实现消息的分发，如果对消息的可靠性有非常高的要求可以使用消息中间
// 加一个消息队列分发 task 任务. 注意添加 wal 来防止内存中priority Queue 丢失 或者 MQ通常有 反馈机制 ，如果没有收到反馈再次发送，

    
    

class Task implements Delayed {
    private String name;
    private long startTime;
    
    public Task(String name ,long delayTime){
        this.name = name;
        this.startTime = System.currentTimeMillis() + delayTime;
    }
    
    public String getName(){
        return this.name;
    }
    
    
    @Override
    public long getDelay(TimeUnit unit){
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public int compareTo(Delayed o){
        return (int)(this.startTime - ((Task)o).startTime);
    }
    
}



















class Producer implements Runnable{
    Random rand = new Random();
    String name ;
    DelayQueue<Task> que;
    public Producer(DelayQueue que){
        this.que = que;
        this.name = "producer";
    }
    
    @Override
    public void run(){
        while(true){
            try {
                int delay = rand.nextInt(10000);
                Task curTask = new Task("task-" + delay, delay);
                que.put(curTask);
                System.out.println("put task in to the delayQueue  " + curTask.getName());
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}



class Consumer implements Runnable{
    String name = "consumer";
    DelayQueue<Task> que;
    public Consumer(DelayQueue que){
        this.que = que;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                Task curTask = que.take();
                System.out.println("Take    " + curTask.getName());
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        
        
        
        }
    
    
    }
}
