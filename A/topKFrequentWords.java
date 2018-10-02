// Top K Frequent Words
// Given a list of words and an integer k, return the top k frequent words in the list.

// Example
// Given

// [
//     "yes", "lint", "code",
//     "yes", "code", "baby",
//     "you", "baby", "chrome",
//     "safari", "lint", "code",
//     "body", "lint", "code"
// ]
// for k = 3, return ["code", "lint", "baby"].

// for k = 4, return ["code", "lint", "baby", "yes"],

// Challenge
// Do it in O(nlogk) time and O(n) extra space.


public class Solution {
    /**
     * @param words: an array of string
     * @param k: An integer
     * @return: an array of string
     */
    class Pair{
        String str ;
        int ct;
        Pair(String str , int ct){
            this.str = str;
            this.ct = ct;
        }
    }
    public String[] topKFrequentWords(String[] words, int k) {
        HashMap<String , Pair> map = new HashMap();
        for(String str : words){
            if(map.containsKey(str)){
                map.get(str).ct ++ ;
            }
            else{
                map.put(str , new Pair(str , 1));
            }
        }
        Queue<Pair> que = new PriorityQueue<Pair>(new Comparator<Pair>(){
            @Override
            public int compare(Pair p1 , Pair p2){
                if(p2.ct - p1.ct != 0){
                    return p1.ct - p2.ct;
                }
                else{
                    return p1.str.compareTo(p2.str);
                }       
            }
        });
        for(String str : map.keySet()){
            que.offer(map.get(str));
        }
        String[] res = new String[k];
        int i = 0 ;
        while(k > 0){
            res[i++] = que.poll().str;
            k--;
        }
        return res;

    }
}