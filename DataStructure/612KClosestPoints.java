// K Closest Points
// Given some points and a point origin in two dimensional space, find k points out of the some points which are nearest to origin.
// Return these points sorted by distance, if they are same with distance, sorted by x-axis, otherwise sorted by y-axis.

// Example
// Given points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3
// return [[1,1],[2,5],[4,4]]


/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Point[] res = new Point[k];
        Queue<Point> que = new PriorityQueue(new Comparator<Point>(){
            @Override
            public int compare(Point p1 , Point p2){
                if((p1.x - origin.x)*(p1.x - origin.x) + (p1.y - origin.y)*(p1.y - origin.y) ==
                   (p2.x - origin.x)*(p2.x - origin.x) + (p2.y - origin.y)*(p2.y - origin.y)){
                    return p1.x + p1.y - p2.x - p2.y;       
                } 
                return (p1.x - origin.x)*(p1.x - origin.x) + (p1.y - origin.y)*(p1.y - origin.y) -
                   (p2.x - origin.x)*(p2.x - origin.x) - (p2.y - origin.y)*(p2.y - origin.y);
            }
        });
        for(Point p : points){
            que.offer(p);
        }
        int i = 0;
        while(i < k){
            res[i++] = que.poll();
        }
        return res;
    }
}




/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * } * Definition for a point.
 *
 */

 
// PriorityQueue 里从远到近排序。当 PQ 里超过 k 个的时候，就 pop 掉一个。 更加节省空间

public class Solution {
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Point[] res = new Point[k];
        TreeSet<Point> set = new TreeSet(new Comparator<Point>(){
            @Override
            public int compare(Point p1 , Point p2){
                double dis_dif = getdis(p1 , origin) - getdis(p2 , origin);
            
                if( dis_dif > 0){
                    return 1;
                }
                else if( dis_dif == 0){
                    if(p1.x + p1.y > p2.x + p2.y){
                        return 1;
                    }
                    return -1;
                }
                else{
                    return -1;
                }
            }
        });
        for(Point p : points){
            set.add(p);
        }
        Iterator it = set.iterator();
        for(int i = 0 ; i < k ; i ++){
            res[i] = (Point)it.next();
        }
        return res;
    }
    private double getdis(Point p1 , Point p2){
        return Math.sqrt(Math.pow((p1.x - p2.x) , 2) + Math.pow((p1.y - p2.y) , 2));
    }
}


// PriorityQueue 里从远到近排序。当 PQ 里超过 k 个的时候，就 pop 掉一个。 更加节省空间

// compare to 
public class Solution {
    /**
     * @param points a list of points
     * @param origin a point
     * @param k an integer
     * @return the k closest points
     */
    private Point global_origin = null;
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // Write your code here
        global_origin = origin;
        PriorityQueue<Point> pq = new PriorityQueue<Point> (k, new Comparator<Point> () {
            @Override
            public int compare(Point a, Point b) {
                int diff = getDistance(b, global_origin) - getDistance(a, global_origin);
                if (diff == 0)
                    diff = b.x - a.x;
                if (diff == 0)
                    diff = b.y - a.y;
                return diff;
            }
        });

        for (int i = 0; i < points.length; i++) {
            pq.offer(points[i]);
            if (pq.size() > k)
                pq.poll();
        }
        
        k = pq.size();
        Point[] ret = new Point[k];  
        while (!pq.isEmpty())
            ret[--k] = pq.poll();
        return ret;
    }
    
    private int getDistance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}


[[4,6],[4,6],[4,6],[-4,-6],[-4,6]]
[0,0]
3
Output
[[4,6],[4,6],[4,6]]
Expected
[[-4,-6],[-4,6],[4,6]]

public class Solution {
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        HashMap< Integer , List<Point>> map = new HashMap();
        int[] nums = new int[points.length];
        for(int i = 0 ; i < nums.length ; i++){
            int x = points[i].x - origin.x;
            int y = points[i].y - origin.y;
            nums[i] = x * x + y * y;
            if(map.containsKey(nums[i])){
                map.get(nums[i]).add(points[i]);
            }
            else{
                map.put(nums[i] , new ArrayList(Arrays.asList(points[i])));
            }
              
        }
        Point[] res = new Point[k];
        partition(nums , 0 , nums.length - 1 , k - 1);
        
        int i = 0;
        int j = 0;
        while(j < k){
            List<Point> list = map.get(nums[i++]);
            for(Point p : list){
                if(j < k){
                    res[j++] = p;
                }
                else{
                    break;
                }
            }
        }
        return res;
    }
    private void partition(int[] nums , int s , int e , int k){
        if(s >= e){
            return;
        }
        int l = s , r = e , pivot = nums[(s + e) / 2];
        while(l <= r){
            while(l <= r && nums[l] < pivot){
                l++;
            }
            while(l <= r && nums[r] > pivot){
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
        if( r >= k){
            partition(nums , s , r , k);
        }
        if( l <= k){
            partition(nums, l ,e , k);
        }
    }
}
