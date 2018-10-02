
// Reverse Words in a String II
// Given an input character array, reverse the array word by word. A word is defined as a sequence of non-space characters.

// The input character array does not contain leading or trailing spaces and the words are always separated by a single space.

// Example
// Given s = "the sky is blue",
// after reversing : "blue is sky the"





public class Solution {
    /**
     * @param str: a string
     * @return: return a string
     */
    public char[] reverseWords(char[] str) {
        String s = new String(str);
        String[] ss = s.split(" ");
        int i = 0 , j = ss.length - 1;
        while(i < j){
            String tmp = ss[i];
            ss[i] = ss[j];
            ss[j] = tmp;
            i++;
            j--;
        }
        StringBuilder sb = new StringBuilder();
        for(String xx : ss){
            sb.append(xx + " ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString().toCharArray();
    }
}