// 923. 3Sum With Multiplicity
// DescriptionHintsSubmissionsDiscussSolution
// Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.

// As the answer can be very large, return it modulo 10^9 + 7.

 

// Example 1:

// Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
// Output: 20
// Explanation: 
// Enumerating by the values (A[i], A[j], A[k]):
// (1, 2, 5) occurs 8 times;
// (1, 3, 4) occurs 8 times;
// (2, 2, 4) occurs 2 times;
// (2, 3, 3) occurs 2 times.
// Example 2:

// Input: A = [1,1,2,2,2,2], target = 5
// Output: 12
// Explanation: 
// A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
// We choose one 1 from [1,1] in 2 ways,
// and two 2s from [2,2,2,2] in 6 ways.
 

// Note:

// 3 <= A.length <= 3000
// 0 <= A[i] <= 100
// 0 <= target <= 300

class Solution {
    public int threeSumMulti(int[] nums, int target) {
        if(nums == null || nums.length < 3){
            return 0;
        }
        int MOD = 1_000_000_007;
        long ct = 0;
        Arrays.sort(nums);
        for(int k = 2 ; k < nums.length ; k++){
            //这里不可以去重 存在 nums[k] == nums[j - 1]的情况 会有偏差
            int i = 0 , j = k - 1;
            while(i < j){
                if(nums[i] + nums[j] + nums[k] == target){
                    if(nums[i]!=nums[j]){
                        int left = 1 , right = 1; // 分别统计左右两边指针的 重复个数相加
                        while(i < j && nums[i] == nums[i + 1]){
                            i++;
                            left++;
                        }
                        while(i < j && nums[j] == nums[j - 1]){
                            j--;
                            right++;
                        }
                        ct += left * right;
                        ct %= MOD;
                        i++;
                        j--;
                    }
                    else{
                        ct += (j - i + 1) * (j - i) / 2;  //(Cn2) 组合问题从（ j - i + 1） 个中选择2个
                        ct %= MOD;
                        break;
                    }
                }
                else if(nums[i] + nums[j] + nums[k] < target){
                    i++;
                }
                else{
                    j--;
                }
            }
        }
        return (int)ct;
    }
}
class Solution {
    public int threeSumMulti(int[] A, int target) {
        int MOD = 1_000_000_007;
        long ans = 0;
        Arrays.sort(A);

        for (int i = 0; i < A.length; ++i) {
            // We'll try to find the number of i < j < k
            // with A[j] + A[k] == T, where T = target - A[i].

            // The below is a "two sum with multiplicity".
            int T = target - A[i];
            int j = i+1, k = A.length - 1;

            while (j < k) {
                // These steps proceed as in a typical two-sum.
                if (A[j] + A[k] < T)
                    j++;
                else if (A[j] + A[k] > T)
                    k--;
                else if (A[j] != A[k]) {  // We have A[j] + A[k] == T.
                    // Let's count "left": the number of A[j] == A[j+1] == A[j+2] == ...
                    // And similarly for "right".
                    int left = 1, right = 1;
                    while (j+1 < k && A[j] == A[j+1]) {
                        left++;
                        j++;
                    }
                    while (k-1 > j && A[k] == A[k-1]) {
                        right++;
                        k--;
                    }

                    ans += left * right;
                    ans %= MOD;
                    j++;
                    k--;
                } else {
                    // M = k - j + 1
                    // We contributed M * (M-1) / 2 pairs.
                    ans += (k-j+1) * (k-j) / 2;
                    ans %= MOD;
                    break;
                }
            }
        }

        return (int) ans;
    }
}
















class Solution {
    public int threeSumMulti(int[] A, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int res = 0;
        int mod = 1000000007;
        for (int i = 0; i < A.length; i++) {
            res = (res + map.getOrDefault(target - A[i], 0)) % mod;
            
            for (int j = 0; j < i; j++) {
                int temp = A[i] + A[j];
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        return res;
    }
}