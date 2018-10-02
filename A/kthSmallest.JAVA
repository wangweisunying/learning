//  Kth Smallest Number in Sorted Matrix
// Find the kth smallest number in at row and column sorted matrix.

// Example
// Given k = 4 and a matrix:

// [
//   [1 ,5 ,7],
//   [3 ,7 ,8],
//   [4 ,8 ,9],
// ]
// return 5

// Challenge
// Solve it in O(k log n) time where n is the bigger one between row size and column size.


public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
    
    public int kthSmallest(int[][] matrix, int k) {
        Queue<int[]> que = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int[] arr1 = (int[]) o1;
                int[] arr2 = (int[]) o2;
                return arr1[2] - arr2[2];
            }
        });
        int[] deltaX = new int[]{1, 0};
        int[] deltaY = new int[]{0, 1};
        boolean[][] visited  = new boolean[matrix.length][matrix[0].length];
        que.offer(new int[]{0, 0, matrix[0][0]});
        visited[0][0] = true;
        while (!que.isEmpty()) {
            
            int[] cur = que.poll();
            k--;
            System.out.println(Arrays.toString(cur));
            if (k == 0) {
                return cur[2];
            }
            for (int i = 0; i < 2; i++) {
                int x = cur[0] + deltaX[i];
                int y = cur[1] + deltaY[i];
                if (outBound(x, y, matrix.length, matrix[0].length)) {
                    continue;
                }
                if( visited[x][y]){
                    continue;
                }
                visited[x][y] = true;
                que.offer(new int[]{x , y ,matrix[x][y]});
            }
        }
        return -1;
    }
    private boolean outBound(int x, int y, int m, int n) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return true;
        }
        return false;
    }
}