// 253. Meeting Rooms II
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

// Example 1:

// Input: [[0, 30],[5, 10],[15, 20]]
// Output: 2
// Example 2:

// Input: [[7,10],[2,4]]
// Output: 1


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

 // sweep line
class Solution {
    class Point{
        int time;
        int ct;
        Point(int time , int ct){
            this.time = time;
            this.ct = ct;
        }
    }
    public int minMeetingRooms(Interval[] intervals) {
        List<Point> list = new ArrayList();
        for(Interval i : intervals){
            list.add(new Point(i.start , 1));
            list.add(new Point(i.end , -1));
        }
        Collections.sort(list , (a , b) ->(a.time == b.time ? (a.ct - b.ct) : (a.time - b.time)));
        int res = 0 , cur = 0;
        for(Point p : list){
            cur += p.ct;
            res = Math.max(cur , res);
        }
        return res;
    }
}
