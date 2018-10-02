// Palindrome Pairs
// Given a list of unique words, find all pairs of** distinct** indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

// Example
// Given words = ["bat", "tab", "cat"]
// Return [[0, 1], [1, 0]]
// The palindromes are ["battab", "tabbat"]

// Given words = ["abcd", "dcba", "lls", "s", "sssll"]
// Return [[0, 1], [1, 0], [3, 2], [2, 4]]
// The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]



public class Solution {
    /**
     * @param words: a list of unique words
     * @return: all pairs of distinct indices
     */
    TrieNode root;
    boolean Triereverse = false;
    class TrieNode{
        char c; 
        HashMap< Character, TrieNode > sons;
        String word;
        int idx;
        TrieNode(char c){
            this.c = c;
            sons = new HashMap(); 
            this.word = null;
            idx = -1;
        }
    }
    void insert(String str, int idx){
        TrieNode d = root;
        for (char c : str.toCharArray()){
            if (!d.sons.containsKey(c)){
                d.sons.put(c, new TrieNode(c));
            }
            d = d.sons.get(c);
        }
        d.word = str;
        d.idx = idx;
    }
    List<List<Integer>> find(String word , int index){
        List<List<Integer>> res = new ArrayList();
        char[] arr = word.toCharArray();
        TrieNode d = root;
        if(d.word != null && d.idx != index && isP(arr , 0 ,arr.length - 1)){
            if(Triereverse){
                res.add(new ArrayList(Arrays.asList( index , d.idx)));
            }
            else{
                res.add(new ArrayList(Arrays.asList(  d.idx , index)));
            }
        }
        for(int i = arr.length - 1 ; i >=0 ; i --){
            d = d.sons.get(arr[i]);
            if(d == null){
                break;
            }
            if(d.word != null  && d.idx != index && isP(arr , 0 , i - 1)){
                if(Triereverse){
                    res.add(new ArrayList(Arrays.asList( index , d.idx)));
                }
                else{
                    res.add(new ArrayList(Arrays.asList(  d.idx , index)));
                }
            } // compare from back check the front is P or not

        }
        return res;

    }
    boolean isP(char[] arr , int s , int e){
        //排除 tab bat 重复结果
        if (Triereverse && s == e + 1){
            return false;
        }
        while(s < e){
            if(arr[s++] == arr[e--]){
                continue;
            }
            return false;
        }
        return true;
    }



    List<List<Integer>> helper(String[] words){
        List<List<Integer>> res = new ArrayList();
        root = new TrieNode('+');
        for(int i = 0 ; i < words.length ; i ++){
            insert(words[i] , i);
        }
        
        for(int i = 0 ; i < words.length ; i ++){
            res.addAll(find(words[i] , i));
        }
        return res;
    }
    
    

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList();
        res.addAll(helper(words));
        Triereverse = true;
        for(int i = 0 ; i < words.length ; i ++){
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }
        res.addAll(helper(words));
        return res;
    }
}






public class Solution {
        //正向存入 反向比
        //反向存入 正向比
        // Search twice in the trie, the second time reverse the string. 
        // Keep in mind that: The info in the trie is always equal or shorter than the matching string.
        //  We deal with the case that info in the trie is longer than matching str in the reverse pass.
    
    class TrieNode{ 
        HashMap< Character, TrieNode > sons = new HashMap();
        String word;
        int idx;
    }
    
    TrieNode root;
    
    void insert(String str, int idx){
        TrieNode d = root;
        for (char c : str.toCharArray()){
            if (!d.sons.containsKey(c)){
                d.sons.put(c, new TrieNode());
            }
            d = d.sons.get(c);
        }
        d.word = str;
        d.idx = idx;
    }

    boolean checkPalin(char[] A, int lo, int hi){
        // Keep in mind that: The info in the trie is always equal or shorter than the matching string.
        //  We deal with the case that info in the trie is longer than matching str in the reverse pass.
        
        if (reverse && lo > hi){
            return false;
        }
        while (lo < hi){
            if (A[lo++] != A[hi--]){
                return false;
            }
        }
        return true;
    }
    
    List<List<Integer>> find(String str, int idx){
        List<List<Integer>> ans = new ArrayList<>();
        char[] A = str.toCharArray();
        TrieNode d = root;
        if (d.word != null && d.idx != idx && checkPalin(A, 0, A.length - 1)){
            // deal with empty string as input
            ans.add(reverse ? Arrays.asList(idx, d.idx) : Arrays.asList(d.idx, idx));
        }
        for (int i = A.length - 1; i >= 0; i--){
            d = d.sons.get(A[i]);
            if (d == null){
                break;
            }
            if (d.word != null && d.idx != idx && checkPalin(A, 0, i - 1)){
                // found a word, how check whether the remainder of str is palin
                ans.add(reverse ? Arrays.asList(idx, d.idx) : Arrays.asList(d.idx, idx));
            }
        }
        return ans;
    }
    
    /**
     * @param words: a list of unique words
     * @return: all pairs of distinct indices
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        // Write your code here
        List<List<Integer>> ans = helper(words);
        for (int i = 0; i < words.length; i++){
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }

        reverse = true;

        ans.addAll(helper(words));

        return ans;
    }
    
    boolean reverse = false;
    
    List<List<Integer>> helper(String[] words){
        List<List<Integer>> ans = new ArrayList<>();
        root = new TrieNode();
        for (int i = 0; i < words.length; i++){
            insert(words[i], i);
        }
        for (int i = 0; i < words.length; i++){
            ans.addAll(find(words[i], i));
        }
        return ans;
    }
}



public class Solution {
        //正向存入 反向比
        //反向存入 正向比
        // Search twice in the trie, the second time reverse the string. 
        // Keep in mind that: The info in the trie is always equal or shorter than the matching string.
        //  We deal with the case that info in the trie is longer than matching str in the reverse pass.
    
    class TrieNode{ 
        TrieNode[] sons = new TrieNode[26];
        String word;
        int idx;
    }
    
    TrieNode root;
    
    void insert(String str, int idx){
        TrieNode d = root;
        for (char c : str.toCharArray()){
            if (d.sons[c - 'a'] == null){
                d.sons[c - 'a'] = new TrieNode();
            }
            d = d.sons[c - 'a'];
        }
        d.word = str;
        d.idx = idx;
    }

    boolean checkPalin(char[] A, int lo, int hi){
        if (reverse && lo > hi){
            return false;
        }
        while (lo < hi){
            if (A[lo++] != A[hi--]){
                return false;
            }
        }
        return true;
    }
    
    List<List<Integer>> find(String str, int idx){
        List<List<Integer>> ans = new ArrayList<>();
        char[] A = str.toCharArray();
        TrieNode d = root;
        if (d.word != null && d.idx != idx && checkPalin(A, 0, A.length - 1)){
            // deal with empty string as input
            ans.add(reverse ? Arrays.asList(idx, d.idx) : Arrays.asList(d.idx, idx));
        }
        for (int i = A.length - 1; i >= 0; i--){
            d = d.sons[A[i] - 'a'];
            if (d == null){
                break;
            }
            if (d.word != null && d.idx != idx && checkPalin(A, 0, i - 1)){
                // found a word, how check whether the remainder of str is palin
                ans.add(reverse ? Arrays.asList(idx, d.idx) : Arrays.asList(d.idx, idx));
            }
        }
        return ans;
    }
    
    /**
     * @param words: a list of unique words
     * @return: all pairs of distinct indices
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        // Write your code here
        List<List<Integer>> ans = helper(words);
        for (int i = 0; i < words.length; i++){
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }

        reverse = true;

        ans.addAll(helper(words));

        return ans;
    }
    
    boolean reverse = false;
    
    List<List<Integer>> helper(String[] words){
        List<List<Integer>> ans = new ArrayList<>();
        root = new TrieNode();
        for (int i = 0; i < words.length; i++){
            insert(words[i], i);
        }
        for (int i = 0; i < words.length; i++){
            ans.addAll(find(words[i], i));
        }
        return ans;
    }
}













public class Solution {
public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if(words == null || words.length == 0){
        return res;
    }
    //build the map save the key-val pairs: String - idx
    HashMap<String, Integer> map = new HashMap<>();
    for(int i = 0; i < words.length; i++){
        map.put(words[i], i);
    }
    
    //special cases: "" can be combine with any palindrome string
    if(map.containsKey("")){
        int blankIdx = map.get("");
        for(int i = 0; i < words.length; i++){
            if(isPalindrome(words[i])){
                if(i == blankIdx) continue;
                res.add(Arrays.asList(blankIdx, i));
                res.add(Arrays.asList(i, blankIdx));
            }
        }
    }
    
    //find all string and reverse string pairs
    for(int i = 0; i < words.length; i++){
        String cur_r = reverseStr(words[i]);
        if(map.containsKey(cur_r)){
            int found = map.get(cur_r);
            if(found == i) continue;
            res.add(Arrays.asList(i, found));
        }
    }
    
    //find the pair s1, s2 that 
    //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
    //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
    for(int i = 0; i < words.length; i++){
        String cur = words[i];
        for(int cut = 1; cut < cur.length(); cut++){
            if(isPalindrome(cur.substring(0, cut))){
                String cut_r = reverseStr(cur.substring(cut));
                if(map.containsKey(cut_r)){
                    int found = map.get(cut_r);
                    if(found == i) continue;
                    res.add(Arrays.asList(found, i));
                }
            }
            if(isPalindrome(cur.substring(cut))){
                String cut_r = reverseStr(cur.substring(0, cut));
                if(map.containsKey(cut_r)){
                    int found = map.get(cut_r);
                    if(found == i) continue;
                    res.add(Arrays.asList(i, found));
                }
            }
        }
    }
    
    return res;
}

public String reverseStr(String str){
    StringBuilder sb= new StringBuilder(str);
    return sb.reverse().toString();
}

public boolean isPalindrome(String s){
    int i = 0;
    int j = s.length() - 1;
    while(i <= j){
        if(s.charAt(i) != s.charAt(j)){
            return false;
        }
        i++;
        j--;
    }
    return true;
}
}