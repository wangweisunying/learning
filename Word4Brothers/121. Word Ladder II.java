// 126. Word Ladder II
// Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

// Only one letter can be changed at a time
// Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
// Note:

// Return an empty list if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.
// You may assume no duplicates in the word list.
// You may assume beginWord and endWord are non-empty and are not the same.
// Example 1:

// Input:
// beginWord = "hit",
// endWord = "cog",
// wordList = ["hot","dot","dog","lot","log","cog"]

// Output:
// [
//   ["hit","hot","dot","dog","cog"],
//   ["hit","hot","lot","log","cog"]
// ]
// Example 2:

// Input:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log"]

// Output: []

// Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.


class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList();
        Set<String> set = new HashSet(wordList);
        if(!set.contains(endWord)) return res;
        Queue<String> que = new LinkedList();
        
        que.offer(endWord);
        Map<String , Integer> disMap = new HashMap();
        disMap.put(endWord , 0);

        int dis = 0;
        while(!que.isEmpty()){
            ++dis;
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                String curWord = que.poll();
                for(int j = 0  ; j < curWord.length() ; j++){
                    for(char ch = 'a' ; ch <= 'z' ; ch++ ){
                        String next = curWord.substring(0 , j) + ch + curWord.substring(j + 1);
                        if(!set.contains(next) || disMap.containsKey(next)) continue;
                        disMap.put(next , dis);
                        que.offer(next);    
                    }
                }    
            }
        }

        dfs(res , disMap , new ArrayList(Arrays.asList(beginWord)), beginWord , endWord , new HashSet());
        return res;
    }
    private void dfs(List<List<String>> res , Map<String , Integer> disMap , List<String> cur, String curWord , String end , Set<String> visited){
        if(curWord.equals(end)){
            res.add(new ArrayList(cur));
            return;
        } 
        
        List<String> canList = new ArrayList();
        int dis = Integer.MAX_VALUE;
        for(int j = 0  ; j < curWord.length() ; j++){
            for(char ch = 'a' ; ch <= 'z' ; ch++ ){
                String next = curWord.substring(0 , j) + ch + curWord.substring(j + 1);
                if(visited.contains(next) || !disMap.containsKey(next)) continue;
                int distance = disMap.get(next);
                if(dis > distance){
                    canList.clear();
                    canList.add(next);
                    dis = distance;
                }
                else if( dis == distance) canList.add(next);
            }
        }
        
        for(String next : canList){
            cur.add(next);
            visited.add(next);
            dfs(res , disMap , cur , next , end , visited);
            visited.remove(next);
            cur.remove(cur.size() - 1);    
        }
    }
}
























public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList();
        HashMap<String , Integer> dis_map = new HashMap();
        
        Queue<String> que = new LinkedList(); 
        
        que.offer(end);
        dis_map.put(end ,0);
        

        int level = 0;    
        while(!que.isEmpty()){
            int size = que.size();
            level++;
            for(int i = 0 ; i < size ; i++){
                String cur = que.poll();
                for(int j = 0 ; j < cur.length() ; j++){
                    for(char ch = 'a' ; ch <= 'z' ; ch++){
                        String newWord = cur.substring(0 , j) + ch + cur.substring(j + 1);
                        if(!dict.contains(newWord)){
                            continue;
                        }
                        if(dis_map.containsKey(newWord)){
                            continue;
                        }
                        dis_map.put(newWord ,level);
                        que.offer(newWord);
                    }
                }
            }
        }
        
        dfs(res ,start , end, new ArrayList(Arrays.asList(start)) , dis_map , new HashSet());
        return res;
    }
    private void dfs(List<List<String>> res , String start ,String end , List<String> cur ,HashMap<String , Integer> dis_map , HashSet<String> visited){
        if(start.equals(end)){
            res.add(new ArrayList(cur));
            return;
        }


        int distance = Integer.MAX_VALUE;
        List<String> list = new ArrayList();
        for(int i = 0 ; i < start.length() ; i++){
            for(char ch = 'a' ; ch <= 'z' ; ch++){
                String newWord = start.substring(0 , i) + ch + start.substring(i + 1);
                if(!dis_map.containsKey(newWord)){
                    continue;
                }
                if(visited.contains(newWord)){
                    continue;
                }
                int dis = dis_map.get(newWord);
                if(dis < distance){
                    list.clear();
                    list.add(newWord);
                    distance = dis;
                }
                else if(dis == distance){
                    list.add(newWord);
                }
            }
        }
        for(String newWord : list){
            visited.add(newWord);
            cur.add(newWord);
            dfs(res , newWord , end , cur , dis_map ,visited);
            cur.remove(cur.size() - 1);
            visited.remove(newWord);
        }
    }
}

public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList();
        
        HashMap<String , Integer> dis_map = new HashMap();
        
        Queue<String> que = new LinkedList(); 
        
        que.offer(end);
        dis_map.put(end ,0);
        

        int level = 0;    
        while(!que.isEmpty()){
            int size = que.size();
            level++;
            for(int i = 0 ; i < size ; i++){
                String cur = que.poll();
                for(int j = 0 ; j < cur.length() ; j++){
                    for(char ch = 'a' ; ch <= 'z' ; ch++){
                        String newWord = cur.substring(0 , j) + ch + cur.substring(j + 1);
                        if(!dict.contains(newWord)){
                            continue;
                        }
                        if(dis_map.containsKey(newWord)){
                            continue;
                        }
                        dis_map.put(newWord ,level);
                        que.offer(newWord);
                    }
                }
            }
        }
        
        dfs(res ,start , end, new ArrayList(Arrays.asList(start)) , dis_map);
        return res;
    }
    private void dfs(List<List<String>> res , String start ,String end , List<String> cur ,HashMap<String , Integer> dis_map ){
        if(start.equals(end)){
            res.add(new ArrayList(cur));
            return;
        }


        int distance = Integer.MAX_VALUE;
        List<String> list = new ArrayList();
        for(int i = 0 ; i < start.length() ; i++){
            for(char ch = 'a' ; ch <= 'z' ; ch++){
                String newWord = start.substring(0 , i) + ch + start.substring(i + 1);
                if(!dis_map.containsKey(newWord)){
                    continue;
                }
                //不需要visited记录 dis 自己过滤掉
                int dis = dis_map.get(newWord);

                
                if(dis < distance){
                    list.clear();
                    list.add(newWord);
                    distance = dis;
                }
                else if(dis == distance){
                    list.add(newWord);
                }
            }
        }
        for(String newWord : list){
           
            cur.add(newWord);
            dfs(res , newWord , end , cur , dis_map );
            cur.remove(cur.size() - 1);
           
        }
    }
}




public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        HashMap<String , Integer> distance_map = new HashMap();
        HashSet<String> visited = new HashSet();
        Queue<String> que = new LinkedList();
        que.offer(end);
        visited.add(end);
        distance_map.put(end , 0);
        
        int level = 0;
        while(!que.isEmpty()){
            int size = que.size();
            level++;
            for(int i = 0 ; i < size ; i++){
                String cur = que.poll();
                for(int j = 0 ; j < cur.length() ; j++){
                    for(char ch = 'a' ; ch <= 'z' ; ch ++){
                        String newWord = cur.substring(0 , j) + ch + cur.substring(j + 1);
                        if(!dict.contains(newWord)){
                            continue;
                        }
                        if(visited.contains(newWord)){
                            continue;
                        }
                        visited.add(newWord);
                        distance_map.put(newWord , level);
                        que.offer(newWord);
                    }    
                }
            }
        }
        List<List<String>> res = new ArrayList();
        dfs(start , end , distance_map , res , new ArrayList(Arrays.asList(start)));
        return res;
    }
    private void dfs(String start , String end , Map<String , Integer> distance_map,
                        List<List<String>> res , List<String> cur){
        if(start.equals(end)){
            res.add(new ArrayList(cur));
            return;
        }
        int min = Integer.MAX_VALUE;
        List<String> list = new ArrayList();
        for(int i = 0 ; i < start.length() ; i++){
            for(char ch = 'a' ; ch <= 'z' ; ch ++){
                String newWord = start.substring(0 , i) + ch + start.substring(i + 1);
                if(!distance_map.containsKey(newWord)){
                    continue;
                }
                int dis = distance_map.get(newWord);
                if(dis < min){
                    list.clear();
                    list.add(newWord);
                    min = dis;
                }
                else if(dis == min){
                    list.add(newWord);
                }
            }
        }
        for(String newWord : list){
            cur.add(newWord);
            dfs(newWord , end , distance_map , res , cur);
            cur.remove(cur.size() - 1);
        }
        
    }
    
    
    
}