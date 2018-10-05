
// 121. Word Ladder II
// Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

// Only one letter can be changed at a time
// Each intermediate word must exist in the dictionary
// Example
// Given:
// start = "hit"
// end = "cog"
// dict = ["hot","dot","dog","lot","log"]

// Return

// [
//   ["hit","hot","dot","dog","cog"],
//   ["hit","hot","lot","log","cog"]
// ]
// Notice
// All words have the same length.
// All words contain only lowercase alphabetic characters.


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