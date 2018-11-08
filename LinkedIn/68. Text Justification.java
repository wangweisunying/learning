// 68. Text Justification
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

// You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

// Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces 
// than the slots on the right.

// For the last line of text, it should be left justified and no extra space is inserted between words.

// Note:

// A word is defined as a character sequence consisting of non-space characters only.
// Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
// The input array words contains at least one word.
// Example 1:

// Input:
// words = ["This", "is", "an", "example", "of", "text", "justification."]
// maxWidth = 16
// Output:
// [
//    "This    is    an",
//    "example  of text",
//    "justification.  "
// ]
// Example 2:

// Input:
// words = ["What","must","be","acknowledgment","shall","be"]
// maxWidth = 16
// Output:
// [
//   "What   must   be",
//   "acknowledgment  ",
//   "shall be        "
// ]
// Explanation: Note that the last line is "shall be    " instead of "shall     be",
//              because the last line must be left-justified instead of fully-justified.
//              Note that the second line is also left-justified becase it contains only one word.
// Example 3:

// Input:
// words = ["Science","is","what","we","understand","well","enough","to","explain",
//          "to","a","computer.","Art","is","everything","else","we","do"]
// maxWidth = 20
// Output:
// [
//   "Science  is  what we",
//   "understand      well",
//   "enough to explain to",
//   "a  computer.  Art is",
//   "everything  else  we",
//   "do                  "
// ]

// ["This    is    an","of          text"]
// Expected answer
// ["This    is    an","example  of text","justification.  "]

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList();
        
        
        StringBuilder sb = new StringBuilder();
        int ct = 0;
        for(String word : words){
            if(sb.length() == 0){
                ct++;
                sb.append(word);
                continue;
            }
            if(sb.length() + 1 + word.length() <= maxWidth){
                ct++;
                sb.append(" " + word);
                continue;
            }
            if(ct == 0){
                while(sb.length() < maxWidth){
                    sb.append(" ");
                }
            }
            if(ct > 1){
                insertSpace(sb , maxWidth - sb.length());
            }
            res.add(sb.toString());
            sb.setLength(0);
            sb.append(word);
            ct = 1;
        }
        
        while(sb.length() < maxWidth){
            sb.append(" ");
        }
        

        res.add(sb.toString());
        return res;
    }
    private void insertSpace(StringBuilder sb , int ct){
        int index = 0;
        while(ct > 0){
            if(!sb.substring(index).contains(" ")){
                index = 0;
            }
            index = sb.indexOf(" " , index);
            sb.insert(index, " ");
            while(sb.charAt(index) == ' '){
                index ++;
            }
            ct--;
        }
    }
}