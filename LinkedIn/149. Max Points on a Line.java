// 149. Max Points on a Line

// Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

// Example 1:

// Input: [[1,1],[2,2],[3,3]]
// Output: 3
// Explanation:
// ^
// |
// |        o
// |     o
// |  o  
// +------------->
// 0  1  2  3  4
// Example 2:

// Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
// Output: 4
// Explanation:
// ^
// |
// |  o
// |     o        o
// |        o
// |  o        o
// +------------------->
// 0  1  2  3  4  5  6
  /*
     *  A line is determined by two factors,say y=ax+b
     *  
     *  If two points(x1,y1) (x2,y2) are on the same line(Of course). 

     *  Consider the gap between two points.

     *  We have (y2-y1)=a(x2-x1),a=(y2-y1)/(x2-x1) a is a rational, b is canceled since b is a constant

     *  If a third point (x3,y3) are on the same line. So we must have y3=ax3+b

     *  Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

     *  Since a is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

     *  So we can use y0&x0 to track a line;
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

//按顺序枚举 每两个点的斜率 用hashmap 计算斜率相同的边  ， + 1 就是点值
public class Solution{
    class Slope{
        int up , down;
        Slope(int up , int down){
            this.up = up;
            this.down = down;
        }
        @Override
        public int hashCode(){
            return up * 999999 + down;
        }
        @Override
        public boolean equals(Object o1){
            Slope s = (Slope)o1;
            return this.up == s.up && this.down == s.down; 
        }
    }


    public int maxPoints(Point[] points) {
        if(points == null || points.length == 0 ){
            return 0;
        }
        if(points.length <= 2){
            return points.length;
        }
        
        int max = 0;
        for(int i = 0 ; i < points.length - 1 ; i++){
            int dup = 0; 
            Map<Slope, Integer> map = new HashMap();
            for(int j = i + 1; j < points.length ; j++){
                if(points[i].x == points[j].x && points[i].y == points[j].y){
                    dup++;
                    continue;
                }
                Slope slope = getSlope(points[i] , points[j]);
                map.put(slope , map.getOrDefault(slope , 0 ) + 1);
            }

            // 最后求得是点的个数  = 边的个数 + 1；
            if(map.isEmpty()){
                max = Math.max(max , dup);
            }else{
                for(Slope sl : map.keySet()){
                     max = Math.max(max , map.get(sl) + dup);
                }
            }
            
        }
        return max + 1;
    }
    private Slope getSlope(Point p1 , Point p2){
        int xx = p1.x - p2.x;
        int yy = p1.y - p2.y;
        if(xx == 0){
            return new Slope(p1.x , 0);
        }
        if(yy == 0){
            return new Slope(0 , p1.y);
        }
        int gcd = gcd(xx , yy);
        // int up = xx / gcd > 0 ? xx / gcd : -xx/gcd;
        // int down = xx / gcd > 0 ? yy / gcd : -yy/gcd;
        return new Slope(xx / gcd , yy / gcd);
    }
    private int gcd(int x , int y){
        if(x == 0){
            return y;
        }
        return gcd(y % x , x);
    }
}


























    public class Solution{
        public int maxPoints(Point[] points) {
        	if (points==null) return 0;
        	if (points.length<=2) return points.length;
        	
        	Map<Integer,Map<Integer,Integer>> map = new HashMap<Integer,Map<Integer,Integer>>();
        	int result=0;
        	for (int i=0;i<points.length;i++){ 
        		map.clear();
        		int overlap=0,max=0;
        		for (int j=i+1;j<points.length;j++){
        			int x=points[j].x-points[i].x;
        			int y=points[j].y-points[i].y;
        			if (x==0&&y==0){
        				overlap++;
        				continue;
        			}
        			int gcd=generateGCD(x,y);
        			if (gcd!=0){
        				x/=gcd;
        				y/=gcd;
        			}
        			
        			if (map.containsKey(x)){
        				if (map.get(x).containsKey(y)){
        					map.get(x).put(y, map.get(x).get(y)+1);
        				}else{
        					map.get(x).put(y, 1);
        				}   					
        			}else{
        				Map<Integer,Integer> m = new HashMap<Integer,Integer>();
        				m.put(y, 1);
        				map.put(x, m);
        			}
        			max=Math.max(max, map.get(x).get(y));
        		}
        		result=Math.max(result, max+overlap+1);
        	}
        	return result;
        	
        	
        }
        private int generateGCD(int a,int b){
    
        	if (b==0) return a;
        	else return generateGCD(b,a%b);
        	
        }
    }