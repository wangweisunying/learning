// Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n,
//  with 1 ≤ n ≤ 10^4. Reconstruction means building a shortest common supersequence of the sequences in seqs 
//  (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
//   Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

// Example
// Given org = [1,2,3], seqs = [[1,2],[1,3]]
// Return false
// Explanation:
// [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.

// Given org = [1,2,3], seqs = [[1,2]]
// Return false
// Explanation:
// The reconstructed sequence can only be [1,2].

// Given org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
// Return true
// Explanation:
// The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

// Given org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
// Return true
[5,3,2,4,1]
[[5,3,2,4],[4,1],[1],[3],[2,4], [1000000000]]
public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if(org.length == 1 && seqs.length == 1 && seqs[0].length == 1 && seqs[0][0] == org[0]){
            return true;
        }
        if(seqs == null || seqs.length == 0 || (seqs[0].length == 0 && org.length!= 0)){
            return false;
        }

        int n = org.length;
        HashMap<Integer, Integer > indegree = new HashMap();
        HashMap<Integer, Set<Integer>> graph = new HashMap();
        for(int i = 1 ; i <= n ; i++){
            indegree.put(i , 0);
            graph.put(i , new HashSet());
        }
        for(int[] route : seqs){
            if(route.length != 0 && !graph.containsKey(route[0])){
                return false;
            } 
            for(int i = 0 ; i < route.length - 1; i++){
                if(!graph.containsKey(route[i]) || !graph.containsKey(route[i + 1 ])){
                    return false;
                }
                if(graph.get(route[i]).contains(route[i + 1])){
                    continue;
                }
                indegree.put(route[i + 1] , indegree.get(route[i + 1]) + 1);
                graph.get(route[i]).add(route[i + 1]);
            }         
            
        }
        Queue<Integer> que = new LinkedList();
        for(int start : indegree.keySet()){
            if(indegree.get(start) == 0){
                que.offer(start);
            }
        }
        if(que.size() == 0 && n != 0){
            return false;
        }
        int ct = 0;
        while(!que.isEmpty()){
            int size = que.size();
            if(size > 1){
                System.out.println("size");
                return false;
            }
            int cur = que.poll();
            if(org[ct++] != cur){
                System.out.println(cur);
                return false;
            }
            for(int neighbor : graph.get(cur)){
                int tmp = indegree.get(neighbor) - 1;
                indegree.put(neighbor , tmp);
                if(tmp == 0){
                    que.offer(neighbor);
                }
            }
        }
        return ct == n;
        
    }
}

















































public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        Map<Integer , Set<Integer>> map = new HashMap<Integer , Set<Integer>>();
        Map<Integer , Integer> indegree = new HashMap<Integer , Integer>();
        // init 
        for(int num : org){
            map.put(num , new HashSet<Integer>());
            indegree.put(num , 0);
        }
        
        int n = org.length;
        int count = 0;
        for(int[] seq : seqs){
            count += seq.length;
            if(seq.length >= 1 && (seq[0] <= 0 || seq[0] > n)){
                return false;
            }
            for(int i = 1 ; i < seq.length ; i ++){
                if(seq[i] <= 0 || seq[i] > n){
                    return false;
                }
                if(map.get(seq[i - 1]).add(seq[i])){ // neighbors 
                    indegree.put(seq[i] , indegree.get(seq[i]) + 1);
                } 
            } 
        }

        if(count < n){
            return false;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for(int key : indegree.keySet()){
            if(indegree.get(key) == 0){
                q.offer(key);
            }
        }
        int cnt = 0;
        while(q.size() == 1){ // 一次只能进一个que  
            int ele = q.poll();
            for(int next : map.get(ele)){
                indegree.put(next , indegree.get(next) - 1);
                if(indegree.get(next) == 0){
                    q.offer(next);
                }
            }
            if(ele != org[cnt]){
                return false;
            }
            cnt++;
        }
        return cnt == org.length;
    }
}