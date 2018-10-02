
// Kth Smallest Element in a Sorted Matrix
// Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

// Note that it is the kth smallest element in the sorted order, not the kth distinct element.

// Example
// matrix = [
// [ 1, 5, 9], 
// [10, 11, 13],
// [12, 13, 15]
// ],
// k = 8,

// return 13.

// Notice
// You may assume k is always valid, 1 ≤ k ≤ n2.
[[1,3,5,7,9],[2,4,6,8,10],[11,13,15,17,19],[12,14,16,18,20],[21,22,23,24,25]] 
public class Solution {
    /**
     * @param matrix: List[List[int]]
     * @param k: a integer
     * @return: return a integer
     */
    public int kthSmallest(int[][] matrix, int k) {
        Queue<Integer> que = new PriorityQueue();
        int i = 0 , j;
        for(;i < matrix.length ; i++){
            for(j = 0;j < matrix[0].length ;j++){
                que.offer(matrix[i][j]);
            }
        }
        i = 0;
        while(i < k){
            j = que.poll();
            i++;
        }
        return j;
    }
}