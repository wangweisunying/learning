
// Graph Valid Tree
// Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

// Example
// Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

// Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

// Notice
// You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.



public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    //check whether there is a cycle and share the same union
    int[] f;
    public int find(int a){
        if(f[a] == a){
            return a;
        }
        return f[a] = find(f[a]);
    }
    private boolean union(int a ,int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            f[rootA] = rootB;
            return false;
        }
        return true;
    }
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        // if (edges.length != n - 1) {
        //     return false;
        // }
        f = new int[n];
        for(int i = 0 ;i < n ; i++){
            f[i] = i;
        }
        
        for(int[] edge : edges){
            if(find(edge[1]) == find(edge[0])){
                return false;
            }    
            union(edge[0] , edge[1]);
        }

        // 判断是否联通 即是否有公用祖先
        int tmp = find(0);
        for(int i = 1 ; i < f.length ; i++ ){
            if(tmp != find(i)){
                return false;
            }
        }
        return true;
    }
}


class Solution {
    public boolean validTree(int n, int[][] edges) {
        Map<Integer , List<Integer>> map = new HashMap();
        for(int[] edge : edges){
            if(map.containsKey(edge[0])){
                map.get(edge[0]).add(edge[1]);
            }
            else{
                map.put(edge[0], new ArrayList(Arrays.asList(edge[1])));
            }
            if(map.containsKey(edge[1])){
                map.get(edge[1]).add(edge[0]);
            }
            else{
                map.put(edge[1], new ArrayList(Arrays.asList(edge[0])));
            }      
        }
        HashSet<Integer> visited = new HashSet();
        visited.add(0);
        dfs(map ,visited , 0 , - 1);
        return visited.size() == n && !cycle;
    }
    boolean cycle = false;
    private void dfs(Map<Integer , List<Integer>> map , HashSet<Integer> visited , int start  , int parent ){
        if(!map.containsKey(start)){
            return;
        }
        for(int next : map.get(start)){
            if(visited.contains(next) && parent != next){
                cycle = true;
            }
            if(visited.contains(next)){
                continue;
            }
            
            visited.add(next);
            dfs(map , visited , next , start);
        }    
    }
}





























public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        // 关键 单链结一定是 edges.length = n - 1
        if (edges.length != n - 1) {
            return false;
        }
        HashMap<Integer , HashSet<Integer>> graph = new HashMap();
        int i;
        for(i = 0 ; i < n ; i ++){
            graph.put(i , new HashSet());
        }
        for(i = 0 ; i < edges.length ; i++){
            int k = edges[i][0];
            int v = edges[i][1];
            graph.get(k).add(v);
            graph.get(v).add(k);
        }

        Queue<Integer> que = new LinkedList();
        HashSet<Integer> set = new HashSet();
        que.offer(0);
        set.add(0);

        while(!que.isEmpty()){
            int node = que.poll();
            for(int neighbor : graph.get(node)){
                if(set.contains(neighbor)){
                    continue;
                }
                set.add(neighbor);
                que.offer(neighbor);
            }
        }
        System.out.println(set.size());
        return set.size() == n;
    }
}


public class Solution {
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        
        //证明无环
        if (edges.length != n - 1) {
            return false;
        }
        
        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        
        // bfs 只需要证明 是否联通
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> hash = new HashSet<>();
        
        queue.offer(0);
        hash.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if (hash.contains(neighbor)) {
                    continue;
                }
                hash.add(neighbor);
                queue.offer(neighbor);
            }
        }
        
        return (hash.size() == n);
    }
    
    private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        return graph;
    }
}