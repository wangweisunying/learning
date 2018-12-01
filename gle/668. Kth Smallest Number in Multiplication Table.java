// 668. Kth Smallest Number in Multiplication Table
// DescriptionHintsSubmissionsDiscussSolution
// Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?

// Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

// Example 1:
// Input: m = 3, n = 3, k = 5
// Output: 
// Explanation: 
// The Multiplication Table:
// 1	2	3
// 2	4	6
// 3	6	9

// The 5-th smallest number is 3 (1, 2, 2, 3, 3).
// Example 2:
// Input: m = 2, n = 3, k = 6
// Output: 
// Explanation: 
// The Multiplication Table:
// 1	2	3
// 2	4	6

// The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
// Note:
// The m and n will be in the range [1, 30000].
// The k will be in the range [1, m * n]

//  For each of m rows, the i^{th}i th
//row looks like {[i, 2*i, 3*i, ..., n*i]}[i, 2*i, 3*i, ..., n*i]. The largest possible \text{k*i ≤ x}k*i ≤ x that could appear is \text{k = x // i}k = x /
// i. However, if \text{x}x is really big, then perhaps \text{k &gt; n}k > n, so in total there are \text{min(k, n) = min(x // i, n)}min(k, n) = min(x // i, n) 
// values in that row that are less than or equal to \text{x}x.
// val binary search
class Solution {
    public int findKthNumber(int m, int n, int k) {
        int s = 1  , e = m*n;
        while(s + 1 < e){
            int mid = (e - s) /2 + s;
            int ct = count(mid , m , n );
            if(ct >= k ){
                e = mid;
            }
            else{
                s = mid;
            }
        }
        if(count(s , m , n ) >= k ){
            return s;
        }
        return e;
    }

    //int scan row by row find the min number of the count 
    // 1   2   3   4    val
    // 1x2 2x2 3x2 4x2  val/2
    // 1xi 2xi 3xi 4xi  ...val / i
    private int count(int val , int m , int n){
        int res = 0;
        for(int i = 1 ; i <= m ; i++){
            res += Math.min( val / i , n);
        }
        return res;
    }
}