// 886. Possible Bipartition
// Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

// Each person may dislike some other people, and they should not go into the same group. 

// Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

// Return true if and only if it is possible to split everyone into two groups in this way.

 

// Example 1:

// Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
// Output: true
// Explanation: group1 [1,4], group2 [2,3]
// Example 2:

// Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
// Output: false
// Example 3:

// Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
// Output: false
 

// Note:

// 1 <= N <= 2000
// 0 <= dislikes.length <= 10000
// 1 <= dislikes[i][j] <= N
// dislikes[i][0] < dislikes[i][1]
// There does not exist i != j for which dislikes[i] == dislikes[j].

// 5
// [[1,2],[3,4],[4,5],[3,5]]


class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer , Set<Integer>> map = new HashMap();
        for(int i = 1 ; i <= N ;i++){
            map.put(i , new HashSet());
        }     
        for(int[] dislike : dislikes){
            map.get(dislike[0]).add(dislike[1]);
            map.get(dislike[1]).add(dislike[0]);
        }
        
        int[] color = new int[N + 1];
        Arrays.fill(color , -1);
        
        // need to check all the points may be a froest
        for(int x = 1 ; x <= N ; x++){
            
            if(color[x] != -1)continue;
            Queue<Integer> que = new LinkedList();
            que.offer(x);
            color[x] = 0;
            int curColor = 0;
            while(!que.isEmpty()){
                int size = que.size();
                curColor = curColor == 1 ? 0 : 1; 
                for(int i = 0 ; i < size ; i ++){
                    int cur = que.poll();
                    for(int next : map.get(cur)){
                        if(color[next] != -1){
                            if(color[next] != curColor)return false;
                            else continue;
                        }
                        color[next] = curColor;
                        que.offer(next);
                    }
                }
                
            }
        }
        
        return true;
    }
}




class Solution {
    //图的二分问题 将每个相邻的节点染成不同颜色 ，最后都遍历完了  就返回true ，如果发现遍历不了就返回false；
    //dfs
    // class Solution {
    //图的二分问题 将每个相邻的节点染成不同颜色 ，最后都遍历完了  就返回true ，如果发现遍历不了就返回false
    
    //dfs bfs 都写一遍！！
//     public boolean possibleBipartition(int n, int[][] nums) {
//         if(nums == null || nums.length == 0){
//             return true;
//         }
//         int[] f =  new int[n + 1];
//         HashMap<Integer , List<Integer>> map = new HashMap();
//         for(int[] num : nums){
//             map.computeIfAbsent(num[0], x -> new ArrayList()).add(num[1]);
//             map.computeIfAbsent(num[1] , x -> new ArrayList()).add(num[0]);
//         }
//         for(int j = 1 ; j <= n ; j++){
//             if(f[j] != 0 || !map.containsKey(j)){
//                 continue;
//             }
//             f[j] = 1;
//             if(!dfs(map , f , j , 1)){
//                 return false;
//             }
              
//         }
//         return true;
//     }
//     private boolean dfs(HashMap<Integer , List<Integer>> map , int[] f , int i , int color){
//         for(int nei : map.get(i)){
//             if(f[nei]!= 0){
//                 if(f[nei] == color){
//                     return false;
//                 }
//                 continue;
//             }
//             f[nei] = -color;
//             if(!dfs(map , f , nei , -color)){
//                 return false;    
//             }
//         }
//         return true;
//     }
// }
    
    
    //bfs
    public boolean possibleBipartition(int n, int[][] nums) {
        if(nums == null || nums.length == 0){
            return true;
        }
        int[] f =  new int[n + 1];
        HashMap<Integer , List<Integer>> map = new HashMap();
        for(int[] num : nums){
            map.computeIfAbsent(num[0], x -> new ArrayList()).add(num[1]);
            map.computeIfAbsent(num[1] , x -> new ArrayList()).add(num[0]);
        }
        
        Queue<Integer> que = new LinkedList();
        for(int j = 1 ; j <= n ; j++){
            if(f[j] != 0){
                continue;
            }
            f[j] = 1;
            que.offer(j);
            int label = 1;
            while(!que.isEmpty()){
                label *= -1;
                int size = que.size();
                for(int i = 0 ; i < size ; i++){
                    int cur = que.poll();
                    if(!map.containsKey(cur)){
                        continue;
                    }
                    for(int nei : map.get(cur)){
                        if(f[nei] != 0 ){
                            if(f[cur] == f[nei]){
                                return false;
                            }
                            continue;
                        }
                        f[nei] = label;
                        que.offer(nei);
                    }
                }
            }  
        }
        
        return true;
    }
}