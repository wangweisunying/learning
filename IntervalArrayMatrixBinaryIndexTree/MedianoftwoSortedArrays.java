// MedianoftwoSortedArrays
// There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.

// Example
// Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.

// Given A=[1,2,3] and B=[4,5], the median is 3.

// Challenge
// The overall run time complexity should be O(log (m+n)).

public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int i = 0 , j = 0;
        int m = A.length , n = B.length;
        
        int ct = 0;
        int mid = ((m + n) / 2) + 1;
        int tmpNow = 0;
        int tmpPre = 0;
        while(i < m && j < n){
            if(ct == mid ){
                break;
            }
            tmpPre = tmpNow;
            if(A[i] <= B [j]){
                tmpNow = A[i ++];
                
            }
            else{
                tmpNow = B[j ++];
            }
            ct++;
        }
        while(i < m){
            if(ct == mid ){
                break;
            }
            tmpPre = tmpNow;
            tmpNow = A[i ++];
            ct++;
        }
        while(j < n){
            if(ct == mid ){
                break;
            }
            tmpPre = tmpNow;
            tmpNow = B[j ++];
            ct++;
        }
        if((m + n) % 2 == 1){
                return (double)tmpNow;
            }
        return  (((double)tmpNow +(double)tmpPre)/2);
        
    }
}