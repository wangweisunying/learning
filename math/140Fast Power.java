
// Fast Power
// Calculate the an % b where a, b and n are all 32bit integers.

// Example
// For 231 % 3 = 2

// For 1001000 % 1000 = 0

// Challenge
// O(logn)

public class Solution {
    /**
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        if(n == 0){
            return 1 % b;
        }
        if(n == 1 ){
            return a % b;
        }
        
        long tmp = fastPower(a , b , n / 2);
        tmp = (tmp * tmp) % b; //for tmp * tmp * a out of long bound 
        if(n % 2 == 1){
            return (int)((tmp * a) % b);
        }
        else{
            return (int)tmp;
        }
    }
}


public class Solution {
    /**
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        if (n == 1) {
            return a % b;
        }
        if (n == 0) {
            return 1 % b;
        }
        
        long product = fastPower(a, b, n / 2);
        product = (product * product) % b;
        if (n % 2 == 1) {
            product = (product * a) % b;
        }
        return (int) product;
    
    }
}