// 1630. Interesting String
// There is now a string s consisting of only numbers and lowercase letters. 
// If the string s is interesting, then s must be split into several substrings,
//  each substring satisfies the beginning of the number, and the number represents 
//  The number of characters after it. For example, s = "4gray6hunter", we can divide it into "4gray" and "6hunter", so the string is interesting.
// If s is an interesting string, output "yes", otherwise output "no"

// Example
// s = "124gray6hunter"
// return "yes"
// s = "31ba2a" 
// return "no"
// Notice
// the length of string no more than 10000


public class Solution {
    /**
     * @param s: the string s
     * @return: check if the string is interesting
     */
    public String check(String s) {
        if(s == null || s.length() == 0){
            return "no";
        }
        int ct = 0;
        boolean isCharacter = false;
        for(int i = 0 ; i < s.length() ; i++ ){
            if(Character.isLetter(s.charAt(i))){
                ct--;
                if(isCharacter){
                    if(ct < 0){
                        return "no";
                    }
                }
                else{ 
                    isCharacter = true;
                }
            }
            else{
                if(isCharacter){
                    if(ct != 0){
                        return "no";
                    }
                    isCharacter = false;
                }
                ct = s.charAt(i) - '0';
            }
            
        }
        return "yes";
    }
}