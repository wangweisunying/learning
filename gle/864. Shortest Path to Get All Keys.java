// 864. Shortest Path to Get All Keys
// DescriptionHintsSubmissionsDiscussSolution
// We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

// // We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.
//   If we walk over a key, we pick it up. 
//  We can't walk over a lock unless we have the corresponding key.

// // For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid. 
//  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

// // Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.

 

// Example 1:

// Input: 

// ["@.a.#"
// ,"###.#"
// ,"b.A.B"]
// Output: 8
// Example 2:

// Input: ["@..aA","..B#.","....b"]
// Output: 6
 

// Note:

// 1 <= grid.length <= 30
// 1 <= grid[0].length <= 30
// grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
// The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.

class Solution {
    class Node{
        int x , y , key; 
        Node(int x, int y , int key){
            this.x = x;
            this.y = y;
            this.key = key;
        }
    }
    public int shortestPathAllKeys(String[] grid) {
        HashSet<String> visited  = new HashSet();
        Queue<Node> que = new LinkedList();
        int totalKeys = 0;
        int m = grid.length;
        int n = grid[0].length();
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i].charAt(j) == '@'){
                    que.offer(new Node( i , j , 0));
                    visited.add(i + "_" + j + "_" + 0);
                }
                if(grid[i].charAt(j) >= 'a' && grid[i].charAt(j) <= 'z' ) totalKeys++;
            }
        }
        int[] deltaX = {0 , 0 , 1 , -1};
        int[] deltaY = {1 , -1 , 0 , 0};
        int ct = 0;
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                Node node = que.poll();
                if((1 << totalKeys) - 1 == node.key){
                    return ct;
                }
                for(int j = 0 ; j < 4 ; j++){
                    int x = node.x + deltaX[j];
                    int y = node.y + deltaY[j];
                    int key = node.key;
                    if(x < 0 || y < 0 || x >= m || y >= n || grid[x].charAt(y) == '#')continue;
                    char c = grid[x].charAt(y);
                    if(c >= 'A' && c <='Z' && ((node.key >> (c - 'A')) & 1) != 1)continue;
                    if(c >= 'a' && c <= 'z'){
                        key |= 1 << (c - 'a');
                    }
                    if(visited.contains(x + "_" + y + "_" + key))continue;
                    visited.add(x + "_" + y + "_" + key);
                    que.offer(new Node(x , y , key));
                }
            }
            ct++;
        }
        return -1;
    }
}




















class Solution {
    class Status{
        int x , y , keys;
        Status(int x ,int y , int keys){
            this.x = x;
            this.y = y;
            this.keys = keys;
        }
    }
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        HashSet<String> visited = new HashSet();
        int max = -1;
        Queue<Status> que = new LinkedList();
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < grid[i].length() ; j++){
                if(grid[i].charAt(j) == '@'){
                    visited.add(i + "_" + j + "_" + 0);
                    que.offer(new Status(i , j , 0));
                }
                if(grid[i].charAt(j) >= 'a' && grid[i].charAt(j) <= 'f'){
                    max = Math.max(max ,grid[i].charAt(j) - 'a' + 1);
                }
            }
        }
        
        int[] deltaX = {1 , -1 , 0 , 0};
        int[] deltaY = {0 , 0 , 1 , -1};
        int ct = 0;
        while(!que.isEmpty()){
            int size = que.size();
            for(int k = 0 ; k < size ; k++){
                Status cur = que.poll();
                if (cur.keys == (1 << max) - 1) {
                    return ct;
                }
                for(int i = 0 ; i < 4 ; i++){
                    int x = cur.x + deltaX[i];
                    int y = cur.y + deltaY[i];
                    int keys = cur.keys;
                    if(x < 0 || y < 0 || x >=m || y >= n || grid[x].charAt(y) == '#'){
                        continue;
                    }
                    if(visited.contains(x + "_" + y + "_" + keys)){
                        continue;
                    }
                    char c = grid[x].charAt(y);
                    // move the key to the right() check whether it match the lock 1
                    if (c >= 'A' && c <= 'F' && (keys >> (c - 'A') & 1 )== 0) {
                        continue;
                    }
                    if (c >= 'a' && c <= 'f') {
                        //move the key to the left
                        keys |= 1 << (c-'a');
                    }
                    
                    visited.add(x + "_" + y + "_" + keys);
                    que.offer(new Status(x , y , keys));
                    
                }
                
                
            }
            ct++;
        }        
        return -1;
    }
}