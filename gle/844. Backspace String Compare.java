// 844. Backspace String Compare
// DescriptionHintsSubmissionsDiscussSolution
// Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

// Example 1:

// Input: S = "ab#c", T = "ad#c"
// Output: true
// Explanation: Both S and T become "ac".
// Example 2:

// Input: S = "ab##", T = "c#d#"
// Output: true
// Explanation: Both S and T become "".
// Example 3:

// Input: S = "a##c", T = "#a#c"
// Output: true
// Explanation: Both S and T become "c".
// Example 4:

// Input: S = "a#c", T = "b"
// Output: false
// Explanation: S becomes "c" while T becomes "b".
// Note:

// 1 <= S.length <= 200
// 1 <= T.length <= 200
// S and T only contain lowercase letters and '#' characters.
// Follow up:

// Can you solve it in O(N) time and O(1) space?


"ab##"
"c#d#"
//坑很多！
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int indexS = S.length() - 1;
        int indexT = T.length() - 1;
        int ctS = 0 , ctT = 0 ;
        while(indexS >= 0 || indexT >= 0){
            while(indexS >= 0){
                if(S.charAt(indexS) == '#'){
                    ++ctS;
                }
                else if(ctS > 0){
                    --ctS;
                }
                else{
                    break;
                }
                --indexS;
            }
            while(indexT >= 0){
                if(T.charAt(indexT) == '#'){
                    ++ctT;
                }
                else if(ctT > 0){
                    --ctT;
                }
                else{
                    break;
                }
                --indexT;
            }
            if(indexS >= 0 && indexT >= 0){
                if(S.charAt(indexS) != T.charAt(indexT))return false;
            }
            // if char expect nothing return false;
            if((indexS < 0 && indexT >= 0) || (indexS >= 0 && indexT < 0)) return false;
            --indexS;
            --indexT;
        }
        return true;
    }
}



class Solution {
    public boolean backspaceCompare(String S, String T) {
        int indexS = S.length() - 1;
        int indexT = T.length() - 1;
        int ctS = 0 , ctT = 0 ;
        int preSIndex  = -5 , preTIndex = -5;
        while(indexS >= 0 || indexT >= 0){
            while(indexS >= 0 && S.charAt(indexS) == '#'){
                while(indexS >= 0 && S.charAt(indexS) == '#'){
                    ++ctS;
                    --indexS;
                }
                while(indexS >= 0 && ctS > 0){
                    if(S.charAt(indexS) == '#')++ctS;
                    else --ctS;
                    --indexS;
                }
            }
            while(indexT >= 0 && T.charAt(indexT) == '#'){
                while(indexT >= 0 && T.charAt(indexT) == '#'){
                    ++ctT;
                    --indexT;
                }
                while(indexT >= 0 && ctT > 0){
                    if(T.charAt(indexT) == '#')++ctT;
                    else --ctT;
                    --indexT;
                }
            }
            if(indexS >= 0 && indexT >= 0){
                if(S.charAt(indexS) == T.charAt(indexT)){
                    --indexS;
                    --indexT;
                }
                else return false;
            }
            else if(preSIndex == indexS && preTIndex == indexT) return false;
            preSIndex = indexS;
            preTIndex = indexT;
        }
        return true;
    }
}



class Solution {
    //从后往前想
    public boolean backspaceCompare(String S, String T) {
     int i=S.length()-1;
     int j= T.length()-1;
     int skipi = 0;
     int skipj = 0;
     
     while(i>=0 || j >=0){
         while(i>=0){
             if(S.charAt(i)=='#'){
                 skipi++;
                 i--;
             }else if(skipi>0){
                 i--;
                 skipi--;
             }else{
                 break;
             }
         }
         
         while(j>=0){
             if(T.charAt(j)=='#'){
                 skipj++;
                 j--;
             }else if(skipj>0){
                 j--;
                 skipj--;
             }else{
                 break;
             }
         }
         
         if(i>=0 && j >=0 && S.charAt(i)!=T.charAt(j)){
             return false;
         }
         if ((i >= 0) != (j >= 0)){
                // ex: S = "bxj##tw"; T = "bxj###tw";  i becomes 0 and j becomes -1
                return false;
         }
         i--;j--;
     }
    return true;     
 }
}



class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
            while (i >= 0) { // Find position of next possible char in build(S)
                if (S.charAt(i) == '#') {skipS++; i--;}
                else if (skipS > 0) {skipS--; i--;}
                else break;
            }
            while (j >= 0) { // Find position of next possible char in build(T)
                if (T.charAt(j) == '#') {skipT++; j--;}
                else if (skipT > 0) {skipT--; j--;}
                else break;
            }
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
                return false;
            i--; j--;
        }
        return true;
    }
}