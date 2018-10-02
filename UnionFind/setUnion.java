// Set Union
// There is a list composed by sets. If two sets have the same elements, merge them. In the end, there are several sets left.

// Example
// Given list = [[1,2,3],[3,9,7],[4,5,10]], return 2.

// Explanation:
// There are 2 sets of [1,2,3,9,7] and [4,5,10] left.
// Given list = [[1],[1,2,3],[4],[8,7,4,5]], return 2.

// Explanation:
// There are 2 sets of [1,2,3] and [4,5,7,8] left.
// Notice
// The number of sets n <=1000.
// The number of elements for each set m <= 100.
// The element must be a non negative integer and not greater than 100000.

public class Solution {
    /**
     * @param sets: Initial set list
     * @return: The final number of sets
     */
    
    int find(int x, int[] f) {
        int fx = f[x];
        while(fx != x) {
            x = fx;
            fx = f[x];
        }
        return x;
    } 
    
    void merge(int x, int y, int[] f) {
        int fx = find(x, f);
        int fy = find(y, f);
        if(fx != fy) {
            f[fx] = fy;
        }
    }
     
    public int setUnion(int[][] sets) {
        // Write your code here
        int[] f = new int[1001];
        int[] vis = new int[100001];
        for(int i = 0; i < sets.length; i++) {
            f[i] = i;
        }
        for(int i = 0; i < 100001; i++) {
            vis[i] = -1;
        }
        for(int i = 0; i < sets.length; i++) {
            for(int j = 0; j < sets[i].length; j++) {
                int num = sets[i][j];
                if(vis[num] != -1) {
                    merge(vis[num], i, f);
                    vis[num] = find(i, f);
                } else {
                    vis[num] = find(i, f);
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < sets.length; i++) {
            if(f[i] == i) {
                ans++;
            }
        }
        System.out.println(ans);
        return ans;
    }
}