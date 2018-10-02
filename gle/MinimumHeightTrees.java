// Minimum Height Trees
// For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, 
// those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

// Format
// The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

// You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0]and thus will not appear together in edges.

// Example
// Example 1:

// Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

//         0
//         |
//         1
//        / \
//       2   3
// return [1]

// Example 2:

// Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

//      0  1  2
//       \ | /
//         3
//         |
//         4
//         |
//         5
// return [3, 4]

//暴力
public class Solution {
    /**
     * @param n: n nodes labeled from 0 to n - 1
     * @param edges: a undirected graph
     * @return:  a list of all the MHTs root labels
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        HashMap<Integer , HashSet<Integer>> graph = new HashMap();
        int i;
        for(i = 0 ; i < n ; i++){
            graph.put(i , new HashSet<Integer>());
        }
        for(i =  0 ; i < edges.length ; i++ ){
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        List<Integer> res = new ArrayList();
        Queue<Integer> que = new LinkedList();
        HashSet<Integer> visited = new HashSet();
        int min = Integer.MAX_VALUE;
        for(i = 0 ; i < n ; i ++){
            que.clear();
            visited.clear();
            que.offer(i);
            visited.add(i);
            int curMax = 0;
            while(!que.isEmpty()){
                curMax++;
                if(curMax > min){
                    break;
                }
                int size = que.size();
                for(int j = 0 ; j < size ; j++){
                    int curNode = que.poll();
                    for(int neighbor : graph.get(curNode)){
                        if(visited.contains(neighbor)){
                            continue;
                        }
                        que.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            if(curMax < min){
                res.clear();
                res.add(i);
                min = curMax;
                continue;
            }
            if(curMax == min){
                res.add(i);
                continue;
            }
        }
        return res;
    }
}


// 从末端开始找（用indgree 统计 ，从末端往上找）
public class Solution {
    /**
     * @param n: n nodes labeled from 0 to n - 1
     * @param edges: a undirected graph
     * @return:  a list of all the MHTs root labels
     */
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        HashMap<Integer , HashSet<Integer>> graph = new HashMap();
        int i;
        for(i = 0 ; i < n ; i++){
            graph.put(i , new HashSet<Integer>());
        }
        int[] indegree = new int[n];
        for(i =  0 ; i < edges.length ; i++ ){
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
            indegree[edges[i][0]]++;
            indegree[edges[i][1]]++;
        }
          List<Integer> res = new ArrayList();
        Queue<Integer> que = new LinkedList();
        for(i = 0 ; i < n ; i ++){
            if(indegree[i] == 0){
                res.add(0);
                return res;
            }
            else if(indegree[i] == 1){
                que.offer(i);
            }
        }
      
        while(!que.isEmpty()){
            int size = que.size();
            res.clear();
            for(i = 0 ; i < size ; i ++){
                int curNode = que.poll();
                res.add(curNode);
                indegree[curNode]--;
                for(int neighbor : graph.get(curNode)){   
                    indegree[neighbor]--;
                    if(indegree[neighbor] == 1){
                        que.offer(neighbor);
                    }
                }
            }   
        }
        return res;
    }
}


class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // sanity check ignored
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        if(n==1){
            res.add(0);
            return res;
        }
        for(int i = 0; i< n; i++){
            map.put(i, new ArrayList<Integer>());
        }
        // build indegree array and graph for later use
        int[] indegree = new int[n];
        for(int[] pair: edges){
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
            indegree[pair[0]]++;
            indegree[pair[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i< n; i++){
            if(indegree[i] == 0){
                return res;
            }
            else if(indegree[i] ==1){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int count = queue.size();
            // renew an res array for the final result since we do not whether it is one or two numbers. 
            res = new ArrayList<Integer>();
            for(int i = 0; i< count; i++){
                int cur = queue.poll();
                res.add(cur);
                indegree[cur]--;
                for(int nei: map.get(cur)){
                    if(indegree[nei]==0){
                        continue;
                    }
                    if(indegree[nei] ==2){
                        queue.offer(nei);
                    }
                    indegree[nei]--;// undirected so cut both indegrees 
                }
            }
        }
        return res;
    }
}