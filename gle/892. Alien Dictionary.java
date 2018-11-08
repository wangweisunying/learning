
// 892. Alien Dictionary
// There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
// You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language.
//  Derive the order of letters in this language.

// Example
// Given the following words in dictionary,

// [
//   "wrt",
//   "wrf",
//   "er",
//   "ett",
//   "rftt"
// ]
// The correct order is: "wertf"

// Given the following words in dictionary,

// [
//   "z",
//   "x"
// ]
// The correct order is: "zx".

// Notice
// You may assume all letters are in lowercase.
// You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
// If the order is invalid, return an empty string.
// There may be multiple valid order of letters, return the smallest in lexicographical order
// Input
// ["abc","bcd","qwert","ab"]
// Output
// "cdertw"
// Expected
// ""


// Input
// ["zy","zx"]
// Output
// "yx"
// Expected
// "yxz"

// 思路判断有向图有无环的问题 用拓扑排序 ，或者dfs ,因为要根据英文顺序排列， 所以需要用priority queue 所以 用拓扑排序来解
// ， 最后需要判断一下String 的size 要和节点数量一致,有可能是森林的情况

class Solution {
    public String alienOrder(String[] words) {
        
        //construct graph and indegreee!! 
        Map<Character , Set<Character>> map = new HashMap();
        Map<Character , Integer> indegree = new HashMap();    
        for(String word : words){
            for(char c : word.toCharArray()){
                map.putIfAbsent(c , new HashSet());
                indegree.putIfAbsent( c , 0);
            }
        }
        
        for(int i = 0 ; i < words.length - 1 ; i++){
            for(int j = 0 ; j < Math.min(words[i].length() ,words[i + 1].length()) ; j++){
                if(words[i].charAt(j) != words[i + 1].charAt(j)){
                    map.get(words[i].charAt(j)).add(words[i+1].charAt(j));
                    break;
                }
            } 
        }

        for(char c : map.keySet()){
            for(char ch : map.get(c)){
                indegree.put(ch , indegree.get(ch) + 1);
            }
        }

        Queue<Character> que = new PriorityQueue();
        for(char c : indegree.keySet()){
            if(indegree.get(c) == 0){
                que.offer(c);
            }
        }

        String res = "";
        while(!que.isEmpty()){
            char cur = que.poll();
            res += cur;
            for(char nei : map.get(cur)){
                indegree.put(nei , indegree.get(nei) - 1);
                if(indegree.get(nei) == 0){
                    que.offer(nei);
                }
            }
        }
        if(res.length() != map.size()){
            return "";
        }
        return res;
    }
}

















// toplogical sort
public class Solution {
    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public String alienOrder(String[] words) {
        
        HashMap<Character , Set<Character>> nodes = new HashMap(); 
        for(String word : words){
            for(char ch : word.toCharArray()){
                nodes.putIfAbsent(ch , new HashSet());
            }
        }
        
        for(int i = 0 ; i < words.length - 1 ; i++){
            int size  = Math.min(words[i].length() , words[i + 1].length());
            for(int j = 0 ; j < size ; j++ ){
                if(words[i].charAt(j) != words[i + 1].charAt(j)){
                    nodes.get(words[i].charAt(j)).add(words[i + 1].charAt(j));
                    break;
                }
            }
        }

        HashMap<Character , Integer> indegree = new HashMap();
        for(char a : nodes.keySet()){
            indegree.putIfAbsent(a , 0);
            for(char ch : nodes.get(a)){
                indegree.put(ch , indegree.getOrDefault(ch , 0) + 1);
            }
        }
    
        Queue<Character> que = new PriorityQueue();
        for(char a : indegree.keySet()){
            if(indegree.get(a) == 0){
                que.offer(a);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!que.isEmpty()){
            char cur = que.poll();
            sb.append(cur);
            for(char neighbor : nodes.get(cur)){
                indegree.put(neighbor , indegree.get(neighbor) - 1);
                if(indegree.get(neighbor) == 0){
                    que.offer(neighbor);
                }
            }
        }
        if (sb.length() != nodes.size()) {
            return "";
        }
        return sb.toString();
        
    }
}






















class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = constructGraph(words);
        return topologicalSorting(graph);
    }
    
        
    private Map<Character, Set<Character>> constructGraph(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        
        // create nodes
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<Character>());
                }
            }
        }
        
        // create edges
        for (int i = 0; i <  words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) {
                    graph.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
                    break;
                }
                index++;
            }
        }

        return graph;
    }
    
    private Map<Character, Integer> getIndegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();
        for (Character u : graph.keySet()) {
            indegree.put(u, 0);
        }
        
        for (Character u : graph.keySet()) {
            for (Character v : graph.get(u)) {
                indegree.put(v, indegree.get(v) + 1);
            }
        }     
        
        return indegree;
    }

    private String topologicalSorting(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = getIndegree(graph);
        // as we should return the topo order with lexicographical order
        // we should use PriorityQueue instead of a FIFO Queue
        Queue<Character> queue = new PriorityQueue<>();
        
        for (Character u : indegree.keySet()) {
            if (indegree.get(u) == 0) {
                queue.offer(u);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character head = queue.poll();
            sb.append(head);
            for (Character neighbor : graph.get(head)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        if (sb.length() != indegree.size()) {
            return "";
        }
        return sb.toString();
    }
}

