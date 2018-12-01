// 731. My Calendar II
// DescriptionHintsSubmissionsDiscussSolution
// Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.

// Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end),
//  the range of real numbers x such that start <= x < end.

// A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)

// For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.

// Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
// Example 1:
// MyCalendar();
// MyCalendar.book(10, 20); // returns true
// MyCalendar.book(50, 60); // returns true
// MyCalendar.book(10, 40); // returns true
// MyCalendar.book(5, 15); // returns false
// MyCalendar.book(5, 10); // returns true
// MyCalendar.book(25, 55); // returns true
// Explanation: 
// The first two events can be booked.  The third event can be double booked.
// The fourth event (5, 15) can't be booked, because it would result in a triple booking.
// The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
// The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
// the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
// Note:

// The number of calls to MyCalendar.book per test case will be at most 1000.
// In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].



//思路同sweep line ，都需要遍历 整个map;
//much faster solution ;
class MyCalendarTwo {
    TreeMap<Integer , Integer> map;
    public MyCalendarTwo() {
        map = new TreeMap();
    }

    public boolean book(int start, int end) {
        map.put(start , map.getOrDefault(start , 0 ) + 1);
        map.put(end , map.getOrDefault(end , 0 ) - 1);
        int ct= 0;
        for(int i : map.values()){
            ct += i;
            if(ct > 2){
                map.put(start , map.getOrDefault(start , 0 ) - 1);
                map.put(end , map.getOrDefault(end , 0 ) + 1);
                return false;
            }
        }
        return true;
    }
}






//sweep line 
class MyCalendarTwo {
    class Pair implements Comparable<Pair>{
        int time;
        boolean flag;
        Pair(int time , boolean flag){
            this.time = time;
            this.flag = flag;
        }
        @Override
        public int compareTo(Pair other){
            if(this.time == other.time){
                if(!(this.flag && other.flag)){
                    if(!this.flag){
                        return -1;
                    }
                    else{
                        return 1;
                    }
                     
                }
            }
            return this.time - other.time;
        }
    }
    List<Pair> list;
    public MyCalendarTwo() {
        list = new ArrayList();
    }

    public boolean book(int start, int end) {
        Pair p1  =new Pair(start , true);
        Pair p2 = new Pair(end , false); 
        
        list.add(p1);
        list.add(p2);
        
        Collections.sort(list);
        
        int ct= 0;
        for(Pair p : list ){
            if(p.flag){
                ct++;
            }
            else{
                ct--;
            }
            if(ct > 2){
                list.remove(p1);
                list.remove(p2);
                return false;
            }
        }
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */


/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
