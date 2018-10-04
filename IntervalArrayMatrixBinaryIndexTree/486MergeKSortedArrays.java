
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
    
    class Point{
        int index , val;
        Point(int index , int val){
            this.index = index;
            this.val = val;
        }
    }
    

    public int[] mergekSortedArrays(int[][] arrays) {
        int[] index = new int[arrays.length];
        int ct = 0;
        Queue<Point> que = new PriorityQueue(new Comparator<Point>(){
            @Override
            public int compare(Point p1 , Point p2){
                return p1.val - p2.val;
            }
        });
        for(int i = 0 ; i < arrays.length ; i ++){
            if(arrays[i].length != 0){
                que.offer(new Point( i , arrays[i][0]));
                ct += arrays[i].length;
                index[i] = 1;  
            }
        }
        int[] res = new int[ct];
        int res_index = 0;
        while(!que.isEmpty()){
            Point cur = que.poll();
            res[res_index++] = cur.val;
            
            if(index[cur.index] < arrays[cur.index].length){
                que.offer(new Point( cur.index , arrays[cur.index][index[cur.index]++]));   
            }
            
        }
        return res;
    }
}






































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