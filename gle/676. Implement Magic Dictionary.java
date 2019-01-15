// 676. Implement Magic Dictionary
// Implement a magic directory with buildDict, and search methods.

// For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

// For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, 
// the modified word is in the dictionary you just built.

// Example 1:
// Input: buildDict(["hello", "leetcode"]), Output: Null
// Input: search("hello"), Output: False
// Input: search("hhllo"), Output: True
// Input: search("hell"), Output: False
// Input: search("leetcoded"), Output: False
// Note:
// You may assume that all the inputs are consist of lowercase letters a-z.
// For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
// Please remember to RESET your class variables declared in class MagicDictionary, 
// as static/class variables are persisted across multiple test cases. Please see here for more details.
class MagicDictionary {

    /** Initialize your data structure here. */
    
    Map<Integer , List<String>> map;
    public MagicDictionary() {
        map = new HashMap();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for(String word : dict){
            map.computeIfAbsent(word.length() , x -> new ArrayList()).add(word);
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        if(!map.containsKey(word.length())) return false;
        List<String> list = map.get(word.length());
        for(String str : list){
            int i = 0;
            int ct = 0;
            for(; i < str.length() ; i++){
                if(str.charAt(i) != word.charAt(i)){
                    ct++;
                    if(ct == 2) break;
                }
            }
            if(i == str.length() && ct == 1) return true;
        }
        return false;
    }
}


/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */