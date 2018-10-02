// Flatten List
// Given a list, each element in the list can be a list or integer. flatten it into a simply list with integers.

// Example
// Given [1,2,[1,2]], return [1,2,1,2].

// Given [4,[3,[2,[1]]]], return [4,3,2,1].

// Challenge
// Do it in non-recursive.

// Notice
// If the element in the given list is a list, it can contain list too.


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


public class Solution {

    // @param nestedList a list of NestedInteger
    // @return a list of integer
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> res = new ArrayList();
        dfs(res , nestedList);
        return res;
    }
    private void dfs(List<Integer> res,List<NestedInteger> nestedList){
        for(NestedInteger x : nestedList){
            if(x.isInteger()){
                res.add(x.getInteger());
            }
            if(x.getList() != null){
                dfs(res, x.getList());   
            }
            
        }
    }
}