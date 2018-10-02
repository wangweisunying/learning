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

    int size;
    double sum;
    Queue<Integer> q ;
    public MovingAverage(int size) {
        this.size = size;
        this.sum = 0;
        this.q = new LinkedList<Integer>();
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        
        q.offer(val);
        sum += val; 
        if(q.size() > size){
            sum -= q.poll();
        }
        
        return sum/q.size();
        
    }
}