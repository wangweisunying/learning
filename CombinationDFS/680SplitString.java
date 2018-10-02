// Give a string, you can choose to split the string after one character or two adjacent characters, 
// and make the string to be composed of only one character or two characters.
//  Output all possible results.

// Example
// Given the string "123"
// return [["1","2","3"],["12","3"],["1","23"]]

public class Solution {
    /*
     * @param : a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        List<List<String>> res = new Arraylist()
        
        
        char[] arr = s.toCharArray();
        dfs(arr , res, 0 , new Arraylist());
        return res;
    }
    private void dfs(char[] arr,List<List<String>> res , int startIndex , List<String> cur){
        if(startIndex == arr.length){
            res.add(new ArrayList(cur));
            return;
        }
        if(startIndex < arr.length){
            cur.add(arr[startIndex] + "");
            dfs(arr , res , startIndex + 1 , cur);
            cur.remove(cur.size() - 1);
        }
        
        if(startIndex + 1 < arr.length){
            cur.add(arr[startIndex] + "" + arr[startIndex + 1]);
            dfs(arr , res , startIndex + 2 , cur);
            cur.remove(cur.size() - 1);
        }
        
    }
}















































public class Solution { // 画出递归树！
    /*
     * @param : a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        List<List<String>> res = new ArrayList(); 
        dfs(res , s , 0 , new ArrayList());
        return res;
    }
    private  void dfs(List<List<String>> res, String s , int index ,List<String> cur){
        if(s == null){
            return;
        }
        if(s.length() == 0){
            res.add(new ArrayList());
            return;
        }
        if(index == s.length()){
            res.add(new ArrayList(cur));
            return;
        }
// backtracking only 2 options so loop 2 times or 看成subsets 只让取 到 之后的2个元素
        for(int i = index ; i < index + 2 ; i++){ 
            if(i < s.length()){ // dont fall out of the bound 
                cur.add(s.substring(index , i + 1));
                dfs(res , s , i + 1 , cur); 
                cur.remove(cur.size() - 1);
            }      
        }

// 2 options + 1 or + 2 elemets 
        // cur.add(String.valueOf(s.charAt(index)));
        // dfs(res , s ,index + 1 , cur );
        // cur.remove(cur.size() - 1);
        // if(index + 1 < s.length()){
        //     cur.add(String.valueOf(s.substring(index,index + 2)));
        //     dfs(res , s ,index + 2 , cur );
        //     cur.remove(cur.size() - 1);
        // }  



        // for (int i = index; i < index + 2 && i < s.length(); i++) {
        //     String substring = s.substring(index, i + 1);
        //     result.add(substring);
        //     dfsHelper(results, result, i + 1, s);
        //     result.remove(result.size() - 1);
        // }
    }
}


public class Solution {
    /*
     * @param : a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        // write your code here
        List<List<String>> results = new ArrayList<>();
        if (s == null) {
            return results;
        } else if (s.length() == 0) {
            results.add(new ArrayList<>());
            return results;
        }
        
        dfsHelper(results, new ArrayList<>(), 0, s);
        
        return results;
    }
    
    private void dfsHelper(List<List<String>> results,
                           List<String> result,
                           int index,
                           String s) {
        if (index == s.length()) {
            results.add(new ArrayList<>(result));
            return;
        }
        
        for (int i = index; i < index + 2 && i < s.length(); i++) {
            String substring = s.substring(index, i + 1);
            result.add(substring);
            dfsHelper(results, result, i + 1, s);
            result.remove(result.size() - 1);
        }
    }
}