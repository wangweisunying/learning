// Wood Cut
// Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length
// . What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

// Example
// For L=[232, 124, 456], k=7, return 114.




// Challenge
// O(n log Len), where Len is the longest length of the wood.

// Notice
// You couldn't cut wood into float length.

// If you couldn't get >= k pieces, return 0.


public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
     //范围二分 ， 找出满足条件的最大值 ,小于 114 ，满足 》=k 则 往上找是否满足条件存在，
    public int woodCut(int[] L, int k) {
        int r = - 1;
        for(int x : L){
            r = Math.max(x , r);
        }
        int l = 1;
        while(l + 1 < r){
            int mid = l + (r - l) / 2 ; // avoid out of integer range
            if(count(L , mid) >= k){
                l = mid;    
            }
            else{
                r = mid;
            }
        }
        if(count(L , r) >= k){
            return r;
        }
        if(count(L , l) >= k){
            return l;
        }
        return 0;
    }
    private int count(int[] L , int len){
        int res = 0; 
        for(int i : L){
            res += i/len;
        }
        return res;
    }
}