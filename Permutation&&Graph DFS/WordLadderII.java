// Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

// Only one letter can be changed at a time
// Each intermediate word must exist in the dictionary
// Example
// Given:
// start = "hit"
// end = "cog"
// dict = ["hot","dot","dog","lot","log"]
// Return
//   [
//     ["hit","hot","dot","dog","cog"],
//     ["hit","hot","lot","log","cog"]
//   ]


public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */

     //超时 需优化
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList();
        if(dict.isEmpty()){
            return res;
        }
        dict.add(end);
        
        dfs(start , end , dict ,res , new ArrayList(Arrays.asList(start)) , new HashSet<String>() , 0);
        return res;
    }
    int len = Integer.MAX_VALUE;
    private void dfs(String str, String end, Set<String> dict , List<List<String>> res , List<String> cur , HashSet<String> selected , int curLen){
      
        if(curLen > len){
            return;
        }
        System.out.println(cur);
        if(str.equals(end)){
            if(curLen == len){
                res.add(new ArrayList(cur)); 
            }
            else{
                len = curLen;
                res.clear();
                res.add(new ArrayList(cur)); 
            }
        }
        for(int i = 0 ; i < str.length() ; i ++){
            for(int j = 0 ; j < 26 ; j ++){
                String newWord = str.substring(0 , i) + (char)('a' + j) + str.substring(i + 1);
                if(newWord.equals(str)){
                    continue;
                }
                if(!dict.contains(newWord)){
                    continue;
                }
                if(selected.contains(newWord)){
                    continue;
                }
                selected.add(newWord);
                cur.add(newWord);
                dfs(newWord , end , dict , res ,  cur , selected ,curLen + 1);
                cur.remove(cur.size() - 1);
                selected.remove(newWord);  
            }
        }
    }  
}

     

//bfs calculate the distance from End  to start!!


public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */

     //超时 需优化
public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList();
        if(dict.isEmpty()){
            return res;
        }
        dict.add(end);
        
        //bfs init
        HashMap<String , Integer> distance = new HashMap();
        Queue<String> que = new LinkedList();
        HashSet<String> set = new HashSet();
        int level = 0;
        que.offer(end);
        distance.put(end , level);
        set.add(end); 
        while(!que.isEmpty()){
            level ++;
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                String cur = que.poll();
                for(int j  = 0 ; j < cur.length() ; j ++){
                    for(char ch = 'a' ; ch <= 'z' ; ch++){
                        String newWord = cur.substring(0 , j) + ch + cur.substring(j + 1);
                        if(!dict.contains(newWord)){
                            continue;
                        }
                        if(distance.containsKey(newWord)){
                            continue;
                        }
                        distance.put(newWord , level);
                        que.offer(newWord);
                    }
                }
            }
        }

        
        dfs(start , end , dict ,res , new ArrayList(Arrays.asList(start)) , distance);
        return res;
    }
    private void dfs(String str, String end, Set<String> dict , List<List<String>> res , List<String> cur ,HashMap<String , Integer> distance){
        if(str.equals(end)){
            res.add(new ArrayList(cur));
            return;
        }
        int curdis = Integer.MAX_VALUE;
        List<String> list = new ArrayList();   
        for(int i = 0 ; i < str.length() ; i ++){
            for(char ch = 'a' ; ch <= 'z' ; ch ++){
                String newWord = str.substring(0 , i) + ch + str.substring(i + 1);
                if(!dict.contains(newWord)){
                    continue;
                }
                if(distance.get(newWord) > curdis){
                    continue;
                }
                else if(distance.get(newWord) == curdis){
                    list.add(newWord);
                }
                else{
                    list.clear();
                    curdis = distance.get(newWord);
                    list.add(newWord);
                }
            }
        }
        for(String newWord : list){
            cur.add(newWord);
            dfs(newWord , end , dict , res ,  cur  ,distance);
            cur.remove(cur.size() - 1);
        }
    } 
}