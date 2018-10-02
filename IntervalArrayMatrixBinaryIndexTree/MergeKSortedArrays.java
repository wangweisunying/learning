
//MergeKSortedArrays
// Given k sorted integer arrays, merge them into one sorted array.

// Example
// Given 3 sorted arrays:

// [
//   [1, 3, 5, 7],
//   [2, 4, 6],
//   [0, 8, 9, 10, 11]
// ]
// return [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11].


public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        Queue<Integer> que = new PriorityQueue();
        int ct = 0;
        for(int[] tmp : arrays){
            for(int x : tmp){
                que.offer(x);
                ct++;
            }
        }
        int[] res = new int[ct];
        int n = 0;
        while(!que.isEmpty()){
            res[n++] = que.poll(); 
        }
        return res;
    }
}