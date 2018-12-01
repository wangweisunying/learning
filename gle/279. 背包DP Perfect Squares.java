// 279. Perfect Squares
// DescriptionHintsSubmissionsDiscussSolution
// Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

// Example 1:

// Input: n = 12
// Output: 3 
// Explanation: 12 = 4 + 4 + 4.
// Example 2:

// Input: n = 13
// Output: 2
// Explanation: 13 = 4 + 9.

//背包问题！！！
public class Solution {
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        Arrays.fill(f , Integer.MAX_VALUE);
        f[0] = 0;
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j * j <= i ; j++ ){
                f[i] = Math.min(f[i - j * j] , f[i]);
            }
            f[i] += 1;
        }
        return f[n]; 
    }
}




//tle BFS
class Solution {
    public int numSquares(int n) {
        int num = 1;
        while(num * num <= n){
            ++num;
        }
        --num;
        Queue<int[]> que = new LinkedList();
        que.offer(new int[]{n , 0});
        
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                int[] cur = que.poll();
                if(cur[0] == 0) return cur[1];
                for(int j = num ; j >= 1 ; j--){
                    if(j * j <= cur[0]){
                        que.offer(new int[]{cur[0] - j * j , cur[1] + 1});
                    }
                }
            }
        }
        return -1;
    }
}


