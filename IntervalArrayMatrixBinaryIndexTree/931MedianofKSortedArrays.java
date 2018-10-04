
// Median of K Sorted Arrays
// There are k sorted arrays nums. Find the median of the given k sorted arrays.

// Example
// Given nums = [[1],[2],[3]], return 2.00.
// 经典二分法








public class Solution {
    /**
     * @param nums: the given k sorted arrays
     * @return: the median of the given k sorted arrays
     */
    public double findMedian(int[][] nums) {
        int sum = 0;
        ArrayList<Integer> list = new ArrayList();
        for(int i = 0 ; i < nums.length ; i++){
            for(int j = 0 ; j < nums[i].length ; j ++){
                list.add(nums[i][j]);
                sum += 1;
            }
        }

        if(sum % 2 == 1){
            findK(list , 0 , list.size() - 1 , list.size() / 2);
        }
        int m1 = findK(list , 0 , list.size() - 1 , list.size() / 2);
        int m2 = findK(list , 0 , list.size() - 1 , (list.size() - 1) / 2);
        


        return (double)(Long.valueOf(m1) +  long.valueOf(m2))/2;
        
    }
    private int findK(ArrayList<Integer> list , int s , int e , int k){
        if(s >= e){
            return list.get(k);
        }
        int l = s , r = e , pivot = list.get((l + r) / 2);
        while(l <= r){
            while(l <= r && list.get(l) < pivot){
                l++;
            }
            while(l <= r && list.get(r)> pivot){
                r--;
            }
            if(l <= r){
                int tmp = list.get(l);
                list.set(l , list.get(r));
                list.set(r , tmp);
                l++;
                r--;
            }

        }
        if(k <= r){
            return findK(list , s , r , k);
        }
        if(k >= l){
            return findK(list , l , e , k);
        }
        return list.get(k);
    }
}
























public class Solution {
    /**
     * @param nums: the given k sorted arrays
     * @return: the median of the given k sorted arrays
     */
    public double findMedian(int[][] nums) {
        if (nums == null || nums.length == 0) {
             return 0;
        }
        // write your code here
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            n += nums[i].length;
        }
        if (n == 0) {
            return 0;
        }
        // System.out.println(n);
        int[] allNums = new int[n];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                allNums[count++] = nums[i][j];
            }
        }
        Arrays.sort(allNums);
        return n % 2 == 0 ? ((double) allNums[n/2 - 1] + (double) allNums[n/2])/ (double)2 : allNums[n/2];
    }
}











public class Solution {
    /**
     * @param nums: the given k sorted arrays
     * @return: the median of the given k sorted arrays
     */
    public double findMedian(int[][] nums) {
        if(nums.length == 0 || (nums.length == 1 && nums[0].length == 0)){
            return (double) 0;
        }
        ArrayList<Integer> list = new ArrayList();
        int ct = 0;
        for(int[] tmp : nums){
            for(int x : tmp){
                list.add(x);
            }
        }
        Collections.sort(list);
        if(list.size() % 2 == 1){
            return (double)list.get(list.size() / 2);
        }
        else{
            return ((double)list.get(list.size() / 2) + (double)list.get((list.size() - 1) / 2)) / 2;
        }
    }
}






public class Solution {
    /**
     * @param nums: the given k sorted arrays
     * @return: the median of the given k sorted arrays
     */
    public double findMedian(int[][] nums) {
        int n = getTotal(nums);
        if (n == 0) {
            return 0;
        }
        
        if (n % 2 != 0) {
            return findKth(nums, n / 2 + 1);
        }
        
        return (findKth(nums, n / 2) + findKth(nums, n / 2 + 1)) / 2.0;
    }
    
    private int getTotal(int[][] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i].length;
        }
        return sum;
    }
    
    // k is not zero-based, it starts from 1.
    private int findKth(int[][] nums, int k) {
        int start = 0, end = Integer.MAX_VALUE;
        
        // find the last number x that >= k numbers are >= x. 
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (getGTE(nums, mid) >= k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (getGTE(nums, start) >= k) {
            return start;
        }
        
        return end;
    }
    
    // get how many numbers greater than or equal to val in 2d array
    private int getGTE(int[][] nums, int val) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += getGTE(nums[i], val);
        }
        return sum;
    }
    
    // get how many numbers greater than or equal to val in an array
    private int getGTE(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int start = 0, end = nums.length - 1;
        
        // find first element >= val 
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= val) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums[start] >= val) {
            return nums.length - start;
        }
        
        if (nums[end] >= val) {
            return nums.length - end;
        }
        
        return 0;
    }
}