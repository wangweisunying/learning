// Excel Sheet Column Title
// Given a positive integer, return its corresponding column title as appear in an Excel sheet.

// Example
// 1 -> A
// 2 -> B
// 3 -> C
// ...
// 26 -> Z
// 27 -> AA
// 28 -> AB


public class Solution {
    /**
     * @param n: a integer
     * @return: return a string
     */
    public String convertToTitle(int n) {
        String res = "";
        while(n > 26){
            res = (char)(64 + n % 26) + res;
            n = n / 26;
        }
        res = (char)(64 + n % 26) + res;
        return res;
    }
}