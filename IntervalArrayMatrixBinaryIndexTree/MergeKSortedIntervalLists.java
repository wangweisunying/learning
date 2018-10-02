// Merge K Sorted Interval Lists
// Merge K sorted interval lists into one sorted interval list. You need to merge overlapping intervals too.

// Example
// Given

// [
//   [(1,3),(4,7),(6,8)],
//   [(1,2),(9,10)]
// ]
// Return

// [(1,3),(4,8),(9,10)]

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
     * @param intervals: the given k sorted interval lists
     * @return:  the new sorted interval list
     */

     //prority queue 比头
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        Queue<Interval> que = new PriorityQueue<Interval>(new Comparator<Interval>(){
            @Override
            public int compare(Interval it1, Interval it2){
                return it1.start - it2.start;
            }
        });
        for(List<Interval> tmp : intervals){
            for(Interval cur : tmp){
                que.offer(cur);
            }
        }
        List<Interval> res = new ArrayList();
        Interval first = que.poll();
        int s = first.start;
        int e = first.end;

        while(!que.isEmpty()){
            Interval cur = que.poll();
            if(cur.start <= e){
                e = Math.max(cur.end , e);
                continue;
            }
            res.add(new Interval(s , e));
            s = cur.start;
            e = cur.end;
        }
        res.add(new Interval(s , e)); // 最后加一下 
        
        return res;
    }
}