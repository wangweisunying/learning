// 57. Insert Interval
// DescriptionHintsSubmissionsDiscussSolution
// Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

// You may assume that the intervals were initially sorted according to their start times.

// Example 1:

// Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
// Output: [[1,5],[6,9]]
// Example 2:

// Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
// Output: [[1,2],[3,10],[12,16]]
// Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


1
/**
2
 * Definition for an interval.
3
 * public class Interval {
4
 *     int start;
5
 *     int end;
6
 *     Interval() { start = 0; end = 0; }
7
 *     Interval(int s, int e) { start = s; end = e; }
8
 * }
9
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

//  [[1,3],[6,9]]
// [2,5]
// Output
// [[1,3],[6,9]]
// Expected
// [[1,5],[6,9]]
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(newInterval == null) return intervals;
        List<Interval> res = new ArrayList();
        for(int i = 0 ; i < intervals.size() ; i++){
            Interval cur = intervals.get(i);
            if(cur.start > newInterval.end){
                res.add(newInterval);
                while(i < intervals.size()) res.add(intervals.get(i++));
                return res;
            } 
            else if(cur.end < newInterval.start) res.add(cur);
            else{
                newInterval.start = Math.min(newInterval.start , cur.start);
                newInterval.end = Math.max(newInterval.end , cur.end);
            }
        }
        res.add(newInterval);
        return res;
    }
}


















class Solution {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res =new ArrayList();
        if(intervals == null || intervals.size() == 0){
            res.add(newInterval);
            return res;
        }
        int i = 0;
        while(i < intervals.size() && intervals.get(i).end < newInterval.start){
            res.add(intervals.get(i++));
        }
        
        while(i < intervals.size() && intervals.get(i).start <= newInterval.end){//相交
            newInterval.start = Math.min(newInterval.start , intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end , intervals.get(i).end);
            i++;
        }
        res.add(newInterval);
        while(i < intervals.size()){
            res.add(intervals.get(i++));
        }
        return res;
    }
}
public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> result = new LinkedList<>();
    int i = 0;
    // add all the intervals ending before newInterval starts
    while (i < intervals.size() && intervals.get(i).end < newInterval.start)
        result.add(intervals.get(i++));
    // merge all overlapping intervals to one considering newInterval
    while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
        newInterval = new Interval( // we could mutate newInterval here also
                Math.min(newInterval.start, intervals.get(i).start),
                Math.max(newInterval.end, intervals.get(i).end));
        i++;
    }
    result.add(newInterval); // add the union of intervals we got  or add original newInterval when intervals.get(i).start > newInterval.end
    // add all the rest
    while (i < intervals.size()) result.add(intervals.get(i++)); 
    return result;
}




class Solution {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList();
        if(intervals == null || intervals.size() == 0){
            res.add(newInterval);
            return res;
        }
        if(newInterval.end < intervals.get(0).start){
            res.add(newInterval);
            res.addAll(intervals);
            return res;
        }
        if(newInterval.start > intervals.get(intervals.size() - 1).end){
            intervals.add(newInterval);
            return intervals;
        }


        //一定要注意二分条件！！！！
        int index = bs(intervals , newInterval.start);

        for(int i = 0 ; i < index ; i++){
            res.add(intervals.get(i));
        }
        int start = newInterval.start;
        int end = newInterval.end;
        if(intervals.get(index).end < start){
            res.add(intervals.get(index));    
        }
        else{
            start = Math.min(intervals.get(index).start , start);
            end = Math.max(intervals.get(index).end , end);
        }

        for(int i = index + 1 ; i < intervals.size() ; i++){
            if(intervals.get(i).start > end){
                res.add(new Interval(start , end));
                start = intervals.get(i).start;
                end = intervals.get(i).end;
            }
            else{
                end = Math.max(end , intervals.get(i).end);
            }
        }
        res.add(new Interval(start , end));
        return res;
    }
    private int bs(List<Interval> intervals , int target){
        int s = 0 , e = intervals.size() - 1;
        while(s + 1 < e){
            int mid = (s + e) / 2;
            if(intervals.get(mid).start == target){
                return mid;
            }
            if(intervals.get(mid).start < target){
                s = mid;
            }
            else{
                e = mid;
            }
        }
        if(intervals.get(e).start <= target){
            return e;
        }
        return s;
    }

}



