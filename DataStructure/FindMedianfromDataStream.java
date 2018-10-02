
// Find Median from Data Stream
// Numbers keep coming, return the median of numbers at every time a new number added.

// Example
// For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].

// For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].

// For numbers coming list: [2, 20, 100], return [2, 2, 20].

// Challenge
// Total run time in O(nlogn).

// Clarification
// What's the definition of Median?

// Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. 
// For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: the median of numbers
     */


    //compare median small go to front , larger go to end 
    //compare heap size  
    //move the related
    public int[] medianII(int[] nums) {
        Queue<Integer> minHeap = new PriorityQueue();
        Queue<Integer> maxHeap = new PriorityQueue(new Comparator(){
            @Override
            public int compare(Object o1 , Object o2){
                return (int)o2 - (int)o1;
            }
        });
        
        int[] mid = new int[nums.length];
        mid[0] = nums[0];
        for(int i = 1 ; i < nums.length; i++){
            if(mid[i - 1] >= nums[i]){
                maxHeap.offer(nums[i]);
            }
            else{
                minHeap.offer(nums[i]);
            }
            int sizeMin = minHeap.size();
            int sizeMax = maxHeap.size();
            
            if(sizeMin == sizeMax || sizeMin == sizeMax + 1){
                mid[i] = mid[i - 1];
            }
            else if(sizeMin > sizeMax + 1){
                maxHeap.offer(mid[i - 1]);
                mid[i] = minHeap.poll();                
            }
            else{
                minHeap.offer(mid[i - 1]);
                mid[i] = maxHeap.poll();
            }
        }
        return mid;
    }
}