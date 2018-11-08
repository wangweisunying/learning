
// 356. Line Reflection
// DescriptionHintsSubmissionsDiscussSolution
// Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

// Example 1:

// Input: [[1,1],[-1,1]]
// Output: true
// Example 2:

// Input: [[1,1],[-1,-1]]
// Output: false
// Follow up:
// Could you do better than O(n2) ?

// 如果需要重写 自然要想到 Hash<String> 的方法

class Solution {
 
    public boolean isReflected(int[][] points) {
        HashSet<String> set = new HashSet();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int[] point : points){
            max = Math.max(max , point[0]);
            min = Math.min(min , point[0]);
            set.add(point[0] + "_" + point[1]);
        }
        int sum = max + min;
        for(int[] point : points){
            String now = sum - point[0] + "_" + point[1];
            if(!set.contains(now)){
                return false;
            } 
        }
        return true;
    }
}








   public boolean isReflected(int[][] points) {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    HashSet<String> set = new HashSet<>();
    for(int[] p:points){
        max = Math.max(max,p[0]);
        min = Math.min(min,p[0]);
        String str = p[0] + "a" + p[1];
        set.add(str);
    }
    int sum = max+min;
    for(int[] p:points){
        //int[] arr = {sum-p[0],p[1]};
        String str = (sum-p[0]) + "a" + p[1];
        if( !set.contains(str))
            return false;
        
    }
    return true;
}






class Solution {
    class Point{
        int[] axis;
        Point(int[] axis){
            this.axis = axis;
        }
        
        @Override
        public int hashCode(){
            return axis[0] * 999999 + axis[1];
        }
        @Override 
        public boolean equals(Object o1){
            Point x = (Point)o1;
            return this.axis[0] == x.axis[0] && this.axis[1] == x.axis[1];
        }
    }
    
    public boolean isReflected(int[][] points) {
        if(points == null || points.length < 2){
            return true;
        }
        
        Arrays.sort(points , new Comparator<int[]>(){
            @Override 
            public int compare(int[] arr1 , int[] arr2){
                return arr1[0] - arr2[0];
            }
        });

        HashSet<Point> set = new HashSet();
        for(int[] point : points){
            set.add(new Point(point));
        }
        
        float mid = (points[0][0] + points[points.length - 1][0]) / 2.0f;
    
        for(int[] point : points){
            float x = point[0] > mid ? mid - Math.abs(point[0] - mid) :  mid + Math.abs(point[0] - mid); 
            if(!set.contains(new Point( new int[]{(int)x , point[1]}))){
                return false;
            }
        }

        
        
        return true;

    }
}