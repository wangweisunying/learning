// 299. Bulls and Cows

// You are playing the following Bulls and Cows game with your friend: 
// You write down a number and ask your friend to guess what the number is. 
// Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly
//  in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows").
//   Your friend will use successive guesses and hints to eventually derive the secret number.

// Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

// Please note that both secret number and friend's guess may contain duplicate digits.

// Example 1:

// Input: secret = "1807", guess = "7810"

// Output: "1A3B"

// Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
// Example 2:

// Input: secret = "1123", guess = "0111"

// Output: "1A1B"

// Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
// Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
// "1120"
// "0111"
// Your answer
// "1A1B"
// Expected answer
// "1A2B"

//maintain a number ct array by using ++ -- from either secret or guess, 
// once char are different , check each secret and guess char if it is appered cow ++ and make it less --  or more ++ depends on the operations
// once appears int each other cows++; 

class Solution {
    public String getHint(String secret, String guess) {
        int bull = 0;
        int cow = 0;
        int[] hash = new int[10];
        for(int i = 0 ; i < guess.length() ; i++){
            if(secret.charAt(i) == guess.charAt(i)){
                bull++;
            }
            else{
                if(hash[secret.charAt(i) - '0'] > 0){
                    cow ++;   
                }
                
                if(hash[guess.charAt(i) - '0'] < 0){
                    cow ++;
                }
                hash[secret.charAt(i) - '0']--; 
                hash[guess.charAt(i) - '0']++;
            }

        }
        return bull + "A" + cow + "B";
    }
}

// The idea is to iterate over the numbers in secret and in guess and count all bulls right away. 
// For cows maintain an array that stores count of the number appearances in secret and in guess. 
// Increment cows when either number from secret was already seen in guest or vice versa.

// A slightly more concise version:

public String getHint(String secret, String guess) {
    int bulls = 0;
    int cows = 0;
    int[] numbers = new int[10];
    for (int i = 0; i<secret.length(); i++) {
        if (secret.charAt(i) == guess.charAt(i)) bulls++;
        else {
            if (numbers[secret.charAt(i)-'0']++ < 0) cows++;
            if (numbers[guess.charAt(i)-'0']-- > 0) cows++;
        }
    }
    return bulls + "A" + cows + "B";
}




class Solution {
    public String getHint(String secret, String guess) {
        Map<Character , Set<Integer>> map = new HashMap();
        for(int i = 0 ; i < secret.length() ; i++){
            map.computeIfAbsent(secret.charAt(i) , x -> new HashSet()).add(i); 
        }
        
        int ctB = 0;
        int ctC = 0;
        for(int i = 0 ; i < guess.length() ; i++){
            if(map.containsKey(guess.charAt(i))){
                Set<Integer> index = map.get(guess.charAt(i));
                if(index.contains(i)){
                    ctB++;
                    index.remove(i);
                }
                else{
                    for(int x : index){
                        if(secret.charAt(x) != guess.charAt(x)){
                            ctC ++;
                            index.remove(x);
                            break;
                        }
                    }
                }
            }
        }
        return ctB + "A" + ctC + "B";
    }
}






