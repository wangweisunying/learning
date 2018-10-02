// Pow(x, n)
// Implement pow(x, n).

// Example
// Pow(2.1, 3) = 9.261
// Pow(0, 1) = 0
// Pow(1, 0) = 1
// Challenge
// O(logn) time

// Notice
// You don't need to care about the precision of your answer, it's acceptable if the expected answer and your answer 's difference is smaller than 1e-3.


public class Solution {
    /**
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        if(n == 0){
            return 1;
        }
        if(n == Integer.MIN_VALUE){
            return 0;
        }
        if(n < 0){
            n = -n;
            return 1 / power(x , n); 
        }
        else{
            return power(x , n);
        }
    }
    private double power(double x , int n){
        if(n == 0){
            return 1;
        }
        double tmp = power(x , n / 2);
        if(n % 2 == 1){
            return tmp * tmp * x;
        }
        else{
            return tmp * tmp;
        }

    }
}


public class Solution {
    /*
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        // write your code here
        if (n >= 0) {
            return pow(x, n);
        } else {
            return 1.0 / pow(x, n);
        }
    }
    
    public double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        
        double y = pow(x, n / 2);
        if (n % 2 == 0) {
            return y * y;
        } else {
            return y * y * x;
        }
    }
}