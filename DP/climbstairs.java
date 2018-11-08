class Solution {
    public int climbStairs(int n , int k) {
    	int[] f = new int[n + 1];
    	for(int i = 1;i <= n;i++) {
    		int j = i - k >= 0 ? i - k : 0;
            for( ; j < i ;j++){
                f[i] += f[j];
            }
    	}
    	return f[n];
    }
}