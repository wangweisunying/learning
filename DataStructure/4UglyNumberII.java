// Ugly Number II
// Ugly number is a number that only have factors 2, 3 and 5.

// Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

// Example
// If n=9, return 10.

// Challenge
// O(n log n) or O(n) time.

class Solution {
    /**
     * @param n an integer
     * @return the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        List<Integer> uglys = new ArrayList<Integer>();
        uglys.add(1);
        
        int p2 = 0, p3 = 0, p5 = 0;
        // p2, p3 & p5 share the same queue: uglys

        for (int i = 1; i < n; i++) {
            int lastNumber = uglys.get(i - 1);
            while (uglys.get(p2) * 2 <= lastNumber) p2++;
            while (uglys.get(p3) * 3 <= lastNumber) p3++;
            while (uglys.get(p5) * 5 <= lastNumber) p5++;
            
            uglys.add(Math.min(
                Math.min(uglys.get(p2) * 2, uglys.get(p3) * 3),
                uglys.get(p5) * 5
            ));
        }

        return uglys.get(n - 1);
    }
};

// version 2 O(nlogn) HashMap + Heap
class Solution {
    /**
     * @param n an integer
     * @return the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        // Write your code here
        Queue<Long> Q = new PriorityQueue<Long>();
        HashSet<Long> inQ = new HashSet<Long>();
        Long[] primes = new Long[3];
        primes[0] = Long.valueOf(2);
        primes[1] = Long.valueOf(3);
        primes[2] = Long.valueOf(5);
        for (int i = 0; i < 3; i++) {
            Q.add(primes[i]);
            inQ.add(primes[i]);
        }
        Long number = Long.valueOf(1);
        for (int i = 1; i < n; i++) {
            number = Q.poll();
            for (int j = 0; j < 3; j++) {
                if (!inQ.contains(primes[j] * number)) {
                    Q.add(number * primes[j]);
                    inQ.add(number * primes[j]);
                }
            }
        }
        return number.intValue();
    }
};


public class Solution {
    /**
     * @param n: An integer
     * @return: the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        Queue<Long> que = new PriorityQueue();
        HashSet<Long> set = new HashSet();
        Long cur = Long.valueOf(1);
        que.offer(cur);
        // set.add(cur);
        for(int i = 1 ; i <= n ; i++){
            cur = que.poll();
            if(!set.contains(cur * 2)){
                set.add(cur * 2);
                que.offer(cur * 2);
            }
            if(!set.contains(cur * 3)){
                set.add(cur * 3);
                que.offer(cur * 3);
            }
            if(!set.contains(cur * 5)){
                set.add(cur * 5);
                que.offer(cur * 5);
            }
        }
            
        return cur.intValue();
    }
}






















public class Solution {
    /**
     * @param n: An integer
     * @return: the nth prime number as description.
     */
        public int nthUglyNumber(int n) {
        Queue<Integer> que = new PriorityQueue<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        que.offer(1);
        int cur = 1;
        while (set.size() < n  && !que.isEmpty()) {
            cur = que.poll();
            set.add(cur);
            if (!set.contains(cur * 2)) {
                que.offer(cur * 2);
            }
            if (!set.contains(cur * 3)) {
                que.offer(cur * 3);
            }
            if (!set.contains(cur * 5)) {
                que.offer(cur * 5);
            }
            
        }
        return cur;
    }
}

class Solution {
    /**
     * @param n an integer
     * @return the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        // Write your code here
        Queue<Long> Q = new PriorityQueue<Long>();
        HashSet<Long> inQ = new HashSet<Long>();
        Long[] primes = new Long[3];
        primes[0] = Long.valueOf(2);
        primes[1] = Long.valueOf(3);
        primes[2] = Long.valueOf(5);
        Q.add(Long.valueOf(1));
        inQ.add(Long.valueOf(1));
        Long number = Long.valueOf(1);
        for (int i = 1; i <= n; i++) {
            number = Q.poll();
            for (int j = 0; j < 3; j++) {
                if (!inQ.contains(primes[j] * number)) {
                    Q.add(number * primes[j]);
                    inQ.add(number * primes[j]);
                }
            }
        }
        return number.intValue();
    }
};