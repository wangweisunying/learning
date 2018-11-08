// 418. Sentence Screen Fitting
// DescriptionHintsSubmissionsDiscussSolution
// Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

// Note:

// A word cannot be split into two lines.
// The order of words in the sentence must remain unchanged.
// Two consecutive words in a line must be separated by a single space.
// Total words in the sentence won't exceed 100.
// Length of each word is greater than 0 and won't exceed 10.
// 1 ≤ rows, cols ≤ 20,000.
// Example 1:

// Input:
// rows = 2, cols = 8, sentence = ["hello", "world"]

// Output: 
// 1

// Explanation:
// hello---
// world---

// The character '-' signifies an empty space on the screen.
// Example 2:

// Input:
// rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

// Output: 
// 2

// Explanation:
// a-bcd- 
// e-a---
// bcd-e-

// The character '-' signifies an empty space on the screen.
// Example 3:

// Input:
// rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

// Output: 
// 1

// Explanation:
// I-had
// apple
// pie-I
// had--

// The character '-' signifies an empty space on the screen.
// Input:
// ["hello","leetcode"]
// 1
// 20
// Output:
// 0
// Expected:
// 1

// Explanation:

// Say sentence=["abc", "de", "f], rows=4, and cols=6.
// The screen should look like

// "abc de"
// "f abc "
// "de f  "
// "abc de"
// Consider the following repeating sentence string, with positions of the start character of each row on the screen.














// It's kind of like a jump game. I use a array to record for each word, how far it can jump.
// eg. dp[index] means if the row start at index then the start of next row is dp[index].
// dp[index] can be larger than the length of the sentence, in this case, one row can span multiple sentences.
// I comment the check whether a word is longer than the row since there is no such test case. But it's better to check it. And it make little difference to the speed.

public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] dp = new int[sentence.length];
        int n = sentence.length;
        for(int i = 0, prev = 0, len = 0; i < sentence.length; ++i) {
            // remove the length of previous word and space
            if(i != 0 && len > 0) len -= sentence[i - 1].length() + 1;
            // calculate the start of next line.
            // it's OK the index is beyond the length of array so that 
            // we can use it to count how many words one row has.
            while(len + sentence[prev % n].length() <= cols) len += sentence[prev++ % n].length() + 1;
            dp[i] = prev;
        }
        int count = 0;
        for(int i = 0, k = 0; i < rows; ++i) {
            // count how many words one row has and move to start of next row.
            // It's better to check if d[k] == k but I find there is no test case on it. 
            // if(dp[k] == k) return 0;
            count += dp[k] - k;
            k = dp[k] % n;
        }
        // divide by the number of words in sentence
        return count / n;
    }
}






// "abc de f abc de f abc de f ..."
//  ^      ^     ^    ^      ^
//  0      7     13   18     25
// Our goal is to find the start position of the row next to the last row on the screen, which is 25 here. Since actually it's the length of everything earlie 
// , we can get the answer by dividing this number by the length of (non-repeated) sentence string. Note that the non-repeated sentence string has a space at the end; it is "abc de f " in this example.

// Here is how we find that position. In each iteration, we need to adjust start based on spaces either added or removed.

// "abc de f abc de f abc de f ..." // start=0
//  012345                          // start=start+cols+adjustment=0+6+1=7 (1 space removed in screen string)
//         012345                   // start=7+6+0=13
//               012345             // start=13+6-1=18 (1 space added)
//                    012345        // start=18+6+1=25 (1 space added)
//                           012345


class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s= String.join(" ", sentence)+" ";
        int i=0, n=s.length();
        for (int r=0; r<rows; r++){
            i+=cols;
            while (i>-1 && s.charAt(i%n)!=' ') i--;
            i++;
        }
        return i/n;
    }
}




//just need the number so calculate the max number a cols can contains the sentence

class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int res = 0;
        int rowCt = 0;
        int curCt = 0;
        
        int sum = 0;
        for(String str : sentence){
            sum += str.length();
        }
        sum += sentence.length;
        
        int addon = cols / sum;
        cols = cols % sum;
        while(rowCt < rows){
            for(String str : sentence){
                if(str.length() > cols){
                    return addon * rows;
                }

                if(curCt == 0){
                    curCt += str.length();
                    continue;
                }
                if(curCt + str.length() + 1 <= cols){
                    curCt+= str.length() + 1;
                }
                else{
                    curCt = str.length();
                    res += addon;
                    rowCt ++;
                    if(rowCt == rows){
                        return res;
                    }
                }
                
            }
            res++;
        }
        return res;
    }
}

