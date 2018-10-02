// First Unique Character in a String
// Find the first unique character in a given string. You can assume that there is at least one unique character in the string.

// Example
// For "abaccdeff", return 'b'.
public class Solution {
    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        TreeMap<Character , Integer> map = new TreeMap();
        for(char c : str.toCharArray()){
            if(map.containsKey(c)){
                map.put(c , map.get(c) + 1);
            }
            else{
                map.put(c , 1);
            }
            
        }
        for(char key : map.keySet()){
            if(map.get(key) == 1){
                return key;
            }
        }
        return 'd';
    }
}




































public class Solution {
    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        List<Character> list = new ArrayList();
        HashSet<Character> set = new HashSet();
        int i = 0;
        for( ; i < str.length() ; i ++){
            if(set.contains(str.charAt(i))){
                list.remove((Object)str.charAt(i));
            }
            else{
                set.add(str.charAt(i));
                list.add(str.charAt(i));
            }
        } 
        return list.get(0);
    }
}