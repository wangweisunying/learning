// 855. Exam Room
// DescriptionHintsSubmissionsDiscussSolution
// In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

// When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

// Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

 

// Example 1:

// Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
// Output: [null,0,9,4,2,null,5]
// Explanation:
// ExamRoom(10) -> null
// seat() -> 0, no one is in the room, then the student sits at seat number 0.
// seat() -> 9, the student sits at the last seat number 9.
// seat() -> 4, the student sits at the last seat number 4.
// seat() -> 2, the student sits at the last seat number 2.
// leave(4) -> null
// seat() -> 5, the student sits at the last seat number 5.
// ​​​​​​​

// Note:

// 1 <= N <= 10^9
// ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
// Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.



// Very straight forward idea.
// Use a list L to record the index of seats where people sit.

// seat():
// 1. find the biggest distance at the start, at the end and in the middle.
// 2. insert index of seat
// 3. return index

// leave(p): pop out p


// ["ExamRoom","seat","seat","seat","seat","leave","seat"]
// [[10],[],[],[],[],[4],[]]
// Your answer
// [null,0,9,4,9,null,4]
// Expected answer
// [null,0,9,4,2,null,5]

// A few concepts to discover:

// Need to measure the distance between seated students: O(n) is trivial, but not as fast. Use PriorityQueue to store the potential candidate as interval, and also calculate the candidate's mid-distance to both side.
// seat(): pq.poll() to find interval of largest distance. Split and add new intervals back to queue.
// leave(x): one seat will be in 2 intervals: remove both from pq, and merge to a new interval.
// Trick: there is no interval when adding for first student, so we need to create boundary/fake seats [-1, N], which simplifies the edge case a lot. (I spent hours on edge case, and finally saw a smart abstraction using boundary seats).
// Once these concepts are clear, all the rest is busy coding : )
// PriorityQueue






class ExamRoom {
    PriorityQueue<Interval> pq;
    int N;

    class Interval {
        int x, y, dist;
        public Interval(int x, int y) {
            this.x = x;
            this.y = y;
            if (x == -1) {
                this.dist = y;
            } else if (y == N) {
                this.dist = N - 1 - x;
            } else {
                this.dist = Math.abs(x - y) / 2;    
            }
        }
    }

    public ExamRoom(int N) {
        this.pq = new PriorityQueue<>((a, b) -> a.dist != b.dist? b.dist - a.dist : a.x - b.x);
        this.N = N;
        pq.add(new Interval(-1, N));
    }

    // O(logn): poll top candidate, split into two new intervals
    public int seat() {
        int seat = 0;
        Interval interval = pq.poll();
        if (interval.x == -1) seat = 0;
        else if (interval.y == N) seat = N - 1;
        else seat = (interval.x + interval.y) / 2; 
        
        pq.offer(new Interval(interval.x, seat));
        pq.offer(new Interval(seat, interval.y));
            
        return seat;
    }
    
    // O(n)Find head and tail based on p. Delete and merge two ends
    public void leave(int p) {
        Interval head = null, tail = null;
        List<Interval> intervals = new ArrayList<>(pq);
        for (Interval interval : intervals) {
            if (interval.x == p) tail = interval;
            if (interval.y == p) head = interval;
            if (head != null && tail != null) break;
        }
        // Delete
        pq.remove(head);
        pq.remove(tail);
        // Merge
        pq.offer(new Interval(head.x, tail.y));
    }
}




















class ExamRoom {
    List<Integer> list;
    int n;
    public ExamRoom(int N) {
        n = N;
        list = new ArrayList();
    }
    
    public int seat() {
        if(list.size() == 0){
            list.add(0);
            return 0; 
        }
        int index = -1;
        int max = -1;
        if(list.get(0) != 0){
            max = list.get(0) - 0;
            index = 0;     
        }
        for(int i = 1 ; i < list.size() ; i++){
            if(max < (list.get(i) - list.get(i - 1)) / 2){
                max = (list.get(i) - list.get(i - 1)) / 2;
                index = (list.get(i) +  list.get(i - 1)) / 2;
            } 
        }
        if(list.get(list.size() - 1) != n - 1){
            if(max < ( n - 1 - list.get(list.size() - 1))){
                max = n - 1 - list.get(list.size() - 1);
                index = n - 1;
            }
        }
        list.add(index);
        return index;
    }
    
    public void leave(int p) {
        list.remove(list.indexOf(p));
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */