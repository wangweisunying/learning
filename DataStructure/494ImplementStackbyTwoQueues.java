
// Implement a stack by two queues. The queue is first in first out (FIFO). That means you can not directly pop the last element in a queue.

// Example
// push(1)
// pop()
// push(2)
// isEmpty() // return false
// top() // return 2
// pop()
// isEmpty() // return true


public class Stack {
    /*
     * @param x: An integer
     * @return: nothing
     */
    Queue<Integer> que1 = new LinkedList();
    Queue<Integer> que2 = new LinkedList();
    public void push(int x) {
        Queue<Integer> empty = que1;
        Queue<Integer> other = que2;
        if(!que1.isEmpty()){
            other = que1;
            empty = que2;
        }
        empty.offer(x);
        while(!other.isEmpty()){
            empty.offer(other.poll());
        }
    }

    /*
     * @return: nothing
     */
    public void pop() {
        if(que1.isEmpty()){
            que2.poll();
        }
        else{
            que1.poll();
        }
    }

    /*
     * @return: An integer
     */
    public int top() {
        if(que1.isEmpty()){
            return que2.peek();
        }
        else{
            return que1.peek();
        }
    }

    /*
     * @return: True if the stack is empty
     */
    public boolean isEmpty() {
        return que1.isEmpty() && que2.isEmpty();
    }
}











public class Stack {
    /*
     * @param x: An integer
     * @return: nothing
     */
    Queue<Integer> que1 = new LinkedList();
    Queue<Integer> que2 = new LinkedList();
    public void push(int x) {
        if(que1.isEmpty()){
            que1.offer(x);
            while(!que2.isEmpty()){
                que1.offer(que2.poll());
            }
        }
        else{
            que2.offer(x);
            while(!que1.isEmpty()){
                que2.offer(que1.poll());
            }
        }
    }
    /*
     * @return: nothing
     */
    public void pop() {
        if(!que1.isEmpty()){
            que1.poll();
        }
        else{
            que2.poll();
        }
    }

    /*
     * @return: An integer
     */
    public int top() {
        if(!que1.isEmpty()){
            return que1.peek();
        }
        else{
            return que2.peek();
        }
    }

    /*
     * @return: True if the stack is empty
     */
    public boolean isEmpty() {
        return que1.isEmpty() && que2.isEmpty();
    }
}