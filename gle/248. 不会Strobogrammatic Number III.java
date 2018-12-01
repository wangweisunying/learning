// 248. Strobogrammatic Number III
// DescriptionHintsSubmissionsDiscussSolution
// A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

// Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

// Example:

// Input: low = "50", high = "100"
// Output: 3 
// Explanation: 69, 88, and 96 are three strobogrammatic numbers.
// Note:
// Because the range might be a large number, the low and high numbers are represented as string.
// Construct char arrays from low.length() to high.length()

// Add stro pairs from outside

// When left > right, add eligible count


//dfs find all the possible combination using palindromic{{'0' , '0'} , {'1','1'} , {'6','9'} , {'9','6'} , {'8','8'}}) lenght between low.len, high.len

// use String.compareTo(String) to filter the values lessthan low and higher than high , 

//focus on the conercases when 6 , 9 in the middle , and 0 in the very front when len > 1;


class Solution {
    int ct = 0;
    public int strobogrammaticInRange(String low, String high) {
        for(int len = low.length() ; len <= high.length() ; len++){
            char[] c = new char[len];
            dfs(low ,high , c , 0 , len - 1 , new char[][]{{'0' , '0'} , {'1','1'} , {'6','9'} , {'9','6'} , {'8','8'}});
        }
        return ct;
    }
    private void dfs(String low , String high , char[] c , int s, int e , char[][] pairs){
        if(s > e){
            String cur = new String(c);
            if((cur.length() == low.length() && cur.compareTo(low) < 0) ||
               (cur.length() == high.length() && cur.compareTo(high) > 0) 
            ){
                return;
            }
            ct++;
            return;
        }
        
        for(char[] p : pairs){
            // 排除 0开头
            if(s == 0 && c.length != 1 && p[0] == '0'){
                continue;
            }
            //排除‘6’ ‘9’在当中的情况
            if(e == s && p[0] != p[1]){
                continue;
            }
            c[s] = p[0];
            c[e] = p[1];
            dfs(low , high , c , s + 1 , e - 1 , pairs);
        }
    }
}














private static final char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

public int strobogrammaticInRange(String low, String high) {
    int[] count = {0};
    for (int len = low.length(); len <= high.length(); len++) {
        char[] c = new char[len];
        dfs(low, high, c, 0, len - 1, count);
    }
    return count[0];
}

public void dfs(String low, String high , char[] c, int left, int right, int[] count) {
    if (left > right) {
        String s = new String(c);
        if ((s.length() == low.length() && s.compareTo(low) < 0) ||
            (s.length() == high.length() && s.compareTo(high) > 0)) {
            return;
        }
        count[0]++;
        return;
    }
    for (char[] p : pairs) {
        c[left] = p[0];
        c[right] = p[1];
        if (c.length != 1 && c[0] == '0') {
            continue;
        }
        if (left == right && p[0] != p[1]) {
            continue;
        }
        dfs(low, high, c, left + 1, right - 1, count);
    }
}