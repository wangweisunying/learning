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

    class Unit{
        int i;
        Interval interval;
        Unit(int i , Interval interval){
            this.i = i;
            this.interval = interval;
        }


    }
     
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        List<Interval> res = new ArrayList();
        int[] index = new int[intervals.size()];
        // for(int i = 0 ; i < index.length ; i++){
        //     index[i] = intervals.get(i).size();
        // }

        Queue<Unit> que = new PriorityQueue(new Comparator<Unit>(){
            @Override
            public int compare(Unit i1 , Unit i2){
                if(i1.interval.start == i2.interval.start){
                    return i1.interval.end - i2.interval.end;
                }
                return i1.interval.start - i2.interval.start;
            }
        });


        for(int i = 0 ; i < intervals.size() ; i++){
            if(intervals.get(i).size() == 0){
                continue;
            }
            que.offer(new Unit(i,intervals.get(i).get(0)));
            index[i]++;
        }
        
        // boolean first = true;
        int s = que.peek().interval.start;
        int e = que.peek().interval.end;
        while(!que.isEmpty()){
            Unit cur = que.poll();
            if(index[cur.i] < intervals.get(cur.i).size()){
                que.offer(new Unit(cur.i, intervals.get(cur.i).get(index[cur.i])));
                index[cur.i]++;
            }
            // if(first){
            //     s = cur.interval.start;
            //     e = cur.interval.end;
            //     first = false;
            //     continue;
            // }

            if(cur.interval.start > e){
                res.add(new Interval(s , e));
                s = cur.interval.start;
                e = cur.interval.end;
            }
            else{
                e = Math.max(e , cur.interval.end);
            }
            
        }
        res.add(new Interval(s , e));
        return res;
    }
}































class Pair {
    int row, col;
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Solution {
    /**
     * @param intervals: the given k sorted interval lists
     * @return:  the new sorted interval list
     */
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        int k = intervals.size();
        PriorityQueue<Pair> queue = new PriorityQueue<>(
          k,
          new Comparator<Pair>() {
            public int compare(Pair e1, Pair e2) {
                return intervals.get(e1.row).get(e1.col).start - 
                  intervals.get(e2.row).get(e2.col).start;
            }
          }
        );
        
        for (int i = 0; i < intervals.size(); i ++) {
            if (intervals.get(i).size() > 0) {
                queue.add(new Pair(i, 0));
            }
        }
        
        List<Interval> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            result.add(intervals.get(pair.row).get(pair.col));
            pair.col++;
            if (pair.col < intervals.get(pair.row).size()) {
                queue.add(pair);
            }
        }
        
        return merge(result);
    }
    
    private List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals;
        }
        
        List<Interval> result = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval : intervals) {
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        // kickoff the last interval
        result.add(new Interval(start, end));
        
        return result;
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