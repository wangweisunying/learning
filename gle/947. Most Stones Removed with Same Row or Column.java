// 947. Most Stones Removed with Same Row or Column
// DescriptionHintsSubmissionsDiscussSolution
// On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

// Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

// What is the largest possible number of moves we can make?

 

// Example 1:

// Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
// Output: 5
// Example 2:

// Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
// Output: 3
// Example 3:

// Input: stones = [[0,0]]
// Output: 0
 

// Note:

// 1 <= stones.length <= 1000
// 0 <= stones[i][j] < 10000

// union find 
class Solution {
    private int find(int a){
        if(map.get(a) == a) return a;
        int res = find(map.get(a));
        map.put(a , res);
        return res;
    }
    private void union(int a , int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB ){
            map.put(rootB , rootA);
        }
    }

    Map<Integer , Integer> map;
    public int removeStones(int[][] stones) {
        map =  new HashMap();
        for(int[] stone : stones){
            map.put(stone[0] * 10000 + stone[1] , stone[0] * 10000 + stone[1]);
        }
        
        for(int i = 0 ; i < stones.length - 1 ; i ++){
            for(int j = i + 1 ; j < stones.length  ; j++){
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                    union(stones[i][0] * 10000 + stones[i][1] ,stones[j][0] * 10000 + stones[j][1]);
                }
            }
        }
        
        for(int key : map.keySet()){
            find(key);
        }
        HashSet<Integer> set = new HashSet(map.values());
        return stones.length - set.size();
    }
}



//dfs 裸搜 超时 是个全排列解N! 不是组合问题  先后顺序影响结果！
class Solution {
    public int removeStones(int[][] stones) {
        Map<Integer ,Set<Integer>> rowMap = new HashMap();
        Map<Integer ,Set<Integer>> colMap = new HashMap();
        for(int[] stone : stones){
            rowMap.computeIfAbsent(stone[0] , x -> new HashSet()).add(stone[1]);
            colMap.computeIfAbsent(stone[1] , x -> new HashSet()).add(stone[0]);
        }
        int[] ct = new int[1];
        dfs(rowMap , colMap , stones , ct);
        return max;
    }
    int max = 0;
    private void dfs(Map<Integer ,Set<Integer>> rowMap ,  Map<Integer ,Set<Integer>> colMap , int[][] stones , int[] ct){
        max = Math.max(max , ct[0]);
        for(int i = 0 ; i < stones.length ; i++){
            if(!rowMap.get(stones[i][0]).contains(stones[i][1])){
                continue;
            }
            if(rowMap.get(stones[i][0]).size() > 1 || colMap.get(stones[i][1]).size() > 1){
                ct[0]++;
                rowMap.get(stones[i][0]).remove(stones[i][1]);
                colMap.get(stones[i][1]).remove(stones[i][0]);
                dfs(rowMap , colMap , stones , ct );
                colMap.get(stones[i][1]).add(stones[i][0]);
                rowMap.get(stones[i][0]).add(stones[i][1]);
                ct[0]--;          
            }    
        }
    }
}





// [[1,2],[3,2],[3,0],[4,4],[4,2],[2,4],[4,0]]



