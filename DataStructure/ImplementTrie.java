
// Implement Trie (Prefix Tree)
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
// You may assume that all inputs are consist of lowercase letters a-z.\
class TrieNode{
    char ch;
    HashMap<Character , TrieNode > children;
    boolean hasWord;
    TrieNode(char ch){
        this.ch = ch;
        children = new HashMap();
        hasWord = false;
    }
    TrieNode(){
        children = new HashMap();
        hasWord = false;
    }
}

public class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    /*
     * @param word: a word
     * @return: nothing
     */

    
    public void insert(String word) {
        TrieNode cur = root;
        char[] arr = word.toCharArray();
        for(int i = 0 ; i < arr.length ; i++ ){
            if(cur.children.containsKey(arr[i])){
                cur = cur.children.get(arr[i]);
            }else{
                TrieNode child = new TrieNode(arr[i]);
                cur.children.put(arr[i] , child);
                cur = child;
            }
            if(i == arr.length - 1){
                cur.hasWord = true;
            }
        }
    }
    public TrieNode searchWordNodePos(String s){
        TrieNode cur = root;
        for(char ch : s.toCharArray()){
            if(cur.children.containsKey(ch)){
                cur = cur.children.get(ch);
            }
            else{
                return null;
            }
        }
        return cur;
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        if(searchWordNodePos(word) == null){
            return false;
        }
        return searchWordNodePos(word).hasWord;
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return searchWordNodePos(prefix) == null ? false : true; 
    }
}



class TrieNode {
    // Initialize your data structure here.
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean hasWord;
    
    public TrieNode(){
        
    }
    
    public TrieNode(char c){
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        HashMap<Character, TrieNode> curChildren = root.children;
        char[] wordArray = word.toCharArray();
        for(int i = 0; i < wordArray.length; i++){
            char wc = wordArray[i];
            if(curChildren.containsKey(wc)){
                cur = curChildren.get(wc);
            } else {
                TrieNode newNode = new TrieNode(wc);
                curChildren.put(wc, newNode);
                cur = newNode;
            }
            curChildren = cur.children;
            if(i == wordArray.length - 1){
                cur.hasWord= true;
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if(searchWordNodePos(word) == null){
            return false;
        } else if(searchWordNodePos(word).hasWord) 
          return true;
          else return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(searchWordNodePos(prefix) == null){
            return false;
        } else return true;
    }
    
    public TrieNode searchWordNodePos(String s){
        HashMap<Character, TrieNode> children = root.children;
        TrieNode cur = null;
        char[] sArray = s.toCharArray();
        for(int i = 0; i < sArray.length; i++){
            char c = sArray[i];
            if(children.containsKey(c)){
                cur = children.get(c);
                children = cur.children;
            } else{
                return null;
            }
        }
        return cur;
    }
}