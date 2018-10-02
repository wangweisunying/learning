// Count and Say
// The count-and-say sequence is the sequence of integers beginning as follows:

// 1, 11, 21, 1211, 111221, ...

// 1 is read off as "one 1" or 11.

// 11 is read off as "two 1s" or 21.

// 21 is read off as "one 2, then one 1" or 1211.

// Given an integer n, generate the nth sequence.

// Example
// Given n = 5, return "111221".

public class Solution {
    /**
     * @param n: the nth
     * @return: the nth sequence
     */
    public String countAndSay(int n) {
        String res = "1";
        if(n == 1){
            return res;
        }
        for(int i = 2; i <= n ; i ++){
            res = count(res);
        }
        return res;
    }
    private String count(String s){
        String res = "";
        char tmp = s.charAt(0);
        int ct = 1;
        for(int i = 1 ; i < s.length() ;i++ ){
            if(tmp == s.charAt(i)){
                ct++;
                continue;
            }
            res += String.valueOf(ct) + tmp; 
            tmp = s.charAt(i);
            ct = 1;
        }
        res += String.valueOf(ct) + tmp; 
        return res;
    }
}



