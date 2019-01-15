// 210. Course Schedule II

// Share
// There are a total of n courses you have to take, labeled from 0 to n-1.

// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

// Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

// There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

// Example 1:

// Input: 2, [[1,0]] 
// Output: [0,1]
// Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
//              course 0. So the correct course order is [0,1] .
// Example 2:

// Input: 4, [[1,0],[2,0],[3,1],[3,2]]
// Output: [0,1,2,3] or [0,2,1,3]
// Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
//              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
//              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
// Note:

// The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
// You may assume that there are no duplicate edges in the input prerequisites.


class Solution {
    public int[] findOrder(int n, int[][] prerequisites) {
        int[] indegree = new int[n];
        int[] res = new int[n];
        Map<Integer , Set<Integer>> map = new HashMap();
        for(int i = 0 ; i < n ; i ++) map.put(i , new HashSet());
        for(int[] prerequisite : prerequisites){
            map.get(prerequisite[1]).add(prerequisite[0]);
            ++indegree[prerequisite[0]];
        }
        Queue<Integer> que = new LinkedList();
        // boolean[] visited = new boolean[n];
        for(int i = 0 ; i < n ; i ++){
            if(indegree[i] == 0){
                que.offer(i); 
                // visited[i] = true;
            } 
        }
        if(que.isEmpty()) return new int[]{};
        int index = 0;
        while(!que.isEmpty()){
            int cur = que.poll();
            res[index++] = cur;
            for(int next : map.get(cur)){
                // if(visited[next]) continue;
                --indegree[next];
                if(indegree[next] == 0){
                    que.offer(next);
                    // visited[next] = true;
                }
            }
        }
        if(index != n) return new int[]{};
        return res;
    }
}



class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[]  neighbors = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        
        //init
        for(int i = 0 ; i < numCourses ; i ++){
            neighbors[i] = new ArrayList();
        }
        
        for(int i = 0 ; i < prerequisites.length ; i++){
            degree[prerequisites[i][0]]++;  //位置0的由 位置1 指向 入度 +1
            neighbors[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        Queue<Integer> que = new LinkedList();     
        for(int i = 0 ; i < degree.length ; i ++){
            if(degree[i] == 0){
                que.offer(i);
            }
        }
        
        ArrayList<Integer> res = new ArrayList();
        while(!que.isEmpty()){
            int course = que.poll();
            res.add(course);
            int size = neighbors[course].size();
            for(int i = 0 ; i < size ; i++){
                int pointCourse = neighbors[course].get(i);
                degree[pointCourse]--;
                if(degree[pointCourse] == 0){
                    que.offer(pointCourse);
                }
            }
            
        }
        if(res.size() == numCourses){
            int[] x = new int[numCourses];
            for(int i = 0 ; i < numCourses ; i ++){
                x[i] = res.get(i);
            }
            return x;
            
        }
        return new int[0];
    }
}