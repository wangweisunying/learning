// 743. Network Delay Time
// DescriptionHintsSubmissionsDiscussSolution
// There are N network nodes, labelled 1 to N.

// Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

// Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

// Note:
// N will be in the range [1, 100].
// K will be in the range [1, N].
// The length of times will be in the range [1, 6000].
// All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

//dijkstra
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> map = new HashMap();
        for(int i = 0 ; i <= n ; i++){
            map.put(i , new ArrayList());
        }
        for(int[] time : times){
            map.get(time[0]).add(new int[]{time[1] , time[2]});
        }
        int[] path = new int[n + 1];
        Arrays.fill(path, Integer.MAX_VALUE);
        path[0] = 0;

        Queue<int[]> que = new PriorityQueue<int[]>((a , b) -> (a[1] - b[1]));
        que.offer(new int[]{k , 0});
        
        while(!que.isEmpty()){
            int[] cur = que.poll();
            if(path[cur[0]] != Integer.MAX_VALUE){
                continue;
            }
            path[cur[0]] = cur[1];
            for(int[] next : map.get(cur[0])){
                que.offer(new int[]{next[0] , next[1] + cur[1]});
            }
        }
        int max = 0 ;
        for(int i : path){
            if(i == Integer.MAX_VALUE){
                return -1;
            }
            max = Math.max(max , i);    
        }
        return max;       
    }
}






public int networkDelayTime(int[][] times, int N, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0])); //{distance, node}
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }
        int[] dis = new int[N+1]; //record the minimal distance of each node to K
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[K] = 0;
        boolean[] visited = new boolean[N+1];
        pq.offer(new int[]{0, K});
        int res = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[1];
            if (visited[curNode]) continue;
            visited[curNode] = true;
            int curDis = cur[0];
            res = curDis;
            N--;
            if (!map.containsKey(curNode)) continue;
            for (int next : map.get(curNode).keySet()) {
                if (!visited[next] && curDis + map.get(curNode).get(next) < dis[next]) {
                    dis[next] = curDis + map.get(curNode).get(next);
                    pq.offer(new int[]{dis[next], next});
                }
            }
        }
        return N == 0 ? res : -1;
    }




















//normal dfs
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> map = new HashMap();
        // for(int i = 0 ; i <= n ; i++){
        //     map.put(i , new ArrayList());
        // }
        for(int[] time : times){
            map.computeIfAbsent(time[0] , x -> new ArrayList()).add(new int[]{time[1] , time[2]});
        }
        int[] path = new int[n + 1];
        Arrays.fill(path, Integer.MAX_VALUE);
        path[0] = 0;
        path[k] = 0;
        
        dfs(path , map , k , 0);
        int max = 0 ;
        for(int i : path){
            if(i == Integer.MAX_VALUE){
                return -1;
            }
            max = Math.max(max , i);    
        }
        return max;
        
    }
    void dfs(int[] path , Map<Integer, List<int[]>> map , int k , int cur){
        if(!map.containsKey(k)){
            return;
        }
        for(int[] next : map.get(k)){
            if(cur + next[1] < path[next[0]]){
                path[next[0]] = cur + next[1];
                dfs(path , map  , next[0] , cur + next[1]); 
            }
        }
    }
    
}