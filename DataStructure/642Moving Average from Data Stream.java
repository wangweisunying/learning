
// 642. Moving Average from Data Stream
// Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

// Example
// MovingAverage m = new MovingAverage(3);
// m.next(1) = 1 // return 1.00000
// m.next(10) = (1 + 10) / 2 // return 5.50000
// m.next(3) = (1 + 10 + 3) / 3 // return 4.66667
// m.next(5) = (10 + 3 + 5) / 3 // return 6.00000


public class MovingAverage {
    /*
    * @param size: An integer
    
    */
    
    Queue<Integer> que;
    int max;
    double sum;
    public MovingAverage(int size) {
        que = new LinkedList();
        max = size;
        sum = 0;
        // do intialization if necessary
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        if(que.size() == max){
            sum -= que.poll();
            sum += val;
            que.offer(val);
            return sum / max;
        }
        else{
            sum += val;
            que.offer(val);
            return sum / que.size();
        }
        
    }
}