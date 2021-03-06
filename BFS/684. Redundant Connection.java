// 684. Redundant Connection
// DescriptionHintsSubmissionsDiscussSolution
// In this problem, a tree is an undirected graph that is connected and has no cycles.

// The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), 
// with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

// The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v,
//  that represents an undirected edge connecting nodes u and v.

// Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers,
//  return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

// Example 1:
// Input: [[1,2], [1,3], [2,3]]
// Output: [2,3]
// Explanation: The given undirected graph will be like this:
//   1
//  / \
// 2 - 3
// Example 2:
// Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
// Output: [1,4]
// Explanation: The given undirected graph will be like this:
// 5 - 1 - 2
//     |   |
//     4 - 3
// Note:
// The size of the input 2D-array will be between 3 and 1000.
// Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

// Update (2017-09-26):
// We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. 
// For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.

// [[9,10],[5,8],[2,6],[1,5],[3,8],[4,9],[8,10],[4,10],[6,8],[7,9]]
// Output:
// [6,8]
// Expected:
// [4,10]
// For each edge (u, v), traverse the graph with a depth-first search to see if we can connect u to v. If we can, then it must be the duplicate edge.

class Solution {

    HashMap<Integer , Integer> map;
    private int find(int i){
        if(map.get(i) == i){
            return map.get(i);
        }
        int tmp = find(map.get(i));
        map.put(i , tmp);
        return tmp;
    }
    private boolean union(int a , int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            map.put(rootA , rootB);
            return false;
        }
        return true;
    }
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        map = new HashMap();
        for(int i = 1 ; i <= n ; i++ ){
            map.put(i , i);
        }
        
        
        for(int i = 0 ; i < edges.length ; i++){
            find(edges[i][0]); // refresh parent
            find(edges[i][1]); // refresh parent
            if(union(edges[i][0] , edges[i][1])){
                return edges[i];
            }
        }
        return new int[]{-1 , - 1};
    }
}


class Solution {
    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge: edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                    dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        throw new AssertionError();
    }
    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true;
            for (int nei: graph[source]) {
                if (dfs(graph, nei, target)) return true;
            }
        }
        return false;
    }
}