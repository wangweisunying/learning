// 777. Swap Adjacent in LR String
// DescriptionHintsSubmissionsDiscussSolution
// In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX",
//  or replacing one occurrence of "RX" with "XR". 
// Given the starting string start and the ending string end,
//  return True if and only if there exists a sequence of moves to transform one string to the other.

// Example:

// Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
// Output: True
// Explanation:
// We can transform start to end following these steps:
// RXXLRXRXL ->
// XRXLRXRXL ->
// XRLXRXRXL ->
// XRLXXRRXL ->
// XRLXXRRLX


RXXLRXRXL
XRLXXRRLX
// treat 'X' as empty slot L face left and only move left, R face right can only move right,
//L can not move right , and R can not move  left

// Note:

// 1 <= len(start) = len(end) <= 10000.
// Both start and end will only consist of characters in {'L', 'R', 'X'}.
// "XXRXLXRXXX"
// "XXRLXXXXXR"

class Solution {
    public boolean canTransform(String start, String end) {
        int s = 0 , e = 0;
        while(s < start.length() && e < end.length()){
            while(s < start.length() && start.charAt(s) == 'X'){
                s++;
            }
            while(e < end.length() && end.charAt(e) == 'X'){
                e++;
            }
            if(s == start.length() && e == end.length()) return true;
            if(s == start.length() || e == end.length()) return false;
            if(start.charAt(s) != end.charAt(e)) return false;
            if(start.charAt(s) == 'L' && s < e) return false;
            if(start.charAt(s) == 'R' && s > e) return false;
            s++;
            e++;
        }
        return true;
    }
}
















class Solution {
    public boolean canTransform(String s, String e) {
        int n = s.length();
        int i = 0 , j = 0;
        while( i < n && j < n ){
            while( i < n && s.charAt(i) == 'X'){
                i++;
            } 
            while( j < n && e.charAt(j) == 'X'){
                j++;
            }
            if((i == n && j < n) || ( i < n && j == n)){
                return false;
            }
            if(i == n && j == n){
                return true;
            }
            if(s.charAt(i) != e.charAt(j) || (s.charAt(i) == 'L' && i < j) || (s.charAt(i) == 'R' && i > j)){
                return false;
            }
            i++;
            j++;

        }
        return true;
    }
}




class Solution {
    public boolean canTransform(String start, String end) {
        int N = start.length();
        char[] S = start.toCharArray(), T = end.toCharArray();
        int i = -1, j = -1;
        while (++i < N && ++j < N) {
            while (i < N && S[i] == 'X') i++;
            while (j < N && T[j] == 'X') j++;
            /* At this point, i == N or S[i] != 'X',
               and j == N or T[j] != 'X'.  i and j
               are the indices representing the next
               occurrences of non-X characters in S and T.
            */

            // If only one of i < N and j < N, then it isn't solid-
            // there's more people in one of the strings.
            if ((i < N) ^ (j < N)) return false;

            if (i < N && j < N) {
                // If the person isn't the same, it isn't solid.
                // Or, if the person moved backwards, it isn't accessible.
                if (S[i] != T[j] || (S[i] == 'L' && i < j) ||
                        (S[i] == 'R' && i > j) )
                    return false;
            }
        }
        return true;
    }
}