// 651. 4 Keys Keyboard
// Medium

// 246

// 36

// Favorite

// Share
// Imagine you have a special keyboard with the following keys:

// Key 1: (A): Print one 'A' on screen.

// Key 2: (Ctrl-A): Select the whole screen.

// Key 3: (Ctrl-C): Copy selection to buffer.

// Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

// Now, you can only press the keyboard for N times (with the above four keys), 
// find out the maximum numbers of 'A' you can print on screen.

// Example 1:
// Input: N = 3
// Output: 3
// Explanation: 
// We can at most get 3 A's on screen by pressing following key sequence:
// A, A, A
// Example 2:
// Input: N = 7
// Output: 9
// Explanation: 
// We can at most get 9 A's on screen by pressing following key sequence:
// A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
// Note:
// 1 <= N <= 50
// Answers will be in the range of 32-bit signed integer.


//memo serachr tle 
class Solution {
    public int maxA(int n) {
        Map<String , Integer> memo = new HashMap();
        return dfs(n , 0 , 0 , memo);
    }
    private int dfs(int n , int cur , int buffer , Map<String , Integer> map){
        String key = n + "_" + cur + "_" + buffer;
        if(map.containsKey(key))return map.get(key); 
        if(n == 0) return cur;
        int res = -1;
        res = Math.max(dfs(n - 1 , cur + 1 , buffer , map) , res);
        res = Math.max(dfs(n - 1 , cur + buffer , buffer ,map) , res);
        if( n - 2 >= 0) res = Math.max(dfs(n - 2 , cur , cur , map) ,res);
        map.put(key , res);
        return res;
    }
}

// just dfs  tle 

class Solution {
    public int maxA(int n) {
        return dfs(n , 0 , 0);
    }
    private int dfs(int n , int cur , int buffer ){
        if(n == 0) return cur;
        int res = -1;
        res = Math.max(dfs(n - 1 , cur + 1 , buffer) , res);
        res = Math.max(dfs(n - 1 , cur + buffer , buffer) , res);
        if( n - 2 >=0) res = Math.max(dfs(n - 2 , cur , cur) ,res);
        return res;
    }
}


//     First of all, let's observe how many operations we need when copying.

//  Suppose we have a A on screen, copy once will use 3 operations (key2, key3, key4) and we get AA,
//  copy twice will use 4 operations (key2, key3, key4, key4) and get AAA... So copy p times taks 3+(p-1)=2+p operations.

// Use dp[i] to denote the maximum numbers of 'A' you can print using i operations. We can just try each possible p, and make sure that 2 + p < i, otherwise, 
// we do not have enough operations allowed to complete these pastes. If we copy p times, the remaining valid operations we can use will be i-(2+p), so dp[i] = Math.max(dp[i], (p+1) * dp[i-ops]).

class Solution {
    public int maxA(int n) {
        int[] f = new int[n + 1];
        for(int i = 1 ; i <= n ; i++){
            f[i] = i;
            for(int j = 1 ; j + 2 < i ; j++){
                f[i] = Math.max(f[i] , (j + 1) * f[i - (j + 2)]);
            }
        }       
        return f[n];
    }
}



public int maxA(int N) {
	int[] dp = new int[N+1];
	for (int i = 1; i <= N; i++) {
		dp[i] = i;  //only use key1, no copy
		for (int p = 1; 3+(p-1) < i; p++) {  //copy p times
			int ops = 3 + (p - 1);  //3 + (p - 1)
			dp[i] = Math.max(dp[i], (p+1) * dp[i-ops]);
		}
	}
	return dp[N];
}


//c++
class Solution {
public:
    int maxA(int n) {
        vector<int> f(n + 1 , 0);
        for(int i = 1 ; i <= n ; i++){
            f[i] = i;
            for(int j = 1 ; j + 2 < i;j++){
                f[i] = max(f[i] , f[i - (j + 2)] * (j + 1));
            }
        }
        return f[n];
    }
};