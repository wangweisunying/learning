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
        // write your code here
    }
}














public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        return findMedian(
            new PartialArray(A),
            new PartialArray(B)
        );
    }
    
    private double findMedian(PartialArray A, PartialArray B) {
        while (!A.isEmpty() && !B.isEmpty()) {
            if (A.size() == 1 && B.size() == 1) {
                return (A.getMedian() + B.getMedian()) / 2.0;
            }
            
            PartialArray lowerArr = A;
            int lowerIndex = A.getLowerMedianIndex();
            if (A.getLowerMedian() > B.getLowerMedian()) {
                lowerArr = B;
                lowerIndex = B.getLowerMedianIndex();
            }
            
            PartialArray upperArr = A;
            int upperIndex = A.getUpperMedianIndex();
            if (A.getUpperMedian() < B.getUpperMedian()) {
                upperArr = B;
                upperIndex = B.getUpperMedianIndex();
            }
            
            int numOfRemoved = Math.min(
                lowerArr.getNumOfLower(lowerIndex),
                upperArr.getNumOfUpper(upperIndex)
            );
            
            if (lowerArr.get(lowerIndex) == upperArr.get(upperIndex)) {
                return lowerArr.get(lowerIndex);
            }
            
            lowerArr.removeLower(numOfRemoved);
            upperArr.removeUpper(numOfRemoved);
        }
        
        if (A.isEmpty()) {
            return B.getMedian();
        }
        
        return A.getMedian();
    }
}

class PartialArray {
    int[] arr;
    int start, end;
    
    PartialArray(int[] arr) {
        this.arr = arr;
        this.start = 0;
        this.end = arr.length - 1;
    }
    
    public int getLowerMedian() {
        return arr[(start + end) / 2];
    }
    
    public int getUpperMedian() {
        return arr[(start + end + 1) / 2];
    }
    
    public int getLowerMedianIndex() {
        return (start + end) / 2;
    }
    
    public int getUpperMedianIndex() {
        return (start + end + 1) / 2;
    }
    
    public int size() {
        return end - start + 1;
    }
    
    public double getMedian() {
        return (getUpperMedian() + getLowerMedian()) / 2.0;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public int getNumOfUpper(int index) {
        if (index == end) {
            return 1;
        }
        return end - index;
    }
    
    public int getNumOfLower(int index) {
        if (index == start) {
            return 1;
        }
        return index - start;
    }
    
    public void removeLower(int numOfRemoved) {
        start += numOfRemoved;
    }
    
    public void removeUpper(int numOfRemoved) {
        end -= numOfRemoved;
    }
    
    public int get(int index) {
        return arr[index];
    }
}




public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int n = A.length + B.length;
        
        if (n % 2 == 0) {
            return (
                findKth(A, 0, B, 0, n / 2) + 
                findKth(A, 0, B, 0, n / 2 + 1)
            ) / 2.0;
        }
        
        return findKth(A, 0, B, 0, n / 2 + 1);
    }

    // find kth number of two sorted array
    public static int findKth(int[] A, int startOfA,
                              int[] B, int startOfB,
                              int k){       
        if (startOfA >= A.length) {
            return B[startOfB + k - 1];
        }
        if (startOfB >= B.length) {
            return A[startOfA + k - 1];
        }

        if (k == 1) {
            return Math.min(A[startOfA], B[startOfB]);
        }
        
        int halfKthOfA = startOfA + k / 2 - 1 < A.length
            ? A[startOfA + k / 2 - 1]
            : Integer.MAX_VALUE;
        int halfKthOfB = startOfB + k / 2 - 1 < B.length
            ? B[startOfB + k / 2 - 1]
            : Integer.MAX_VALUE; 
        
        if (halfKthOfA < halfKthOfB) {
            return findKth(A, startOfA + k / 2, B, startOfB, k - k / 2);
        } else {
            return findKth(A, startOfA, B, startOfB + k / 2, k - k / 2);
        }
    }
}









public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    //merge  O( m + n);
    public double findMedianSortedArrays(int[] A, int[] B) {
        int[] tmp = new int[A.length + B.length];
        
        int a_index = 0 , b_index = 0 , tmp_index = 0;
        while(a_index < A.length && b_index < B.length){
            if(A[a_index] <= B[b_index]){
                tmp[tmp_index++] = A[a_index++];
            }
            else{
                tmp[tmp_index++] = B[b_index++];
            }
        }
        while(a_index < A.length){
                tmp[tmp_index++] = A[a_index++];
        }
        while(b_index < B.length){
                tmp[tmp_index++] = B[b_index++];
        }
        int n = tmp.length;
 
        if(n % 2 == 0){
            return ((double)tmp[n / 2] + (double)tmp[(n - 1 ) /2]) / 2;
        }
        return (double)(tmp[n /2]);

    }
}











































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