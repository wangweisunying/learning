// Flatten Nested List Iterator
// Given a nested list of integers, implement an iterator to flatten it.

// Each element is either an integer, or a list -- whose elements may also be integers or other lists.

// Example
// Given the list [[1,1],2,[1,1]], By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

// Given the list [1,[4,[6]]], By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

// Notice
// You don't need to implement the remove method.
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
import java.util.Iterator;
//从后往前入栈！！
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack();
        for(int i = nestedList.size() - 1 ; i >=0 ; i --){
            stack.push(nestedList.get(i));
        }
        
    }

    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        if(hasNext()){
            return stack.pop().getInteger();
        }
        return null;
        
        // Write your code here
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        if(stack.isEmpty()){
            return false;
        }
        while(!stack.isEmpty() &&!stack.peek().isInteger()){
            List<NestedInteger> list = stack.pop().getList();
            if(list != null){
                for(int i = list.size() - 1 ; i >=0 ; i --){
                    stack.push(list.get(i));
                }
            }

        }
        return !stack.isEmpty();
    }

    @Override
    public void remove() {}
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v.add(i.next());
 */