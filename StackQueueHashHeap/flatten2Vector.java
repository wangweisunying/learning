// Flatten 2D Vector
// Implement an iterator to flatten a 2d vector.

// Example
// Given 2d vector =

// [
//   [1,2],
//   [3],
//   [4,5,6]
// ]
// By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].


public class Vector2D implements Iterator<Integer> {
    Stack<List<Integer>> stack;
    Stack<Integer> stackJ; 
    public Vector2D(List<List<Integer>> vec2d) {
        stack = new Stack();
        stackJ = new Stack();
        for(int i = vec2d.size() - 1 ; i >= 0 ; i --){
            stack.push(vec2d.get(i));
        }
    }

    @Override
    public Integer next() {
        if(hasNext()){
            return stackJ.pop();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if(stack.isEmpty()&&stackJ.isEmpty()){
            return false;
        }
        if(!stackJ.isEmpty()){
            return true;
        }    
        while(!stack.isEmpty() && stack.peek().isEmpty()){
            stack.pop();
        }
        if(stack.isEmpty()){
            return false;
        }
        List<Integer> list = stack.pop();
        for(int i = list.size() - 1 ; i >= 0 ; i --){
            stackJ.push(list.get(i));
        }
        return true;
    }

    @Override
    public void remove() {}
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */