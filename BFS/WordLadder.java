// Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

// Only one letter can be changed at a time
// Each intermediate word must exist in the dictionary
// Example
// Given:
// start = "hit"
// end = "cog"
// dict = ["hot","dot","dog","lot","log"]
// As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
// return its length 5.

public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
        public int ladderLength(String start, String end, Set<String> dict) {
        int ct = 1;
        if(dict == null){
            return 0;
        }
        if(start.equals(end)){
            return 1;
        }
        dict.add(start);
        dict.add(end); //make sure end appear in getNextWords
        Queue<String> que = new LinkedList();
        HashSet<String> set = new HashSet(); //get rid of visited points
        que.offer(start);
        set.add(start);
        
        while(!que.isEmpty()){
            ct++;
            int size = que.size();
            for(int i = 0 ; i < size ; i ++){
                String word = que.poll();
                for(String nextWord : getNextWords(word , dict)){
                    if(set.contains(nextWord)){//get rid of visited points
                        continue;
                    }
                    if(nextWord.equals(end)){
                        return ct;
                    }
                    que.offer(nextWord);
                    set.add(nextWord);            
                }
            }
        }
        System.out.println(ct);
        return 0 ;  
    }
    private ArrayList<String> getNextWords(String word ,Set<String> dict){
        ArrayList<String> nextWords = new ArrayList();
        String origin = word;
        for(int i = 0 ; i < origin.length() ; i ++){
            word = origin;
            for(int j = 0 ; j < 26 ; j ++){
                if(origin.charAt(i) == (char)(97 + j)){
                    continue; //get rid of the coming node 
                }
                word = word.substring(0, i) + (char)(97 + j) + word.substring(i + 1);
                if(dict.contains(word)){
                    nextWords.add(word);
                }         
            }
        }
        return nextWords;
    }
}


















public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if (dict == null) {
            return 0;
        }

        if (start.equals(end)) {
            return 1;
        }
        
        dict.add(start);
        dict.add(end);

        HashSet<String> hash = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        hash.add(start);
        
        int length = 1;
        while(!queue.isEmpty()) {
            length++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nextWord: getNextWords(word, dict)) {
                    if (hash.contains(nextWord)) {
                        continue;
                    }
                    if (nextWord.equals(end)) {
                        return length;
                    }
                    
                    hash.add(nextWord);
                    queue.offer(nextWord);
                }
            }
        }
        return 0;
    }

    // replace character of a string at given index to a given character
    // return a new string
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
    
    // get connections with given word.
    // for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
    // it will return ['hit', 'hog']
    private ArrayList<String> getNextWords(String word, Set<String> dict) {
        ArrayList<String> nextWords = new ArrayList<String>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
}