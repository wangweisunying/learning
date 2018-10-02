// Wiggle Sort II
// Given an unsorted array nums, reorder it such that

// nums[0] < nums[1] > nums[2] < nums[3]....
// Example
// Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].

// Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

// Challenge
// Can you do it in O(n) time and/or in-place with O(1) extra space?

// Notice
// You may assume all input has valid answer.

// O(N) 最优
public class Solution {
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int mid = find(nums, 0, nums.length - 1, nums.length / 2);
    
        System.out.println(Arrays.toString(nums));
        //array has already be sorted like arr left < mid < arr right
        
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = mid;
        }
        int l, r;
        if (nums.length % 2 == 0) {
            l = nums.length - 2;
            r = 1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < mid) {
                    ans[l] = nums[i];
                    l -= 2;
                } else if (nums[i] > mid) {
                    ans[r] = nums[i];
                    r += 2;
                }
            }
        } else {
            l = 0;
            r = nums.length - 2;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < mid) {
                    ans[l] = nums[i];
                    l += 2;
                } else if (nums[i] > mid) {
                    ans[r] = nums[i];
                    r -= 2;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }
        
    }
    private int find(int[] nums, int s, int e, int k) {
        if (s >= e) {
            return nums[k];
        }

        int l = s, r = e;
        int p = nums[(s + e) / 2];
        while (l <= r) {
            while (l <= r && nums[l] < p) {
                l++;
            }
            while (l <= r && nums[r] > p) {
                r--;
            }
            if (l <= r) {
                swap(nums, l, r);
                l++;
                r--;
            }
        }
        if (k <= r) {
           return find(nums, s, r, k);
        }
        if (k >= l) {
           return find(nums, l, e, k);
        }
        return p;
  
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}




public class Solution {
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length == 0 ){
            return;
        }
        qs(nums , 0 , nums.length - 1);
        //array has already be sorted like arr left < mid < arr right
        
        int left = (nums.length - 1) / 2  ,  right = nums.length - 1;
        int[] ans = new int[nums.length];
        for(int i = 0; i < nums.length ; i++){
            if(i % 2 == 0){
                ans[i] = nums[left --];
            }
            else{
                ans[i] = nums[right --];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i]; 
        } 
    }
    private void qs(int[] nums , int s ,int e){
        if(s >= e){
            return;
        }
        int l = s , r = e;
        int p = nums[(l + r) / 2];
        while(l <= r){
            while(l <= r && nums[l] < p){
                l ++;
            }
            while(l <= r && nums[r] > p){
                r --;
            }                
            if(l <= r){
                swap(nums, l , r);
                l++;
                r--;
            }
        }
        qs(nums, s , r );
        qs(nums ,l , e );
    }
        
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
}






public class Solution {
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length == 0 ){
            return;
        }
        int midIndex = qs(nums , 0 , nums.length - 1 ,);
        //array has already be sorted like arr left < mid < arr right
        
        int left = (nums.length - 1) / 2  ,  right = nums.length - 1;
        int[] ans = new int[nums.length];
        for(int i = 0; i < nums.length ; i++){
            if(i % 2 == 0){
                ans[i] = nums[left --];
            }
            else{
                ans[i] = nums[right --];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i]; 
        } 
    }
    private void qs(int[] nums , int s ,int e){
        if(s >= e){
            return;
        }
        int l = s , r = e;
        int p = nums[(l + r) / 2];
        while(l <= r){
            while(l <= r && nums[l] < p){
                l ++;
            }
            while(l <= r && nums[r] > p){
                r --;
            }                
            if(l <= r){
                swap(nums, l , r);
                l++;
                r--;
            }
        }
        qs(nums, s , r );
        qs(nums ,l , e );
    }
        
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
}



public class Solution {
    public static void wiggleSort(int[] nums) {
        int[] tem = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tem[i] = nums[i];
        }
        int mid = partition(tem, 0, nums.length-1, nums.length/2);
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = mid;
        }
        int l, r;
        if (nums.length % 2 == 0) {
            l = nums.length - 2;
            r = 1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < mid) {
                    ans[l] = nums[i];
                    l -= 2;
                } else if (nums[i] > mid) {
                    ans[r] = nums[i];
                    r += 2;
                }
            }
        } else {
            l = 0;
            r = nums.length - 2;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < mid) {
                    ans[l] = nums[i];
                    l += 2;
                } else if (nums[i] > mid) {
                    ans[r] = nums[i];
                    r -= 2;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }
    }
    public static int partition(int[] nums, int l, int r, int rank) {
        int left = l, right = r;
        int now = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= now) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= now) {
                left++;
            }
            nums[right] = nums[left];
        }
        if (left - l == rank) {
            return now;
        } else if (left - l < rank) {
            return partition(nums, left + 1, r, rank - (left - l + 1));
        } else {
            return partition(nums, l, right - 1, rank);
        }
    }
}