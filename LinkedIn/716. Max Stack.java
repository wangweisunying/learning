// 716. Max Stack
// DescriptionHintsSubmissionsDiscussSolution
// Design a max stack that supports push, pop, top, peekMax and popMax.

// push(x) -- Push element x onto stack.
// pop() -- Remove the element on top of the stack and return it.
// top() -- Get the element on the top.
// peekMax() -- Retrieve the maximum element in the stack.
// popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, 
// only remove the top-most one.
// Example 1:
// MaxStack stack = new MaxStack();
// stack.push(5); 
// stack.push(1);
// stack.push(5);
// stack.top(); -> 5
// stack.popMax(); -> 5
// stack.top(); -> 1
// stack.peekMax(); -> 5
// stack.pop(); -> 1
// stack.top(); -> 5
// Note:
// -1e7 <= x <= 1e7
// Number of operations won't exceed 10000.
// The last four operations won't be called when stack is empty.

// For peekMax, we remember the largest value we've seen on the side. For example if we add [2, 1, 5, 3, 9], 
// we'll remember [2, 2, 5, 5, 9]. This works seamlessly with pop operations, and also it's easy to compute:
//  it's just the maximum of the element we are adding and the previous maximum.

// For popMax, we know what the current maximum (peekMax) is. We can pop until we find that maximum, then push the popped elements back on the stack.
// ["MaxStack","push","popMax","push","push","push","pop","peekMax","push","pop","pop","push","peekMax","peekMax","push","peekMax","push","peekMax"]
// [[],[-2],[],[-45],[-82],[29],[],[],[40],[],[],[66],[],[],[-61],[],[98],[]]
// Output
// [null,null,-2,null,null,null,29,29,null,40,-82,null,66,66,null,66,null,98]
// Expected
// [null,null,-2,null,null,null,29,-45,null,40,-82,null,66,66,null,66,null,98]

// For peekMax, we remember the largest value we've seen on the side. For example if we add [2, 1, 5, 3, 9], 
// we'll remember [2, 2, 5, 5, 9].
class MaxStack {

    /** initialize your data structure here. */
    Stack<Integer> stack;
    TreeMap<Integer, Integer> map;
    public MaxStack() {
        stack = new Stack();
        map = new TreeMap<>();
    }
    
    public void push(int x) {
        stack.push(x);
        map.put(x , map.getOrDefault(x, 0) + 1);
    }
    
    public int pop() {
        int res = stack.pop();
        map.put(res , map.get(res) - 1);
        if(map.get(res) == 0) map.remove(res);
        return res;
        
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return map.lastKey();
    }
    
    public int popMax() {
        int target = map.lastKey();
        map.put(target , map.get(target) - 1);
        if(map.get(target) == 0) map.remove(target);
    
        Stack<Integer> tmp = new Stack();
        while(!stack.isEmpty() && stack.peek() != target){
            tmp.push(stack.pop());
        }
        stack.pop();
        while(!tmp.isEmpty()){
            stack.push(tmp.pop());
        }
        return target;
    }
}


/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

class MaxStack {

    /** initialize your data structure here. */

    Stack<Integer> stack;
    Stack<Integer> maxStack;
    public MaxStack() {
        stack = new Stack();
        maxStack = new Stack();
    }
    
    public void push(int x) {
        stack.push(x);
        if(maxStack.isEmpty() || x >= maxStack.peek()){
            maxStack.push(x);
        }
        else{
            maxStack.push(maxStack.peek());
        }
    }
    public int pop() {
        maxStack.pop();  
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int res = maxStack.peek();
        Stack<Integer> tmp = new Stack();
        while(stack.peek() != res){
            tmp.push(stack.pop());
            maxStack.pop();
        }
        stack.pop();
        maxStack.pop();
        while(!tmp.isEmpty()){
            push(tmp.pop());
        }
        return res; 
    }
}


/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */