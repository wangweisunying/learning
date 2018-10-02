// Word Squares
// Given a set of words without duplicates, find all word squares you can build from them.

// A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

// For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

// b a l l
// a r e a
// l e a d
// l a d y
// Example
// Given a set ["area","lead","wall","lady","ball"]
// return [["wall","area","lead","lady"],["ball","area","lead","lady"]]
// Explanation:
// The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

// Given a set ["abat","baba","atan","atal"]
// return [["baba","abat","baba","atan"],["baba","abat","baba","atal"]]
// Explanation:
// The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
// Notice
// There are at least 1 and at most 1000 words.
// All words will have the exact same length.
// Word length is at least 1 and at most 5.
// Each word contains only lowercase English alphabet a-z.

public class Solution {
    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */

     //save all the prefix in the list when insert
    class TrieNode{
        ArrayList<String> startWith;
        HashMap<Character , TrieNode> children;
        TrieNode(){
            startWith = new ArrayList();
            children = new HashMap();
        }
    }
    TrieNode root;
    private void insert(String[] word){
        root = new TrieNode();
        for(String w : word){
            TrieNode cur = root;
            char[] arr = w.toCharArray();
            for(int i = 0 ; i < arr.length ; i++){
                cur.startWith.add(w);
                if(cur.children.containsKey(arr[i])){
                    cur = cur.children.get(arr[i]);
                }else{
                    TrieNode children  = new TrieNode();
                    cur.children.put(arr[i] , children);
                    cur = children;
                }
            }
        }

    }
    private ArrayList<String> search(String pre){
        ArrayList<String> res = new ArrayList();
        TrieNode cur = root;
        char[] arr = pre.toCharArray();
        for(char ch : arr){
            if(cur.children.containsKey(ch)){
                cur = cur.children.get(ch);
            }
            else{
                return res;
            }
        }
        res.addAll(cur.startWith);
        return res;
    }
    int len;
    public List<List<String>> wordSquares(String[] words) {
        
        List<List<String>> res = new ArrayList();
        if(words == null  || words.length == 0){
            return res;
        }
        insert(words);
        len = words[0].length();
        dfs(res , new ArrayList<String>());
        return res;
    }
    private void dfs(List<List<String>> res ,List<String> cur){
        if(cur.size() == len){
            res.add(new ArrayList(cur));
            return;
        }
        //only dfs the avaiable words startWith the column[size]  
        int size = cur.size();
        String pre = "";
        for(String x : cur){
            pre += x.charAt(size);
        }
        ArrayList<String> startWith = search(pre);
        for(String str : startWith){
            cur.add(str);
            dfs(res , cur);
            cur.remove(cur.size() - 1);
        }
    }
} 



public class Solution {
     class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();
            for (String w : words) {
                TrieNode cur = root;
                for (char ch : w.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null)
                        cur.children[idx] = new TrieNode();
                    cur.children[idx].startWith.add(w);
                    cur = cur.children[idx];
                }
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null)
                    return ans;

                cur = cur.children[idx];
            }
            ans.addAll(cur.startWith);
            return ans;
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0)
            return ans;
        int len = words[0].length();
        Trie trie = new Trie(words);
        List<String> ansBuilder = new ArrayList<>();
        for (String w : words) {
            ansBuilder.add(w);
            search(len, trie, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }

        return ans;
    }

    private void search(int len, Trie tr, List<List<String>> ans,
            List<String> ansBuilder) {
        if (ansBuilder.size() == len) {
            ans.add(new ArrayList<>(ansBuilder));
            return;
        }

        int idx = ansBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : ansBuilder)
            prefixBuilder.append(s.charAt(idx));
        List<String> startWith = tr.findByPrefix(prefixBuilder.toString());
        for (String sw : startWith) {
            ansBuilder.add(sw);
            search(len, tr, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }
    }
}






//dfs time limited 
public class Solution {
    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    int len;
    public List<List<String>> wordSquares(String[] words) {
        
        List<List<String>> res = new ArrayList();
        if(words == null  || words.length == 0){
            return res;
        }
        len = words[0].length();
        dfs(res ,words  , new ArrayList<String>());
        return res;
    }
    private void dfs(List<List<String>> res ,String[] words  ,List<String> cur){
//        System.out.println(cur);
        if(check(cur)){
            res.add(new ArrayList(cur));
            return;
        }
        if(!checkPre(cur)){
            return;
        }
        if(cur.size() >= len){
            return;
        }
        for(int i = 0 ; i < words.length ; i++){
            cur.add(words[i]);
            dfs(res , words  , cur);
            cur.remove(cur.size() - 1);
        }
    }
    private boolean checkPre(List<String> cur){
        int size = cur.size();
        if(size > 1){
            int i, j;
            for(i = 0 ; i < size ; i++){
                for(j = i ; j < size ; j ++){
                    if(cur.get(i).charAt(j) != cur.get(j).charAt(i)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean check(List<String> cur){
        if(cur.size() != len ){
            return false;
        }
        int i, j;
        for(i = 0 ; i < len ; i++){
            for(j = i ; j < len ; j ++){
                if(cur.get(i).charAt(j) != cur.get(j).charAt(i)){
                    return false;
                }
            }
        }
        return true;
    }
}