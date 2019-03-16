// 905. Sort Array By Parity
// Easy

// 336

// 44

// Favorite

// Share
// Given an array A of non-negative integers, return an array consisting of all the even elements of A, 
// followed by all the odd elements of A.

// You may return any answer array that satisfies this condition.

 

// Example 1:

// Input: [3,1,2,4]
// Output: [2,4,3,1]
// The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 

// Note:

// 1 <= A.length <= 5000
// 0 <= A[i] <= 5000

#include <string>
#include <vector>
#include <unordered_set>
using namespace std;
class Solution {
public:
    vector<int> sortArrayByParity(vector<int>& A) {
        int s = 0 , e = A.size() - 1;
        while(s < A.size() && s < e){
            while(A[s] % 2 != 0 && s < e){
                swap(A[s] , A[e --]);
                // int tmp = A[s];
                // A[s] = A[e];
                // A[e--] = tmp;
            }
            ++s;
        }
        return A;
    }    
};
