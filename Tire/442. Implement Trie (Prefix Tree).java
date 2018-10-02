
// 442. Implement Trie (Prefix Tree)
// Implement a trie with insert, search, and startsWith methods.

// Example
// insert("lintcode")
// search("code")
// >>> false
// startsWith("lint")
// >>> true
// startsWith("linterror")
// >>> false
// insert("linterror")
// search("lintcode)
// >>> true
// startsWith("linterror")
// >>> true
// Notice
// You may assume that all inputs are consist of lowercase letters a-z.
// Input
// insert("lintcode")
// search("lint")
// startsWith("lint")
// Output
// [true,true]
// Expected
// [false,true]

public class Trie {
    class TrieNode{
        char ch;
        boolean hasWord;
        HashMap<Character , TrieNode> children;
        TrieNode(char ch){
            this.ch = ch;
            this.children = new HashMap();
        }
    }



    TrieNode root;
    public Trie() {
        root = new TrieNode('D');
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        TrieNode cur = root;
        char[] arr = word.toCharArray();
        int i = 0;
        for( ;i < arr.length ; i++){
            if(cur.children.containsKey(arr[i])){
                cur = cur.children.get(arr[i]);
            }
            else{
                TrieNode child = new TrieNode(arr[i]);
                cur.children.put(arr[i] , child);
                cur = child;
            }
        }
        cur.hasWord = true;
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode cur = root;
        char[] arr = word.toCharArray();
        int i = 0;
        for( ; i < arr.length ; i++){
            if(cur.children.containsKey(arr[i])){
                cur = cur.children.get(arr[i]);
            }
            else{
                return false;
            }
        }
        return cur.hasWord;
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        char[] arr = prefix.toCharArray();
        int i = 0;
        for( ; i < arr.length ; i++){
            if(cur.children.containsKey(arr[i])){
                cur = cur.children.get(arr[i]);
            }
            else{
                return false;
            }
        }
        return true;
    }
}