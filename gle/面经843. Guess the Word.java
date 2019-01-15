// 843. Guess the Word

// This problem is an interactive problem new to the LeetCode platform.

// We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

// You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

// This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word. 
//  Also, if your guess is not in the given wordlist, it will return -1 instead.

// For each test case, you have 10 guesses to guess the word. At the end of any number of calls,
//  if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

// Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list. 
//  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

// Example 1:
// Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

// Explanation:

// master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
// master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
// master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
// master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
// master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

// We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
// Note:  Any solutions that attempt to circumvent the judge will result in disqualification.


/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */

 //答案一定和 canword 查 x个 字母， 所以 不断的更新缩小 wordlist to get the approch 
 //加速： 找到和所有字母关联最多的一个词 去找 ，这里用了一个hashMap 找到 和所有词 不关联的个数 ，取不关联个数最小的那一个 就是 最多关联的单词
class Solution {
    public void findSecretWord(String[] wordList, Master master) {
        int ct = 0;
        int x = 0; 
        while(ct < 10 && x < 6){
            //每次都需要重新计算
            Map<String , Integer> ctMap = new HashMap();
            for(String w1 : wordList){
                for(String w2 : wordList){
                    if(match(w1 , w2) == 0) ctMap.put(w1 , ctMap.getOrDefault(w1 , 0) + 1);
                }
            }
            String canWord = "" ;
            int val = 101;
            for(String w : wordList){
                if(ctMap.getOrDefault(w , 0) < val){
                    val = ctMap.getOrDefault(w , 0);
                    canWord = w;
                }
            }
            x = master.guess(canWord);
            List<String> nextList = new ArrayList();
            for(String w : wordList){
                if(match(w , canWord) == x) nextList.add(w);
            }
            wordList = nextList.toArray(new String[0]);
            ct++;
        }
    }
    private int match(String w1 , String w2){
        int res = 0;
        for(int i = 0 ; i < w1.length() ; i++ ){
            if(w1.charAt(i) == w2.charAt(i)) res++;
        }
        return res;
    }
}



public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            HashMap<String, Integer> count = new HashMap<>();
            for (String w1 : wordlist)
                for (String w2 : wordlist)
                    if (match(w1, w2) == 0)
                        count.put(w1, count.getOrDefault(w1 , 0) + 1);
            String canWord = "" ;
            int val = 101;
            for (String w : wordlist)
                if (count.getOrDefault(w, 0) < val){
                    val = count.getOrDefault(w, 0);
                    canWord = w;
                }
                   
            x = master.guess(canWord);
            List<String> wordlist2 = new ArrayList<String>();
            for (String w : wordlist)
                if (match(canWord, w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[0]);
        }
    }

    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i) if (a.charAt(i) == b.charAt(i)) matches ++;
        return matches;
    }


//     //Take a word from wordlist and guess it.
// Get the matches of this word
// Update our wordlist and keep only the same matches to our guess.

// For example we guess "aaaaaa" and get matches x = 3, we keep the words with exactly 3 a.

// I said I could get accepted but not for sure. In fact it has 80% rate to get accepted.

// Now we want to find a solution that improve this rate. We should guess a word that can minimum our worst case.

// Generally, we will get 0 matches and wordlist size reduce slowly.

// So we compare each two words and for each word, we note how many 0 matches it gets.

// Then we guess the word with minimum 0 matches.

// So even in most cases we get 0 match from master, it's still the best word that we can guess.

// Because the wordlist will reduce at minimum as possible.



        public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            HashMap<String, Integer> count = new HashMap<>();
            for (String w1 : wordlist)
                for (String w2 : wordlist)
                    if (match(w1, w2) == 0)
                        count.put(w1, count.getOrDefault(w1 , 0) + 1);
            Pair<String, Integer> minimax = new Pair<>("", 1000);
            for (String w : wordlist)
                if (count.getOrDefault(w, 0) < minimax.getValue())
                    minimax = new Pair<>(w, count.getOrDefault(w, 0));
            x = master.guess(minimax.getKey());
            List<String> wordlist2 = new ArrayList<String>();
            for (String w : wordlist)
                if (match(minimax.getKey(), w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[0]);
        }
    }

    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i) if (a.charAt(i) == b.charAt(i)) matches ++;
        return matches;
    }





    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            HashMap<String, Integer> count = new HashMap<>();
            for (String w1 : wordlist)
                for (String w2 : wordlist)
                    if (match(w1, w2) == 0)
                        count.put(w1, count.getOrDefault(w1 , 0) + 1);
            String canWord = "" ;
            int val = 101;
            for (String w : wordlist)
                if (count.getOrDefault(w, 0) < val){
                    val = count.getOrDefault(w, 0);
                    canWord = w;
                }
                   
            x = master.guess(canWord);
            List<String> wordlist2 = new ArrayList<String>();
            for (String w : wordlist)
                if (match(canWord, w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[0]);
        }
    }

    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i) if (a.charAt(i) == b.charAt(i)) matches ++;
        return matches;
    }


