// 373. Find K Pairs with Smallest Sums
// DescriptionHintsSubmissionsDiscussSolution
// You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

// Define a pair (u,v) which consists of one element from the first array and one element from the second array.

// Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

// Example 1:

// Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
// Output: [[1,2],[1,4],[1,6]] 
// Explanation: The first 3 pairs are returned from the sequence: 
//              [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// Example 2:

// Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
// Output: [1,1],[1,1]
// Explanation: The first 2 pairs are returned from the sequence: 
//              [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// Example 3:

// Input: nums1 = [1,2], nums2 = [3], k = 3
// Output: [1,3],[2,3]
// Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]

// 1 , 2 , 3
// 1 , 2 , 3

class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList();
         if(nums1.length == 0 || nums2.length == 0){
            return res;
        }
        HashSet<String> set = new HashSet(Arrays.asList(0 + " " + 0 ));
        Queue<int[]> que = new PriorityQueue<>((a , b) -> (a[2] - b[2]));
        que.offer(new int[]{0, 0, nums1[0] + nums2[0]});
        for(int i = 0  ; i < k ; i++){
            int[] cur = que.poll();
            if(cur == null) break;
            res.add(new int[]{nums1[cur[0]] , nums2[cur[1]]});
            if(cur[0] != nums1.length - 1 && !set.contains((cur[0] + 1) + " " + cur[1])){
                set.add((cur[0] + 1) + " " + cur[1]);
                que.offer(new int[]{cur[0] + 1 , cur[1] , nums1[cur[0] + 1] + nums2[cur[1]]});
            }
            if(cur[1] != nums2.length - 1 && !set.contains(cur[0] + " " + (cur[1] + 1))){
                set.add(cur[0] + " " + (cur[1] + 1));
                que.offer(new int[]{cur[0], cur[1] + 1 , nums1[cur[0]] + nums2[cur[1] + 1]});
            }
        }
        return res;
    }
}





public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->a[0]+a[1]-b[0]-b[1]);
        List<int[]> res = new ArrayList<>();
        if(nums1.length==0 || nums2.length==0 || k==0) return res;
        for(int i=0; i<nums1.length && i<k; i++) que.offer(new int[]{nums1[i], nums2[0], 0});
        while(k-- > 0 && !que.isEmpty()){
            int[] cur = que.poll();
            res.add(new int[]{cur[0], cur[1]});
            if(cur[2] == nums2.length-1) continue;
            que.offer(new int[]{cur[0],nums2[cur[2]+1], cur[2]+1});
        }
        return res;
    }
}


public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->a[0]+a[1]-b[0]-b[1]);
        List<int[]> res = new ArrayList<>();
        if(nums1.length==0 || nums2.length==0 || k==0) return res;
        for(int i=0; i<nums1.length && i<k; i++) que.offer(new int[]{nums1[i], nums2[0], 0});
        while(k-- > 0 && !que.isEmpty()){
            int[] cur = que.poll();
            res.add(new int[]{cur[0], cur[1]});
            if(cur[2] == nums2.length-1) continue;
            que.offer(new int[]{cur[0],nums2[cur[2]+1], cur[2]+1});
        }
        return res;
    }
}



