// 96. Unique Binary Search Trees
// Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

// Example:

// Input: 3
// Output: 5
// Explanation:
// Given n = 3, there are a total of 5 unique BST's:

//    1         3     3      2      1
//     \       /     /      / \      \
//      3     2     1      1   3      2
//     /     /       \                 \
//    2     1         2                 3
//  0 - 1
//  1 - 1
//  2 - 2
//  3 :    (0)1(2) 1x2   (1)2(1) 1x1   (2)3(0) 2x1     = 5;
//  4 :    (0)1(3) 1x5   (1)2(2) 1x2   (2)3(1) 2x1  (3)4(0) 5x1    = 14;




// BST  WHEN NODE = 1  F[0] * F[2];
//     WHEN NODE = 2   F[1] * F[1];
//     WHEN NODE = 3   F[2] * F[0];
//     f[n] = f[0]f[n - 1] + f[1]f[n - 2] + ... f[n-1]f[0]; 

class Solution {
    public int numTrees(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        for(int i = 1; i <= n ; i++){
            for(int j = 0 ; j < i ; j ++){
                f[i] += f[j] * f[i - 1 - j];
            }
        }
        return f[n];
    }
}





















class Solution {
    public int numTrees(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        for(int i = 1 ; i <= n ; i++){
            for(int j = 0 ; j < i ; j ++){
                f[i] += f[j] * f[i - j - 1];
            }
        }
        return f[n];
    }
}