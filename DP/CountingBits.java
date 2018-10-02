
// Counting Bits
// Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
// Example
// Given num = 5 you should return [0,1,1,2,1,2].
// Challenge
// It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
// Space complexity should be O(n).
// Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

public class Solution {
    /**
     * @param num: a non negative integer number
     * @return: an array represent the number of 1's in their binary
     */
    public int[] countBits(int n) {
        if(n == 0){
            return new int[]{0};
        }
    
        int[] f = new int[n + 1];
        f[0] = 0;
        int i = 1;
        for( ; i <= n  ; i++){
            f[i] = f[i >> 1] + i % 2; //二进制后向右移位 最低位去除后（/2） ， 去除的一位用（%2 判断） 还剩多少1（ 子问题 ）
        }
        return f;
    }
}