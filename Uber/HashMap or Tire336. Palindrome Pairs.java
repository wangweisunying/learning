// 336. Palindrome Pairs
// DescriptionHintsSubmissionsDiscussSolution
// Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

// Example 1:

// Input: ["abcd","dcba","lls","s","sssll"]
// Output: [[0,1],[1,0],[3,2],[2,4]] 
// Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
// Example 2:

// Input: ["bat","tab","cat"]
// Output: [[0,1],[1,0]] 
// Explanation: The palindromes are ["battab","tabbat"]



// The <= in for (int j=0; j<=words[i].length(); j++) is aimed to handle empty string in the input. Consider the test case of ["a", ""];

// Since we now use <= in for (int j=0; j<=words[i].length(); j++) instead of <. 
// There may be duplicates in the output (consider test case ["abcd", "dcba"]). Therefore I put a str2.length()!=0 to avoid duplicates.

// Another way to avoid duplicates is to use Set<List<Integer>> ret = new HashSet<>(); and return new ArrayList<>(ret);

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> res = new ArrayList();
    if(words == null || words.length < 2) return res;
    Map<String , Integer> map = new HashMap();
    for(int i = 0 ; i < words.length ; i++) map.put(words[i] , i);
    for(int i = 0 ; i < words.length ; i++){
        for(int j = 0 ; j <= words[i].length() ; j++){
            String str1 = words[i].substring(0 , j);
            String str2 = words[i].substring(j);
            if(check(str1)){
                String str2Rev = new StringBuilder(str2).reverse().toString();
                if(map.containsKey(str2Rev) && map.get(str2Rev) != i){
                    res.add(new ArrayList(Arrays.asList(map.get(str2Rev) , i)));
                } 
            }
            if(check(str2)){
                String str1Rev = new StringBuilder(str1).reverse().toString();
                // check "str.length() != 0" to avoid duplicates
                if(map.containsKey(str1Rev)&& map.get(str1Rev) != i && str2.length() != 0){
                    res.add(new ArrayList(Arrays.asList(i , map.get(str1Rev))));
                }    
            }
        }
    }
    return res;
    }
    private boolean check (String word){
        int s = 0 , e = word.length() - 1;
        while(s < e){
            if(word.charAt(s++) != word.charAt(e--))return false;
        }
        return true;
    }
}

public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> ret = new ArrayList<>(); 
    if (words == null || words.length < 2) return ret;
    Map<String, Integer> map = new HashMap<String, Integer>();
    for (int i=0; i<words.length; i++) map.put(words[i], i);
    for (int i=0; i<words.length; i++) {
        // System.out.println(words[i]);
        for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
            String str1 = words[i].substring(0, j);
            String str2 = words[i].substring(j);
            if (isPalindrome(str1)) {
                String str2rvs = new StringBuilder(str2).reverse().toString();
                if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(map.get(str2rvs));
                    list.add(i);
                    ret.add(list);
                    // System.out.printf("isPal(str1): %s\n", list.toString());
                }
            }
            if (isPalindrome(str2)) {
                String str1rvs = new StringBuilder(str1).reverse().toString();
                // check "str.length() != 0" to avoid duplicates
                if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length()!=0) { 
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    list.add(map.get(str1rvs));
                    ret.add(list);
                    // System.out.printf("isPal(str2): %s\n", list.toString());
                }
            }
        }
    }
    return ret;
}

private boolean isPalindrome(String str) {
    int left = 0;
    int right = str.length() - 1;
    while (left <= right) {
        if (str.charAt(left++) !=  str.charAt(right--)) return false;
    }
    return true;
}









//tle
class Solution {
    class Node{
        char ch;
        Map<Character , Node> children;
        List<String> wordList;
        Node(char ch){
            this.ch = ch;
            this.children = new HashMap();
            this.wordList = new ArrayList();
        }
    }
    Node root = new Node('R');                                                                                                                                                                      
    private void insert(String word , int index){
        Node cur = root;
        for(char ch : word.toCharArray()){
            cur.children.putIfAbsent(ch , new Node(ch));
            cur = cur.children.get(ch);
            cur.wordList.add(word + "_" + index);
        }
    }
    private List<String> search(String word){
        Node cur = root;
        for(char ch : word.toCharArray()){
            if(!cur.children.containsKey(ch)) return new ArrayList();
            cur = cur.children.get(ch);    
        }
        return cur.wordList;
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> empty = new ArrayList();
        for(int i = 0 ; i < words.length ; i++){
            if(words.length() == 0) empty.add(i);
        }
        for(int j : empty){
            for(int i = 0 ; i < words.length ; i++){
                if(j != i && check(words[i])){
                    List<Integer> list1 = new ArrayList();
                    list1.add(j);
                    list1.add(i);
                    res.add(list1);
                    List<Integer> list2 = new ArrayList();
                    list2.add(i);
                    list2.add(j);
                    res.add(list2);
                }
            }
        }

        for(int i = 0 ; i < words.length; i++){
            insert(words[i] , i);
        }
        for(int i = 0 ; i < words.length ; i++){
            String tmp = reverseWord(words[i]);
            for(String can : search(tmp)){
                String[] x = can.split("_");
                int index = Integer.parseInt(x[1]);
                if(index == i) continue;
                String rest = x[0].substring(tmp.length());
                if(rest.length() == 0)continue;
                if(check(rest)){
                    List<Integer> list = new ArrayList();
                    list.add(i);
                    list.add(index);
                    res.add(list);
                }         
            }
        }
        root.children = new HashMap();
        for(int i = 0 ; i < words.length; i++){
            insert(reverseWord(words[i]) , i);
        }
        for(int i = 0 ; i < words.length ; i++){
            for(String can : search(words[i])){
                String[] x = can.split("_");
                int index = Integer.parseInt(x[1]);
                if(index == i) continue;
                String rest = x[0].substring(words[i].length());
                if(check(rest)){
                    List<Integer> list = new ArrayList();
                    list.add(index);
                     list.add(i);
                    res.add(list);
                }         
            }
        }
        return res;
    }
    private String reverseWord(String str){
        return new StringBuilder(str).reverse().toString();
    }
    private boolean check (String word){
        int s = 0 , e = word.length() - 1;
        while(s < e){
            if(word.charAt(s++) != word.charAt(e--))return false;
        }
        return true;
    }
}  









//TLE dfs 暴力
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList();
        dfs(res , new boolean[words.length] , new ArrayList() , words , "");
        return res;
    }
    private void dfs(List<List<Integer>> res , boolean[] visited , List<Integer> cur , String[] words , String tmp){
        if(cur.size() == 2){
            res.add(new ArrayList(cur));
            return;
        }
        for(int i = 0 ; i < words.length ; i++){
            if(visited[i]) continue;
            if(cur.size() != 0 && !check(tmp + words[i])) continue;
            visited[i] = true;
            cur.add(i);
            dfs(res, visited , cur , words , tmp + words[i]);
            cur.remove(cur.size() -1);
            visited[i] = false;
        }
    }
    private boolean check (String word){
        int s = 0 , e = word.length() - 1;
        while(s < e){
            if(word.charAt(s++) != word.charAt(e--))return false;
        }
        return true;
    }
}