// MedianoftwoSortedArrays
// There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.

// Example
// Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.

// Given A=[1,2,3] and B=[4,5], the median is 3.

// Challenge
// The overall run time complexity should be O(log (m+n)).

// O(log(min(m,n)))
//O(1)
class Solution {
 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2 , nums1);
        }
        int len = nums1.length + nums2.length;
        int cut1 = 0 , cut2 = 0 , cutL = 0 , cutR = nums1.length;
        while(cut1 <= nums1.length){
            cut1 = (cutR - cutL) / 2 + cutL;
            cut2 = len / 2 - cut1;
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];  
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1]; 
            double R1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1]; 
            double R2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2]; 
            if(L1 > R2){
                cutR = cut1 - 1;
            }
            else if(L2 > R1){
                cutL = cut1 + 1;
            }
            else{
                if(len % 2 == 0){
                    return ((Math.max(L1 , L2) + Math.min(R1 , R2)) / 2);
                }
                else{
                    return  Math.min(R1 , R2);
                }
            }
        } 
        return -1;
    }
}

// https://www.programcreek.com/2012/12/leetcode-median-of-two-sorted-arrays-java/

public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int len = nums1.length + nums2.length;
    if(len % 2 == 0){
        return (double)(findKth(0 , 0 , nums1 , nums2 ,len / 2 + 1) + findKth(0 , 0 , nums1 , nums2 ,len / 2)) / 2;
    }
    return (double)findKth(0 , 0 , nums1 , nums2 ,len / 2 + 1);
}

private int findKth(int p1 , int p2 , int[] nums1 , int[] nums2 , int k){
    if(p1 >= nums1.length){
        return nums2[p2 + k - 1];
    }
    if(p2 >= nums2.length){
        return nums1[p1 + k - 1];
    }
    if(k == 1){
        return Math.min(nums1[p1] , nums2[p2]);
    }

    int m = p1 + k / 2 - 1 < nums1.length ? nums1[p1 + k / 2 - 1] : Integer.MAX_VALUE;
    int n = p2 + k / 2 - 1 < nums2.length ? nums2[p2 + k / 2 - 1] : Integer.MAX_VALUE;
    

    if(m < n){
        return findKth( p1 + k / 2, p2 , nums1 , nums2 , k - k / 2);    //用 减法
    }
    else{
         return findKth( p1, p2 + k / 2 , nums1 , nums2 , k - k / 2);
    }

}







































public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int total = nums1.length+nums2.length;
    if(total%2==0){
        return (findKth(total/2+1, nums1, nums2, 0, 0) + findKth(total/2, nums1, nums2, 0, 0))/2.0;
    }else{
        return findKth(total/2+1, nums1, nums2, 0, 0);
    }
}
 
public int findKth(int k, int[] nums1, int[] nums2, int s1, int s2){
    if(s1>=nums1.length)
        return nums2[s2+k-1];
 
    if(s2>=nums2.length)
        return nums1[s1+k-1];
 
    if(k==1)
        return Math.min(nums1[s1], nums2[s2]);
 
    int m1 = s1+k/2-1;
    int m2 = s2+k/2-1;
 
    int mid1 = m1<nums1.length?nums1[m1]:Integer.MAX_VALUE;    
    int mid2 = m2<nums2.length?nums2[m2]:Integer.MAX_VALUE;
 
    if(mid1<mid2){
        return findKth(k-k/2, nums1, nums2, m1+1, s2);
    }else{
        return findKth(k-k/2, nums1, nums2, s1, m2+1);
    }
}
















class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}




// 这一题如果用二分法来做，其实就是一个二分答案的过程
// 首先我们已经得到了上下界限，那么答案必定是在这个上下界限中的，需要实现的就是从这个歌上下界限中找出答案
// 我们每次取的 mid，其实就是我们每次在假设答案为 mid，二分的过程就是不断的推翻这个假设，然后再假设新的答案
// 需要满足的条件为：
// 上面算法描述中的 sum 需要等于 k，这里的 k = (A.length + B.length) / 2. 如果 sum < k，很明显当前的 mid 偏小，需要增大，否则就说明当前的 mid 偏大，需要缩小.
// 最终在 start 与 end 相邻的时候退出循环，判断 start 跟 end 哪个符合条件即可得到最终结果



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