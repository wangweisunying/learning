// 323. Number of Connected Components in an Undirected Graph
// DescriptionHintsSubmissionsDiscussSolution
// Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

// Example 1:

// Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

//      0          3
//      |          |
//      1 --- 2    4 

// Output: 2
// Example 2:

// Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

//      0           4
//      |           |
//      1 --- 2 --- 3

// Output:  1

// union find
class Solution {
    private int find(int a){
        if(f[a] == a){
            return a;
        }
        return f[a] = find(f[a]);
    }

    private void union(int a , int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            f[rootA] = rootB;
        }
    }
    int[] f;
    public int countComponents(int n, int[][] edges) {
        f = new int[n];
        for(int i = 0 ; i < n ; i++){
            f[i] = i;
        }
        
        for(int[] ed : edges){
            union(ed[0] , ed[1]);
        }
        HashSet<Integer> set = new HashSet();
        for(int i = 0 ; i < n ; i++){
            //有必要重新compress 一下 有一些节点没有更新father
            set.add(find(i));
        }
        return set.size();
    }
}


//dfs


class Solution {
    public int countComponents(int n, int[][] edges) {
        Map<Integer ,Set<Integer>> map = new HashMap();
        for(int i = 0 ; i < n ; i++){
            map.put(i , new HashSet());
        }
        
        for(int[] ed : edges){
            map.get(ed[0]).add(ed[1]);
            map.get(ed[1]).add(ed[0]);
        }
        Set<Integer> visited = new HashSet();
        int ct = 0
        for(int i = 0 ; i < n ; i++){
            if(visited.contains(i)){
                continue;
            }
            ct++;
            visited.add(i);
            dfs(i , map , visited);
        }
        return ct;
    }
    private void dfs(int i ,Map<Integer ,Set<Integer>> map ,Set<Integer> visited  ){
        for(int next : map.get(i)){
            if(visited.contains(next)){
                continue;
            }
            visited.add(next);
            dfs(next , map ,visited);
        }
    }
}





