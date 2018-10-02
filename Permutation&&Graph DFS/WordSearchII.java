// Given a matrix of lower alphabets and a dictionary.
//  Find all words in the dictionary that can be found in the matrix. 
//  A word can start from any position in the matrix and go left/right/up/down to the adjacent position.
//   One character only be used once in one word.

// Example
// Given matrix:

// doaf
// agai
// dcan
// and dictionary:

// {"dog", "dad", "dgdg", "can", "again"}

// return {"dog", "dad", "can", "again"}

// Challenge
// Using trie to implement your algorithm.
// ["b","a","b","b","a"]
// ["babbab","b","a","ba"]

public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */

    int[] deltaX ;
    int[] deltaY ;
    TrieNode root;
    public List<String> wordSearchII(char[][] board, List<String> words) {
        root = new TrieNode('D');
        for(String word : words){
            insert(word);
        }
        
        deltaX = new int[]{1 , -1 , 0 , 0};
        deltaY = new int[]{0 , 0 , 1 , -1};
        List<String> res = new ArrayList();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[0].length ; j++){
                visited[i][j] = true;
                dfs(res , board , "" + board[i][j] , i , j ,visited , words );  
                visited[i][j] = false;  
            }
        }
        
        return res;
    }
    private void dfs(List<String> res, char[][] board , String cur , int i , int j , boolean[][] visited , List<String> words){
        if(words.contains(cur)){
            if(!res.contains(cur)){
                res.add(cur);
            }
        }
        
        for(int k = 0 ; k < 4 ; k++){
            int x = i + deltaX[k];
            int y = j + deltaY[k];
            if(outBound(x , y , board.length , board[0].length)){
                continue;
            }
            if(visited[x][y]){
                continue;
            }
            if(!checkprefix(cur)){
                continue;
            }
            visited[x][y] = true;
            dfs(res , board , cur + board[x][y] , x , y , visited , words);
            visited[x][y] = false;
            
        }
    }
    private boolean outBound(int x ,int y ,int m , int n){
        if(x < 0 || y < 0 || x >= m || y >= n){
            return true;
        }
        return false;
    }


    class TrieNode{
        char ch;
        boolean hasWord;
        HashMap<Character , TrieNode> children;
        TrieNode(char ch){
            this.ch = ch;
            this.children = new HashMap();
            this.hasWord = false;
        }
    } 
    
    
    public void insert(String word){
        TrieNode cur = root;
        char[] arr = word.toCharArray();
        int i = 0 ;
        for( ;i < arr.length ; i++){
            if(cur.children.containsKey(arr[i])){
                cur = cur.children.get(arr[i]);
            }
            else{
                TrieNode child = new TrieNode(arr[i]);
                cur.children.put(arr[i], child);
                cur = child;
            }
        }
        cur.hasWord = true;
    }

    // public boolean search(String word){
    //     TrieNode cur = root;
    //     char[] arr = word.toCharArray();
    //     int i = 0 ;
    //     for( ;i < arr.length ; i++){
    //         if(cur.children.containsKey(arr[i])){
    //             cur = cur.children.get(arr[i]);
    //         }
    //         else{
    //             return false;
    //         }
    //     }
    //     return cur.hasWord;
    // }
    public boolean checkprefix(String word){
        TrieNode cur = root;
        char[] arr = word.toCharArray();
        int i = 0 ;
        for( ;i < arr.length ; i++){
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

















public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    class Axis {
        int x, y;
        Axis(int x, int y) {
            this.x = x;
            this.y = y;
           
        }

    }
    int[] deltaX = {-1, 1, 0, 0};
    int[] deltaY = {0, 0, -1, 1};

    public List<String> wordSearchII(char[][] board, List<String> words) {
        List<String> res = new ArrayList();
        if (words.size() == 0) {
            return res;
        }
        HashMap<String, List<Axis>> map = new HashMap();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                for (String str : words) {
                    if (board[i][j] == str.charAt(0)) {
                        if (map.keySet().contains(str)) {
                            map.get(str).add(new Axis(i, j));
                        } else {
                            List<Axis> tmp = new ArrayList();
                            tmp.add(new Axis(i, j));
                            map.put(str, tmp);
                        }
                    }
                }
            }
        }
        for (String word : map.keySet()) {
            for (Axis cur : map.get(word)) {
                boolean[][] visited  = new boolean[board.length][board[0].length];
                visited[cur.x][cur.y] = true;
                if (dfs(board, cur, word.substring(0 , 1), word, visited , false , 0)) {
                    res.add(word);
                    break;
                }
            }
        }

        return res;
    }

    private boolean dfs(char[][] board, Axis axis, String cur, String std, boolean[][] visited , boolean found , int startIndex) {
        if(found){
            return true;
            
        }
        if (std.equals(cur)) {
            found = true;
            return true;
        }

        for (int i = startIndex; i < std.length(); i++) {
            for (int j = 0; j < 4; j++) {
                if (!inBound(axis.x + deltaX[j] , axis.y + deltaY[j] , board.length, board[0].length)) {
                    continue;
                }
                if(visited[axis.x + deltaX[j]][axis.y + deltaY[j]]){
                    continue;
                }
                if (std.charAt(startIndex + 1) != board[axis.x + deltaX[j]][axis.y + deltaY[j]]) {
                    continue;
                }             
                visited[axis.x + deltaX[j]][axis.y + deltaY[j]] = true;
                found = dfs(board, new Axis(axis.x + deltaX[j], axis.y + deltaY[j]), cur + board[axis.x + deltaX[j]][axis.y + deltaY[j]], std, visited , found , startIndex + 1);
                visited[axis.x + deltaX[j]][axis.y + deltaY[j]] = false;
            }
            return found;
            
        }
        return found;
    }

    private boolean inBound(int x , int y, int m, int n) {
        if (x >= 0 && x < m && y >= 0 && y < n) {
            return true;
        }
        return false;
    }
}