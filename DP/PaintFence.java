
// Paint Fence
// There is a fence with n posts, each post can be painted with one of the k colors.
// You have to paint all the posts such that no more than two adjacent fence posts have the same color.
// Return the total number of ways you can paint the fence.

// Example
// Given n=3, k=2 return 6

//       post 1,   post 2, post 3
// way1    0         0       1 
// way2    0         1       0
// way3    0         1       1
// way4    1         0       0
// way5    1         0       1
// way6    1         1       0
public class Solution {
    /**
     * @param n: non-negative integer, n posts
     * @param k: non-negative integer, k colors
     * @return: an integer, the total number of ways
     */
    public int numWays(int n, int k) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return k;
        }
        if(n == 2){
            return k * k;
        }
        int same = k;
        int dif = k*(k - 1);
        int i = 3;
        for( ; i <= n ; i ++){
            int tmp = dif;
            dif = same *(k - 1) + dif* (k - 1); // dif 分为前两个一样 的情况 和前两个不一样的情况
            same = tmp;
        }
        return same + dif;
    }
}