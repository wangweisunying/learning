

// There are a total of n courses you have to take, labeled from 0 to n - 1.
// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

// Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

// There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

// Example
// Given n = 2, prerequisites = [[1,0]]
// Return [0,1]

// Given n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
// Return [0,1,2,3] or [0,2,1,3]

public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer , Integer > indegree = new HashMap();
        HashMap<Integer , List<Integer>> graph = new HashMap();
        int[] res = new int[numCourses];
        for(int i = 0 ; i < numCourses ; i++){
            indegree.put(i , 0);
            graph.put(i , new ArrayList());
        } 

        for(int[] course : prerequisites){
            indegree.put(course[0] , indegree.get(course[0]) + 1 ); 
            graph.get(course[1]).add(course[0]);
        }

        Queue<Integer> que = new LinkedList();
        for(int course : indegree.keySet()){
            if(indegree.get(course) == 0){
                que.offer(course);
            }
        }
        int ct = 0;
        while(!que.isEmpty()){
            int cur = que.poll();
            res[ct++] = cur;
            indegree.remove(cur);
            for(int neighbor : graph.get(cur)){
                int tmp = indegree.get(neighbor) - 1;
                if(tmp == 0){
                    que.offer(neighbor);
                    // indegree.remove(neighbor);
                }
                else{
                    indegree.put(neighbor , tmp);
                }
            }
        }
        if(indegree.size() != 0){
            return new int[]{};
        }
        return res;
    }
}










public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer> res  = new ArrayList();
        //get all the indegree
        ArrayList<Integer>[] neighbors  = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        
        for(int i = 0 ; i < numCourses ; i ++){
            neighbors[i] = new ArrayList();
        }
        for(int i = 0 ; i < prerequisites.length ; i ++){
            degree[prerequisites[i][0]]++;
            neighbors[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        Queue<Integer> que = new LinkedList();
        for(int i = 0 ; i < degree.length ; i++){
            if(degree[i] == 0){
                que.offer(i);
            }
        }
        while(!que.isEmpty()){
            int course = que.poll();
            res.add(course);
            int size = neighbors[course].size();
            for(int i = 0 ; i < size ; i++){
                int cur = neighbors[course].get(i); // get current course  neighbors
                degree[cur]--;
                if(degree[cur] == 0){
                    que.offer(cur);
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