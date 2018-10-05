
// 132. Word Search II
// Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. A word can start from any position in the matrix and go left/right/up/down to the adjacent position. One character only be used once in one word.

// Example
// Given matrix:

// doaf
// agai
// dcan
// and dictionary:

// {"dog", "dad", "dgdg", "can", "again"}

// return {"dog", "dad", "can", "again"}


// dog:
// doaf
// agai
// dcan
// dad:

// doaf
// agai
// dcan
// can:

// doaf
// agai
// dcan
// again:

// doaf
// agai
// dcan


public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    class TriNode{
        char ch;
        boolean hasWord;
        Map<Character , TriNode> children;
        TriNode(char ch){
            this.ch = ch;
            this.hasWord = false;
            this.children = new HashMap();
        }
    }

    TriNode root;
    private void insert(String word){
        TriNode cur = root;
        for(char ch : word.toCharArray()){
            if(cur.children.containsKey(ch)){
                cur = cur.children.get(ch);
            }
            else{
                TriNode child = new TriNode(ch);
                cur.children.put(ch , child);
                cur = child;
            }
        }
        cur.hasWord = true;
    }
    
    private boolean precheck(String word){
        TriNode cur = root;
        for(char ch : word.toCharArray()){
            if(cur.children.containsKey(ch)){
                cur = cur.children.get(ch);
            }
            else{
                return false;
            }
        }
        return true;
    }


    private boolean check(String word){
        TriNode cur = root;
        for(char ch : word.toCharArray()){
            if(cur.children.containsKey(ch)){
                cur = cur.children.get(ch);
            }
            else{
                return false;
            }
        }
        return cur.hasWord;
    }
    
    int[] deltaX = {0 , 0 , 1, -1};
    int[] deltaY = {1 , -1 , 0 , 0};
    public List<String> wordSearchII(char[][] board, List<String> words) {
        root = new TriNode('D');
        for(String word : words){
            insert(word);
        }

        List<String> res = new ArrayList();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ;j < board[0].length ; j++){
               visited[i][j] = true;
               dfs(res , board , board[i][j] + "" , i , j , visited);
               visited[i][j] = false; 
            }
        }
        return res;
    }
    
    private void dfs(List<String> res ,char[][] board , String cur ,int xx , int yy  ,boolean[][] visited){
        if(check(cur)){
            if(!res.contains(cur)){
                res.add(cur);
            }
            
        }
        for(int i = 0 ; i < 4 ; i++){
            int x = xx + deltaX[i];
            int y = yy + deltaY[i];
            if(outBound(x ,y , board.length , board[0].length)){
                continue;
            }
            if(visited[x][y]){
                continue;
            }
            String newWord = cur + board[x][y];
            if(!precheck(newWord)){
                continue;
            }
            visited[x][y] = true;
            dfs(res , board, newWord , x , y , visited);
            visited[x][y] = false;
        }
    }
    
    private boolean outBound(int x ,int y , int m , int n){
        if(x < 0 || y < 0 || x >= m || y >= n ){
            return true;
        }
        return false;
    }
}



public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */

    class TrieNode{
        char ch;
        // boolean hasWord;
        HashMap<Character , TrieNode> children;
        TrieNode(char ch){
            this.ch = ch;
            // hasWord = false;
            children = new HashMap();
        }
    }
// 用trie 树 prefix check
    private void insert(String word){
        TrieNode cur = root;
        int i = 0;
        char[] s = word.toCharArray();
        for(; i < s.length ; i++){
            if(cur.children.containsKey(s[i])){
                cur = cur.children.get(s[i]);
            }
            else{
                TrieNode child = new TrieNode(s[i]);
                cur.children.put(s[i] , child);
                cur = child;
            }
            // if(i == s.length - 1){
            //     cur.hasWord = true;
            // }
        }
    }
    
    private boolean search(String pre){
        TrieNode cur = root;
        int i = 0;
        char[] s = pre.toCharArray();
        for(; i < s.length ; i++){
            if(cur.children.containsKey(s[i])){
                cur = cur.children.get(s[i]);
            }
            else{
                return false;
            }
        }
        return true;
    }

    TrieNode root;
    int[] deltaX;
    int[] deltaY;
    public List<String> wordSearchII(char[][] board, List<String> words) {
        root = new TrieNode('+');
        deltaX = new int[]{-1, 1, 0, 0};
        deltaY = new int[]{0, 0, -1, 1};
        for(String word : words){
            insert(word);
        }
        List<String> res = new ArrayList();
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[0].length ; j++){
                dfs(board , new boolean[board.length][board[0].length] , res , "" + board[i][j] , i , j , words);
            }
        }
        return res;
    }
    
    private void dfs(char[][] board , boolean[][] visited , List<String> res , String cur ,int i ,int j ,List<String> words ){
        visited[i][j] = true;
        if(!search(cur)){
            return;
        }
        if(words.contains(cur)){
            if(!res.contains(cur)){
                res.add(cur);
            }
            
        }
        for(int k = 0 ; k < 4 ; k++){
            int x = i + deltaX[k];
            int y = j + deltaY[k];
            if(!inBound(x , y , board.length , board[0].length)){
                continue;
            } 
            if(visited[x][y]){
                continue;
            }
            visited[x][y] = true;
            dfs(board , visited , res , cur + board[x][y] , x , y , words);
            visited[x][y] = false;
        }
    }
    private boolean inBound(int x , int y, int m, int n) {
        if (x >= 0 && x < m && y >= 0 && y < n) {
            return true;
        }
        return false;
    }
}