// 155. Min Stack
// DescriptionHintsSubmissionsDiscussSolution
// Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

// push(x) -- Push element x onto stack.
// pop() -- Removes the element on top of the stack.
// top() -- Get the top element.
// getMin() -- Retrieve the minimum element in the stack.
// Example:
// MinStack minStack = new MinStack();
// minStack.push(-2);
// minStack.push(0);
// minStack.push(-3);
// minStack.getMin();   --> Returns -3.
// minStack.pop();
// minStack.top();      --> Returns 0.
// minStack.getMin();   --> Returns -2.

class MinStack {

    /** initialize your data structure here. */
    
    
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public MinStack() {
        stack = new Stack();
        minStack = new Stack();
    }
    
    public void push(int x) {
        stack.push(x);
        minStack.push(x > minStack.peek() ? minStack.peek() : x);
    }
    
    public void pop() {
       stack.pop();
       minStack.pop();
        
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}






class MinStack {

    /** initialize your data structure here. */
    
    
    Stack<Integer> stack;
    Queue<Integer> que;
    public MinStack() {
        stack = new Stack();
        que = new PriorityQueue();
    }
    
    public void push(int x) {
        stack.push(x);
        que.offer(x);
    }
    
    public void pop() {
        que.remove(stack.pop());
        
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return que.peek();
    }
}