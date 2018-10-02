// ImplementQueuebyTwoStacks
// As the title described, you should only use two stacks to implement a queue's actions.

// The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

// Both pop and top methods should return the value of first element.

// Example
// push(1)
// pop()     // return 1
// push(2)
// push(3)
// top()     // return 2
// pop()     // return 2
// Challenge
// implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE
public class MyQueue {
    Stack<Integer> empty;
    Stack<Integer> other;
    
    public MyQueue() {
        empty = new Stack();
        other = new Stack();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        // if(!empty.isEmpty()){
        //     Stack<Integer> tmp = other;
        //     other = empty;
        //     empty = tmp;
        // }
        while(!other.isEmpty()){
            empty.push(other.pop());
        }
        empty.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        while(!empty.isEmpty()){
            other.push(empty.pop());    
        }
        return other.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        while(!empty.isEmpty()){
            other.push(empty.pop());    
        }
        return other.peek();
    }
}


























public class MyQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public MyQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        if(stack1.isEmpty()){
            
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
            stack2.push(element);
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            
            return;
        }
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            stack1.push(element);
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
            return;
        }
    }

    /*
     * @return: An integer
     */
    public int pop() {
        if(stack1.isEmpty()){
            return stack2.pop();
        }
        return stack1.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        if(stack1.isEmpty()){
            return stack2.peek();
        }
        return stack1.peek();
    }
}