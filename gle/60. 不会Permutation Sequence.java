// 60. Permutation Sequence
// DescriptionHintsSubmissionsDiscussSolution
// The set [1,2,3,...,n] contains a total of n! unique permutations.

// By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

// "123"
// "132"
// "213"
// "231"
// "312"
// "321"
// Given n and k, return the kth permutation sequence.

// Note:

// Given n will be between 1 and 9 inclusive.
// Given k will be between 1 and n! inclusive.
// Example 1:

// Input: n = 3, k = 3
// Output: "213"
// Example 2:

// Input: n = 4, k = 9
// Output: "2314"
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int total = 1;
        for(int i = 1 ; i < n ; i++){
            total *= i;
        }
        List<Integer> nums = new ArrayList<>();
        for (int i=1; i<=n; i++) nums.add(i);
        while(n > 1){
            int index = (k - 1) / total;
            sb.append(nums.get(index));
            nums.remove(index);
            k -= index * total;
            // move to next bit
            n--;
            total /= n;
        }
        sb.append(nums.get(0));
        return sb.toString();
    }
}




public String getPermutation(int n, int k) {
    List<Integer> nums = new ArrayList<>();
    for (int i=1; i<=n; i++) nums.add(i);
    int total = 1;
    for (int i=1; i<n; i++) total *= i;
    StringBuilder sb = new StringBuilder();
    while (n > 1) {
        int idx = (k-1)/total;
        sb.append(nums.get(idx));
        nums.remove(idx);
        k -= idx * total;
        total /= --n;
    }
    sb.append(nums.get(0));
    return sb.toString();
}