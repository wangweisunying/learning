// 809. Expressive Words
// DescriptionHintsSubmissionsDiscussSolution
// Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  
// Here, we have groups, of adjacent letters that 
// are all the same character, and adjacent characters to the group are different. 
//  A group is extended if that group is length 3 or more, so "e" and "o" would be extended in the first example,
//  and "i" would be extended in the second example.  As another example, the groups of "abbcccaaaa" would be "a", "bb", "ccc", and "aaaa"; and "ccc" and "aaaa"
//  are the extended groups of that string.

// For some given string S, a query word is stretchy if it can be made to be equal to S by extending some groups.  
// Formally, we are allowed to repeatedly choose a group (as defined above) of characters c, 
// and add some number of the same character c to it so that the length of the group is 3 or mores.  
// Note that we cannot extend a group of size one like "h" to a group of size two like "hh" -
//  all extensions must leave the group extended - ie., at least 3 characters long.

// Given a list of query words, return the number of words that are stretchy. 

// Example:
// Input: 
// S = "heeellooo"
// words = ["hello", "hi", "helo"]
// Output: 1
// Explanation: 
// We can extend "e" and "o" in the word "hello" to get "heeellooo".
// We can't extend "helo" to get "heeellooo" because the group "ll" is not extended.
// Notes:

// 0 <= len(S) <= 100.
// 0 <= len(words) <= 100.
// 0 <= len(words[i]) <= 100.
// S and all words in words consist only of lowercase letters
// Loop through all words. check(string S, string W) checks if W is stretchy to S.

// In check function, use two pointer:

// If S[i] == W[j, i++, j++
// If S[i - 2] == S[i - 1] == S[i] or S[i - 1] == S[i] == S[i + 1], i++
  
  
  

class Solution {
    public int expressiveWords(String S, String[] words) {
        int ct = 0;
        for(String word : words){
            if(check(S , word)) ct++;
        }
        return ct;
    }
    private boolean check(String s , String w){
        int j = 0;
        for(int i = 0 ; i < s.length() ; i++){
            if(j < w.length() && s.charAt(i) == w.charAt(j)){
                j++;
            }
            //if not match only two condition can pass  mid - 1= mid = mid + 1; mid = mid - 1 = mid - 2
            else if(i > 0 && s.charAt(i)== s.charAt(i - 1) && i < s.length() - 1  && s.charAt(i)==s.charAt(i + 1)){
                continue;
            }
            else if(i > 1 && s.charAt(i) == s.charAt(i - 1) && s.charAt(i - 1) == s.charAt(i - 2)){
                continue;
            }
            else{
                return false;
            }
        }
        return j == w.length();
    }
}