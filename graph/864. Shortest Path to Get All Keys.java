// 864. Shortest Path to Get All Keys
// We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

// We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions. 
//  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

// // For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid. 
//  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

// // Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.

 

// Example 1:

// Input: 
// ["@.a.#",
//  "###.#",
//  "b.A.B"]

// Output: 8
// Example 2:

// Input: 
// ["@..aA",
//  "..B#.",
//  "....b"]
// Output: 6

// ["@..bA",
//  ".. B#.",
//  "....a"]
// Output: 6


// ["@..bA",
//  "..B#.",
//  "....a"]
// Output: 8




// Note:

// 1 <= grid.length <= 30
// 1 <= grid[0].length <= 30
// grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
// The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.

// ["..#....##."
// ,"....d.#.D#"
// ,"#...#.c..."
// ,"..##.#..a."
// ,"...#....##"
// ,"#....b...."
// ,".#..#....."
// ,".........."
// ,".#..##..A."
// ,".B..C.#..@"]

//bfs 利用位运算 来保持unique

class Solution {
    class State {
        int keys, i, j;
        State(int keys, int i, int j) {
            this.keys = keys;
            this.i = i;
            this.j = j;
        }
    }
    public int shortestPathAllKeys(String[] grid) {
        int x = -1, y = -1, m = grid.length, n = grid[0].length(), max = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    x = i;
                    y = j;
                }
                if (c >= 'a' && c <= 'f') {
                    max = Math.max(c - 'a' + 1, max);
                }
            }
        }
        State start = new State(0, x, y);
        Queue<State> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(0 + " " + x + " " + y);
        q.offer(start);
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                State cur = q.poll();
                if (cur.keys == (1 << max) - 1) {
                    return step;
                }
                for (int[] dir : dirs) {
                    int i = cur.i + dir[0];
                    int j = cur.j + dir[1];
                    int keys = cur.keys;
                    if (i >= 0 && i < m && j >= 0 && j < n) {
                        char c = grid[i].charAt(j);
                        if (c == '#') {
                            continue;
                        }

                        //bit operation think like a key teeth 

                        if (c >= 'a' && c <= 'f') {
                            //move the key teeth left 101010 //have f0d0b0
                            keys |= 1 << (c - 'a');
                        }
                        
                        if (c >= 'A' && c <= 'F' && ((keys >> (c - 'A')) & 1) == 0) {
                            continue;
                        }
                        if (!visited.contains(keys + " " + i + " " + j)) {
                            visited.add(keys + " " + i + " " + j);
                            q.offer(new State(keys, i, j));
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}


























["..#....##.","....d.#.D#","#...#.c...","..##.#..a.","...#....##","#....b....",".#..#.....","..........",".#..##..A.",".B..C.#..@"]
class Solution {
    int m , n ;
    int[] deltaX = {0 , 0 , 1 , -1};
    int[] deltaY = {1 , -1 , 0 , 0};
    public int shortestPathAllKeys(String[] grid) {
        m = grid.length;
        n = grid[0].length();
        HashSet<Character> keys = new HashSet();
        HashSet<Character> locks = new HashSet();
        Queue<int[]> que = new LinkedList();
        
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i].charAt(j) - 'a' <= 5 && grid[i].charAt(j) - 'a' >=0){
                    keys.add(grid[i].charAt(j));
                }
                if(grid[i].charAt(j) - 'A' <= 5 && grid[i].charAt(j) - 'A' >=0){
                    locks.add(grid[i].charAt(j));
                }
                if(grid[i].charAt(j) == '@'){
                    que.offer(new int[]{i , j});
                    grid[i] = grid[i].substring(0 , j) + '.' + grid[i].substring(j + 1) ;
                }
            }
        }
        System.out.println(keys);
        System.out.println(locks);
        
        
        int pre = -1;
        while(!keys.isEmpty() && pre != ct){
            pre = ct;
            boolean[][] visited = new boolean[m][n];
            bfs(que ,visited , keys , locks , grid);
            
        }
        return keys.isEmpty()? ct : -1 ;
    }
    int ct = 0;
    private void bfs( Queue<int[]> que ,boolean[][] visited , HashSet<Character> keys , HashSet<Character> locks , String[] grid){
//        for(String x : grid){
//            System.out.println(x);
//            
//        }
//        System.out.println();
        while(!que.isEmpty()){
            ct++;
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                int[] cur = que.poll();
                visited[cur[0]][cur[1]] = true;
                for(int j = 0 ; j < 4 ; j++){
                    int x = deltaX[j] + cur[0];
                    int y = deltaY[j] + cur[1];
                    if(x < 0 || y < 0 || x >=m || y >= n || visited[x][y] || grid[x].charAt(y) == '#'){
                        continue;
                    }
                    if(keys.contains(grid[x].charAt(y))){
                        
                        

                        keys.remove(grid[x].charAt(y));
                        
                        grid[x] = grid[x].substring(0 , y) + '.' + grid[x].substring(y + 1);
                        que.clear();
                        que.offer(new int[]{x , y});
                        return;
                    }
                    if(locks.contains(grid[x].charAt(y))){
//                        System.out.println(grid[x].charAt(y));
//                        System.out.println(keys);
                        if(keys.contains(Character.toLowerCase(grid[x].charAt(y)))){
//                            System.out.println(Character.toLowerCase(grid[x].charAt(y)));
                            continue;
                        }
                        locks.remove(grid[x].charAt(y));
                        grid[x] = grid[x].substring(0 , y) + '.' + grid[x].substring(y + 1);
                    }
                    que.offer(new int[]{x , y});
                }
            }
        }
    }
}