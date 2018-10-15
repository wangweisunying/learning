// 32. Longest Valid Parentheses
// DescriptionHintsSubmissionsDiscussSolution
// Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

// Example 1:

// Input: "(()"
// Output: 2
// Explanation: The longest valid parentheses substring is "()"
// Example 2:

// Input: ")()())"
// Output: 4
// Explanation: The longest valid parentheses substring is "()()"





class Solution {
    public int longestValidParentheses(String s) {
        int[] arr = s.toCharArray();
        //以i为结束时最长自学列的长度
        int[] f = new int[s.length()];
        int max = 0;
        //从1 开始
        for(int i = 1 ; i < arr.length ; i++){
            if(arr[i] == ')'){
                if(arr[i - 1] == '('){
                    f[i] = (i == 1 ?  0 : f[i - 2]) + 2;
                }
                //arr[i - 1] == ')'  ))看看 i - arr[i - 1] - 1 这个位置是不是‘（’ 如果 arr(i -1) == 0 也就是 i - 1的位置 
                else if(i - f[i - 1] - 1 >=0 && arr[i - f[i - 1] - 1] == '(' ]){
                    int pre = (i - f[i - 1] - 2 >= 0) ? f[i - f[i - 1] - 2] : 0;
                    f[i] =  pre + f[i - 1] + 2;
                }
                max = Math.max(max , f[i]);
            }

        }
        return max;
    }
}