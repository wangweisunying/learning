
//  Top k Largest Numbers
// Given an integer array, find the top k largest numbers in it.

// Example
// Given [3,10,1000,-99,4,100] and k = 3.
// Return [1000, 100, 10].

public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        int[] res = new int[k];
        partition(nums , 0 , nums.length - 1, k - 1 );
        int i = 0;
        while(i < k){
            res[i++] = nums[i++];
        }
        Arrays.sort(res);
        int s = 0 , e = k - 1;
        while(s < e){
            int tmp = nums[s];
            nums[s] = nums[e];
            nums[e] = tmp;
            s++;
            e--;
        }        
        return res;
    }
    private void partition(int[] nums , int s , int e, int k){
        if(s >= e){
            return;
        }
        int l = s , r = e , pivot = nums[(l + r) / 2];
        while(l <= r){
            while(l <= r && nums[l] > pivot){
                l++;
            }
            while(l <= r && nums[r] < pivot){
                r--;
            }
            if(l <= r){
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++;
                r--;
            }
        }
        if(k <= r){
            partition(nums , s , r , k);
        }
        if(k >= l){
            partition(nums , l , e , k);
        }
    }
}



























public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        Queue<Integer> que = new PriorityQueue<Integer>(new Comparator(){
            @Override
            public int compare(Object front , Object behind){
                return (int)front - (int)behind;
            }
        });
        int i;
        for( i = 0; i < nums.length ; i ++){
            que.offer(nums[i]);
            if(que.size() > k){
                que.poll();
            }
        }
        
        int[] res = new int[k];
        int j;
        for(j = k - 1 ; j >= 0 ; j--){
            res[j] = que.poll();
        }
        return res;        
    }
}







public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        int[] res = new int[k];
        Arrays.sort(nums);
        for(int i = 0 ; i < k ;i ++){
            res[i] = nums[nums.length - 1 - i];
        }
        return res;
    }
}