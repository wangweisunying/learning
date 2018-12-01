// Given a column title as appear in an Excel sheet, return its corresponding column number.

// For example:

//     A -> 1
//     B -> 2
//     C -> 3
//     ...
//     Z -> 26
//     AA -> 27
//     AB -> 28 
//     ...
// Example 1:

// Input: "A"
// Output: 1
// Example 2:

// Input: "AB"
// Output: 28
// Example 3:

// Input: "ZY"
// Output: 701

class Solution {
    public int titleToNumber(String s) {
        char[] arr = s.toCharArray();
        int res = 0;
        for(int i = 0 ; i < arr.length ; i++){
            res += (arr[i] - '@') * Math.pow(26 , arr.length - 1 - i);
        }
        return res;
    }
}


public static String transferIntgerToString(int n) throws Exception{
        if(n < 1){
            throw new Exception("n can not be less than 1!");
        }
        // write from bottom 
        String res = "";
        while( n > 26){
            if(n % 26 == 0){
                res = 'Z' + res;
                n = n / 26 - 1;
            }
            else{
                res = (char)(n % 26 + 64) + res;
                n = n / 26;
            }
        }
        res = (char)(n + 64) + res;
        return res;
    }