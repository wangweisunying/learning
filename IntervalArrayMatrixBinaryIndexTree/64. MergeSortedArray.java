// 64. Merge Sorted Array
// Given two sorted integer arrays A and B, merge B into A as one sorted array.

// Example
// A = [1, 2, 3, empty, empty], B = [4, 5]

// After merge, A will be filled as [1, 2, 3, 4, 5]


// [1,2,3]
// 3
// [4,5]
// 2


//from back to the front
public class Solution {
    /*
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        int a_p = m - 1 , b_p = n - 1 , cur = m + n - 1;
        while(a_p >= 0 && b_p >= 0){
            if(A[a_p] >= B[b_p]){
                A[cur--] = A[a_p--];
            }
            else{
                A[cur--] = B[b_p--];
            }
        }
        while(a_p >= 0){
            A[cur--] = A[a_p--];
        }
        while(b_p >= 0){
            A[cur--] = B[b_p--];
        }
    }
}








































class Solution {
    /**
     * @param A: sorted integer array A which has m elements, 
     *           but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
     */

    //  from end to start
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        //end to front
        int i = m - 1 , j = n - 1 , cur = m + n - 1;
        while(i >= 0 && j >= 0){
            if(A[i] >= B[j]){
                A[cur--] = A[i--];          
            }
            else{
                A[cur--] = B[j--];
            }
        }
        while(i >=0){
            A[cur--] = A[i--];
        } 
        while(j >= 0){
            A[cur--] = B[j--];
        }
    }
}

public class Solution {
    /*
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
     
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        for(int i = m ; i < m + n ; i++){
            A[i] = B[i - m];
        }
        Arrays.sort(A);
    }
}

