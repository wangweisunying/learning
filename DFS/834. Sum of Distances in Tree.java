// 834. Sum of Distances in Tree

// An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.

// The ith edge connects nodes edges[i][0] and edges[i][1] together.

// Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

// Example 1:

// Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
// Output: [8,12,6,10,10,10]
// Explanation: 
// Here is a diagram of the given tree:
//   0
//  / \
// 1   2
//    /|\
//   3 4 5
// We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
// equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.

// Note: 1 <= N <= 10000


// [[0,1],[0,2],[2,3],[2,4],[2,5]]
// Output
// [2,1,7,2,2,2]
// Expected
// [8,12,6,10,10,10]

// memo dfs memeray exceed!
class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[][] memo = new int[n][n];
        // for(int[] tmp : memo) Arrays.fill(tmp , -1);
        // for(int i = 0 ; i < n ; i++){
        //     memo[i][i] = 0;
        // }
        int[] res = new int[n];
        Map<Integer , Set<Integer>> map = new HashMap();
        for(int[] edge : edges){
            map.computeIfAbsent(edge[0] , x-> new HashSet()).add(edge[1]);
            map.computeIfAbsent(edge[1] , x-> new HashSet()).add(edge[0]);
        }

        for(int i = 0 ; i < n - 1 ; i++){
            for(int j = i + 1 ; j < n ; j++){
                // 图的dfs 还是需要用个hashSet记录
                dfs(memo , map , i , j  , new HashSet(Arrays.asList(i)));
            }
        }
       
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ;j++){
                if(i == j) continue;
                res[i]+=memo[i][j];
            }
        }
        return res;
    }
    private boolean dfs(int[][] memo ,  Map<Integer , Set<Integer>> map , int cur , int dest  , Set<Integer> visited){
        if(cur == dest) return true;
        if(memo[cur][dest] != 0|| memo[dest][cur] != 0)return true;
        for(int next : map.get(cur)){
            if(visited.contains(next)) continue;
            visited.add(next);
            if(dfs(memo , map , next , dest , visited)){
                memo[cur][dest] =  1 + memo[next][dest];
                memo[dest][cur] =  1 + memo[dest][next];
                return true;
            }
        }
        return false;
    }
// }
// Then, as illustrated in the diagram, the answer for xx in the entire tree, is the answer of xx on XX "x@X", plus the answer of yy on YY "y@Y", plus the number of nodes in YY "#(Y)".
//  The last part "#(Y)" is specifically because for any node z in Y, dist(x, z) = dist(y, z) + 1.

// By similar reasoning, the answer for yy in the entire tree is ans[y] = x@X + y@Y + #(X). Hence, for neighboring nodes xx and yy, ans[x] - ans[y] = #(Y) - #(X).

// Algorithm

// Root the tree. For each node, consider the subtree S_{\text{node}}S 
// node
// ​	
//   of that node plus all descendants. Let count[node] be the number of nodes in S_{\text{node}}S 
// node
// ​	
//  , and stsum[node] ("subtree sum") be the sum of the distances from node to the nodes in S_{\text{node}}S 
// node
// ​	
//  .

// We can calculate count and stsum using a post-order traversal,
//  where on exiting some node, the count and stsum of all descendants of this node is correct, and we now calculate count[node] += count[child] and stsum[node] += stsum[child] + count[child].

// This will give us the right answer for the root: ans[root] = stsum[root].

// Now, to use the insight explained previously: if we have a node parent and it's child child, then these are neighboring nodes, and so ans[child] = ans[parent] - count[child] + (N - count[child]).
//  This is because there are count[child] nodes that are 1 easier to get to from child than parent, and N-count[child] nodes that are 1 harder to get to from child than parent.

class Solution {
        int[] res, count; ArrayList<HashSet<Integer>> tree; int n;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        tree = new ArrayList<HashSet<Integer>>();
        res = new int[N];
        count = new int[N];
        n = N;
        for (int i = 0; i < N ; ++i ) tree.add(new HashSet<Integer>());
        for (int[] e : edges) {tree.get(e[0]).add(e[1]); tree.get(e[1]).add(e[0]);}
        dfs(0, new HashSet<Integer>());
        dfs2(0, new HashSet<Integer>());
        return res;
    }

    public void dfs(int root, HashSet<Integer> seen) {
        seen.add(root);
        for (int i : tree.get(root))
            if (!seen.contains(i)) {
                dfs(i, seen);
                count[root] += count[i];
                res[root] += res[i] + count[i];
            }
        count[root]++;
    }


    public void dfs2(int root, HashSet<Integer> seen) {
        seen.add(root);
        for (int i : tree.get(root))
            if (!seen.contains(i)) {
                res[i] = res[root] - count[i] + n - count[i];
                dfs2(i, seen);
            };
    }
}