// Given a string, find all permutations of it without duplicates.

// Example
// Given "abb", return ["abb", "bab", "bba"].

// Given "aabb", return ["aabb", "abab", "baba", "bbaa", "abba", "baab"].


public class Solution {
    /**
     * @param str: A string
     * @return: all permutations
     */
    public List<String> stringPermutation2(String s) {
        List<String> res = new ArrayList();
        if(s == null){
            return res;
        }
        char[] arr = s.toCharArray(); // use to CharArray instead of split("");
        Arrays.sort(arr);
        boolean[] visited  = new boolean[s.length()];
        permutate(res , arr , visited , "");
        return res;
    }
    private void permutate(List<String> res , char[] arr , boolean[] visited ,String cur){
        if(cur.length() == arr.length){
            res.add(new String(cur));
        }

        for(int i = 0 ; i < arr.length ; i++){
            if(visited[i]){
                continue;
            }
            if(i > 0 && arr[i] == arr[i - 1] && visited[i - 1] == false){
                continue;
            }

            visited[i] = true;
            permutate(res , arr , visited , cur + arr[i]);
            visited[i] = false;
        }
    }
}


public class Solution {
    /*
     * @param str: A string
     * @return: all permutations
     */
    public List<String> stringPermutation2(String str) {
        // write your code here
        List<String> result = new ArrayList<String>();
        if(str==null) {
            return result;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        int length = str.length();
        boolean[] visited = new boolean[length];
        String one = "";
        getList(chars, one, visited, result);
        return result;
    }
    
    public void getList(char[] chars, String one, boolean[] visited, List<String> result) {
        if(one.length()==chars.length) {
            result.add(one);
            return;
        }
        for(int i = 0; i < chars.length; i++) {
            if(visited[i] || (i>0 && chars[i]==chars[i-1] && visited[i-1]==false)) {
                continue;
            }
            one += chars[i];
            visited[i] = true;
            getList(chars, one, visited, result);
            one = one.substring(0, one.length()-1);
            visited[i] = false;
        }
    }
}