// 239. Sliding Window Maximum
// DescriptionHintsSubmissionsDiscussSolution
// Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
// You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

// Example:

// Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
// Output: [3,3,5,5,6,7] 
// Explanation: 

// Window position                Max
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7
// Note: 
// You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

// Follow up:
// Could you solve it in linear time?
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return new int[]{};
        }        
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> que = new ArrayDeque();
        
        for(int i = 0 ; i < k ; i++){
            while(!que.isEmpty() && que.peekLast() < nums[i]){
                que.pollLast();
            }
            que.offerFirst(nums[i]);
        }
        res[0] = que.peekLast();
        
        for(int i = k ; i < nums.length ; i++){
            if(que.size() == k){
                que.pollLast();
            }
            while(!que.isEmpty() && que.peekLast() < nums[i]){
                System.out.println(que.pollLast());
            }
            que.offerFirst(nums[i]);
            res[i - k + 1] = que.peekLast();
        }
        return res;
    }
}

// We scan the array from 0 to n-1, keep "promising" elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.

// At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means

// If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array

// Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i,
//  then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.

// As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i-(k-1),i]


class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return new int[]{};
        }        
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        Deque<Integer> que = new ArrayDeque();
        //store index
        for(int i = 0 ; i < nums.length ; i++){
            while(!que.isEmpty() && que.peekFirst() < i - k + 1){
                que.pollFirst();
            }
            while(!que.isEmpty() && nums[que.peekLast()] < nums[i]){
                que.pollLast();
            }       
            que.offerLast(i);
            if(i - k + 1 >= 0){
                res[index++] = nums[que.peekFirst()];
            }
        }
        return res;  
    }
}





public int[] maxSlidingWindow(int[] a, int k) {		
    if (a == null || k <= 0) {
        return new int[0];
    }
    int n = a.length;
    int[] r = new int[n-k+1];
    int ri = 0;
    // store index
    Deque<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < a.length; i++) {
        // remove numbers out of range k
        while (!q.isEmpty() && q.peek() < i - k + 1) {
            q.poll();
        }
        // remove smaller numbers in k range as they are useless
        while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
            q.pollLast();
        }
        // q contains index... r contains content
        q.offer(i);
        if (i >= k - 1) {
            r[ri++] = a[q.peek()];
        }
    }
    return r;
}













// min heap nlogn
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return new int[]{};
        }        
        int[] res = new int[nums.length - k + 1];
        
        Queue<Integer> que = new PriorityQueue<>((a , b) -> (b - a));
        for(int i = 0 ; i < k ; i++){
            que.offer(nums[i]);
        }
        res[0] = que.peek();
        for(int i = k ; i < nums.length ; i++){
            que.offer(nums[k]);
            que.remove(nums[i - k]);
            res[i - k + 1] = que.peek();    
        }
        return res;  
    }
}