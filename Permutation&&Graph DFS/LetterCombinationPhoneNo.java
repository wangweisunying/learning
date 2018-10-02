// Given a digit string excluded 01, return all possible letter combinations that the number could represent.

// A mapping of digit to letters (just like on the telephone buttons) is given below.




// Cellphone

// Example
// Given "23"

// Return ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]


public class Solution {
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList();
        if(digits == null || digits.length() == 0){
            return  res;
        }
        char[] arr = digits.toCharArray();
        HashMap<Character , char[]> map = new HashMap();
        map.put('0', new char[] {});
        map.put('1', new char[] {});
        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v'});
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });
        
        dfs(res , arr ,  "" , 0 , map);
        return res;
    }
    private void dfs(List<String> res ,char[] arr ,String cur , int index ,HashMap<Character , char[]> map ){
        if(index > cur.length()){
            return;
        }
        if(arr.length == cur.length()){// can not use index here  cuz cur == "" always after each round;
            res.add(cur);
            return;
        }
        for(int i = index ; i < arr.length ; i++){
            for(char curCh : map.get(arr[i])){
                dfs(res , arr , cur + curCh , i + 1 , map);
            }
        }
    }
 
}





















//组合问题 从前往后取
public class Solution {
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList();
        if(digits == null || digits.length() == 0){
            return  res;
        }
        char[] arr = digits.toCharArray();
        HashMap<Character , char[]> map = new HashMap();
        map.put('0', new char[] {});
        map.put('1', new char[] {});
        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v'});
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });
        StringBuilder sb = new StringBuilder();
        dfs(res , map , sb , arr , 0);
        return res;
    }
    private void dfs(List<String> res , HashMap<Character , char[]> map , StringBuilder sb , char[] arr , int  startIndex){
        if(sb.length() == arr.length){
            res.add(new String(sb.toString()));
            return;
        }
        for(int i = startIndex ; i < arr.length ; i ++){
            for(char cur : map.get(arr[i])){
                sb.append(cur);
                dfs(res , map , sb , arr , i + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}