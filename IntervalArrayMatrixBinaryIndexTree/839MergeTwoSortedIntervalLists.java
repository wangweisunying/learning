
// MergeTwoSortedIntervalLists
// Merge two sorted (ascending) lists of interval and return it as a new sorted list.
// The new sorted list should be made by splicing together the intervals of the two lists and sorted in ascending order.

// Example
// Given list1 = [(1,2),(3,4)] and list2 = [(2,3),(5,6)], return [(1,4),(5,6)].

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        int s1 = 0 , s2 = 0;
        List<Interval> list = new ArrayList();
        while(s1 < list1.size() && s2 < list2.size()){
            if(list1.get(s1).start < list2.get(s2).start){
                list.add(list1.get(s1));
                s1++;
            }
            else{
                list.add(list2.get(s2));
                s2++;
            }
        }
        while(s1 < list1.size()){
                list.add(list1.get(s1));
                s1++;
        }
        while(s2 < list2.size()){
                list.add(list2.get(s2));
                s2++;
        }
        List<Interval> res = new ArrayList();
        if(list.size() == 0){
            return res;
        }
        int start = list.get(0).start;
        int end = list.get(0).end;
        for(int i = 1 ; i < list.size() ; i++){
            Interval interval = list.get(i);
            if(interval.start > end){
                res.add(new Interval(start , end));
                start = interval.start;
                end = interval.end;
            }
            if(interval.start <= end){
                end = Math.max(end , interval.end);
            }
        }
        res.add(new Interval(start , end));
        return res;
            
    }
}


























/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        List<Interval> res = new ArrayList();
        List<Interval> list = new ArrayList();
        if(list1.size() == 0 && list2.size() == 0){
            return list;
        }
        int i = 0 , j = 0;
        while(i < list1.size() && j < list2.size()){
            if(list1.get(i).start <= list2.get(j).start){
                res.add(list1.get(i));
                i++;
            }
            else{
                res.add(list2.get(j));
                j++;
            }
        }
        while(i < list1.size()){
            res.add(list1.get(i++));
        }
        while(j < list2.size()){
            res.add(list2.get(j++));
        }
        int s = res.get(0).start , e = res.get(0).end;
        for(int k = 0 ; k < res.size(); k++){
            if( k == res.size() - 1 ){
                list.add(new Interval(s , e));
                break;
            }
            if( e < res.get(k + 1).start){
                list.add(new Interval(s , e));
                s = res.get(k + 1).start;
                e = res.get(k + 1).end;
            }
            else{
                e = Math.max(e , res.get(k + 1).end);
            }
        }
        return list;
    }
}