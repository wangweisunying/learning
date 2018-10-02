// Largest Rectangle in Histogram
// Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

// histogram

// Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

// histogram

// The largest rectangle is shown in the shaded area, which has area = 10 unit.

// Example
// Given height = [2,1,5,6,2,3],
// return 10.


public class Solution {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    class Unit{
        int index , height;
        Unit(int index , int height){
            this.index = index;
            this.height = height;
        }
    }
    
    public int largestRectangleArea(int[] height) {
        Stack<Unit> stack = new Stack();
        int max = 0;
        int i = 0;
        for(; i < height.length ; i ++){
            if(stack.isEmpty() || height[i] >= stack.peek().height){
                stack.push(new Unit(i , height[i]));
                continue;
            }
            while(!stack.isEmpty() && stack.peek().height > height[i]){
                int end = i ;
                int h = stack.pop().height;
                int start = -1;
                if(!stack.isEmpty()){
                    start = stack.peek().index;
                }
                max = Math.max(max ,(end - start - 1) * h);
            }
            stack.push(new Unit(i , height[i]));
        }
        while(!stack.isEmpty()){
            int end = i ;
            int h = stack.pop().height;
            int start = -1;
            if(!stack.isEmpty()){
                start = stack.peek().index;
            }
            max = Math.max(max ,(end - start - 1) * h);
        }
        return max;
    }
}