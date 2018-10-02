
// My Calendar II
// Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.

// Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

// A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)

// For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.

// Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)

// Example
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



class MyCalendarTwo {
    ArrayList<Course> list ;
    ArrayList<Course> fullList; 
    public MyCalendarTwo() {
        list = new ArrayList();
        fullList = new ArrayList();
    }
    private class Course{
        int x , y ;
         Course(int x , int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public boolean book(int start, int end) {
        for(Course cur : fullList){ 
            if(start >= cur.y || end <= cur.x){
                continue;
            }
            return false;
        }
        list.add(new Course(start , end));
        Collections.sort(list , new Comparator<Course>(){
            @Override
            public int compare(Course c1 , Course c2){
                return c1.x - c2.x;
            }
        });
        // fullList.clear();
        int r = list.get(0).y;
        for(int i  = 1 ; i < list.size() ; i ++){
            if(r > list.get(i).x){
                if(list.get(i).y < r){
                    
                    fullList.add(new Course(list.get(i).x , list.get(i).y));
                }
                else{
                    fullList.add(new Course(list.get(i).x , r));
                }
            }
            r = Math.max(r , list.get(i).y);
        }
        return true;
    }
}


/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */


 MyCalendarTwo()
book(24,40)
book(43,50)
book(27,43)
book(5,21)
book(30,40)
book(14,29)
book(3,19)
book(3,14)
book(25,39)
book(6,19)
Output
[true,true,true,true,true,true,false,true,false,false]
Expected
[true,true,true,true,false,false,true,false,false,false]