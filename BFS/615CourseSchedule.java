// There are a total of n courses you have to take, labeled from 0 to n - 1.

// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

// Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

// Example
// Given n = 2, prerequisites = [[1,0]]
// Return true

// Given n = 2, prerequisites = [[1,0],[0,1]]
// Return false

public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer , Integer > indegree = new HashMap();
        HashMap<Integer , List<Integer>> graph = new HashMap();

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

        while(!que.isEmpty()){
            int cur = que.poll();
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
        return indegree.isEmpty();
    }
}






































public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //get all the indegree
        ArrayList<Integer>[] neighbors = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        for(int i = 0 ; i < numCourses ; i ++){
            neighbors[i] = new ArrayList();
        } 
        for(int i = 0 ; i < prerequisites.length ; i ++){
            degree[prerequisites[i][0]]++; // 被指向点 indegree++
            neighbors[prerequisites[i][1]].add(prerequisites[i][0]); //被指向的点 为 指向点的neighbor
        }

        Queue<Integer> que = new LinkedList();
        for(int i = 0 ; i < degree.length ; i ++){
            if(degree[i] == 0){
                que.offer(i);
            }
        }
        int ct = 0;
        while(!que.isEmpty()){
            ct++;
            int course = que.poll();
            int size = neighbors[course].size();
            for(int i = 0 ; i < size ; i ++){
                int cur = neighbors[course].get(i);
                degree[cur]--;
                if(degree[cur] == 0){
                    que.offer(cur);
                }
                
            }
        }
        return ct == numCourses;
    }
}


public class Solution {
    /**
     * @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Write your code here
        List[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        
        for (int i = 0;i < numCourses; i++)
            edges[i] = new ArrayList<Integer>();
            
        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]] ++ ;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        Queue queue = new LinkedList();
        for(int i = 0; i < degree.length; i++){
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        
        int count = 0;
        while(!queue.isEmpty()){
            int course = (int)queue.poll();
            count ++;
            int n = edges[course].size();
            for(int i = 0; i < n; i++){
                int pointer = (int)edges[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    queue.add(pointer);
                }
            }
        }
        
        return count == numCourses;
    }
}