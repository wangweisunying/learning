// Rectangle Overlap
// Given two rectangles, find if the given two rectangles overlap or not.

// Example
// Given l1 = [0, 8], r1 = [8, 0], l2 = [6, 6], r2 = [10, 0], return true

// Given l1 = [0, 8], r1 = [8, 0], l2 = [9, 6], r2 = [10, 0], return ``false`

// Notice
// l1: Top Left coordinate of first rectangle.
// r1: Bottom Right coordinate of first rectangle.
// l2: Top Left coordinate of second rectangle.
// r2: Bottom Right coordinate of second rectangle.

// l1 != r2 and l2 != r2



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
     * @param l1: top-left coordinate of first rectangle
     * @param r1: bottom-right coordinate of first rectangle
     * @param l2: top-left coordinate of second rectangle
     * @param r2: bottom-right coordinate of second rectangle
     * @return: true if they are overlap or false
     */
    public boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        Point[] rec1 = new Point[4];
        Point[] rec2 = new Point[4];
        rec1[0] = l1;
        rec1[1] = r1;
        rec1[2] = new Point(l1.x , r1.y);
        rec1[3] = new Point(r1.x , l1.y);

        rec2[0] = l2;
        rec2[1] = r2;
        rec2[2] = new Point(l2.x , r2.y);
        rec2[3] = new Point(r2.x , l2.y);
        int i;
        if((l1.x > l2.x && r1.x < r2.x && l2.y < l1.y && r2.y > r1.y) || (l2.x > l1.x && r2.x < r1.x && l1.y < l2.y && r1.y > r2.y)  ){
            return true;
        }


        for(i = 0 ; i < 4 ; i++){
            if( rec1[i].x >= l2.x && rec1[i].x <= r2.x && rec1[i].y >= r2.y && rec1[i].y <= l2.y){
                return true;
            }
            
            if( rec2[i].x >= l1.x && rec2[i].x <= r1.x && rec2[i].y >= r1.y && rec2[i].y <= l1.y){
                return true;
            }

        }
        return false;
        
    }
}