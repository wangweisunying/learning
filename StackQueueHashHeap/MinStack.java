// Min Stack
// Implement a stack with min() function, which will return the smallest number in the stack.

// It should support push, pop and min operation all in O(1) cost.

// Example
// push(1)
// pop()   // return 1
// push(2)
// push(3)
// min()   // return 2
// push(1)
// min()   // return 1
// Notice
// min operation will never be called if there is no number in the stack.

public class MinStack {
    Stack stack;
    TreeMap<Integer , Integer> map;
    public MinStack() {
        stack = new Stack();
        map = new TreeMap();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int num) {
        stack.push(num);
        if(map.containsKey(num)){
            map.put(num , map.get(num) + 1);
        }
        else{
            map.put(num , 1);
        }
    }

    /*
     * @return: An integer
     */
    public int pop() {
        int tmp = stack.pop();
        if(map.get(tmp) == 1){
            map.remove(tmp);
        }
        else{
            map.put(tmp , map.get(tmp) - 1);
        }
        return tmp;
    }

    /*
     * @return: An integer
     */
    public int min() {
        return map.firstKey();
    }
}