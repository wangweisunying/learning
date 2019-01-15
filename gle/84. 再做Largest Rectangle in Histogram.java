// 84. Largest Rectangle in Histogram
// DescriptionHintsSubmissionsDiscussSolution
// Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
//  find the area of largest rectangle in the histogram.

 


// Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 


// The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

// public class Solution {
//     public int largestRectangleArea(int[] height) {
//         Stack<Integer> stack = new Stack();
//         int max = 0;
//         stack.push(-1);
//         int i = 0;
//         for(; i < height.length ; i++){
//             int checkHeight = 0;
//             if(stack.peek() != -1) checkHeight = height[stack.peek()];
//             if(checkHeight <= height[i]){
//                 stack.push(i);
//             }
//             else{
//                 while(stack.peek() != -1 && height[stack.peek()] > height[i]){
//                     max = Math.max( (i - stack.peek()) * height[stack.pop()] , max);
//                 }
//             }
//         }
//         int min = Integer.MAX_VALUE;
//         while(stack.peek() != -1 && !stack.isEmpty()){
//             min = Math.min(min , height[stack.peek()]);
//             max = Math.max((i - stack.peek()) * height[stack.pop()] , max);
//         }

//         return Math.max( i * min , max);
//     }
// }







// For explanation, please see http://www.geeksforgeeks.org/largest-rectangle-under-histogram/





//stack stack push 单调增的的数字的index , once met the number less than the stack , pop 
public class Solution {
    public int largestRectangleArea(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }
}
// OP's Note: Two years later I need to interview again. I came to this problem and I couldn't understand this solution. After reading the explanation through the link above, I finally figured this out again.
// Two key points that I found helpful while understanding the solution:

// Do push all heights including 0 height.
// i - 1 - s.peek() uses the starting index where height[s.peek() + 1] >= height[tp], because the index on top of the stack right now is the first index left of tp with height smaller than tp's height.












// Example:

// Input: [2,1,5,6,2,3]
// Output: 10







 public class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minheight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minheight = Math.min(minheight, heights[j]);
                maxarea = Math.max(maxarea, minheight * (j - i + 1));
            }
        }
        return maxarea;
    }
}






//O(N^2)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        boolean[] visited = new boolean[n];
        Queue<int[]> que = new PriorityQueue<>((a , b) -> a[1] - b[1]);
        for(int i = 0 ; i < n ; i++){
            que.offer(new int[]{i , heights[i]});
        }
        //NlogN

        int max = 0;
        while(!que.isEmpty()){
            int[] cur = que.poll();
            visited[cur[0]] = true;
            int l = cur[0] - 1 , r = cur[0] + 1;
            int ct = 1;
            while(l >= 0 && !visited[l]){
                l--;
                ct++;
            }
            while(r < n && !visited[r]){
                r++;
                ct++;
            }
            max = Math.max(max , ct * cur[1]);
        }
        return max;
    }
}