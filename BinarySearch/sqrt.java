
//  Sqrt(x)
// Implement int sqrt(int x).

// Compute and return the square root of x.

// Example
// sqrt(3) = 1

// sqrt(4) = 2

// sqrt(5) = 2

// sqrt(10) = 3

// Challenge
// O(log(x))

public class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        long s = 1 , e = x ;
        while(s + 1 < e){
            long mid = (s + e) / 2;
            if(mid * mid <= x){
                s = mid;
            }
            else{
                e = mid;
            }
        }
        if( e * e <= x){
            return (int)e;
        }
        else{
            return (int)s;
        }
    }
}