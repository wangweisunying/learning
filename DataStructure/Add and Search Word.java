// Add and Search Word - Data structure design
// Design a data structure that supports the following two operations: addWord(word) and search(word)

// search(word) can search a literal word or a regular expression string containing only letters a-z or ..

// A . means it can represent any one letter.

// Example
// addWord("bad")
// addWord("dad")
// addWord("mad")
// search("pad")  // return false
// search("bad")  // return true
// search(".ad")  // return true
// search("b..")  // return true
// Notice
// You may assume that all words are consist of lowercase letters a-z.
public class WordDictionary {
    /*
     * @param word: Adds a word into the data structure.
     * @return: nothing
     */

    class TriNode{
        char ch;
        HashMap<Character , TriNode> children;
        boolean hasWord;
        TriNode(char ch){
            this.ch = ch ;
            children = new HashMap();
            hasWord = false;
        }
    }
    
    TriNode root = new TriNode('+');
    
         
    public void addWord(String word) {
        TriNode cur = root;
        char[] s = word.toCharArray();
        for(int i = 0 ; i < s.length ; i++){
            if(cur.children.containsKey(s[i])){
                cur = cur.children.get(s[i]);    
            }else{
                TriNode child = new TriNode(s[i]); 
                cur.children.put(s[i] , child );
                cur = child;
            }
            if(i == s.length - 1){
                cur.hasWord = true;
            }
        }
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        TriNode cur = root;
        for(int i = 0 ; i < word.toCharArray().length ; i++){
            if(!cur.children.containsKey(word.charAt(i))){
                if(word.charAt(i) != '.'){
                    return false;
                }
                // if(cur.children.isEmpty()){
                //     return false;
                // }
                for(char x : cur.children.keySet()){
                    if(search(word.substring(0 , i) + x + word.substring(i + 1))){
                        return true;
                    }
                }
                return false;
            }
            else{
                cur = cur.children.get(word.charAt(i));
            }
        }
        return cur.hasWord;
    }
}