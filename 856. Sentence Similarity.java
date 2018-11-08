
// 856. Sentence Similarity
// Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

// For example, words1 = great acting skills and words2 = fine drama talent are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

// Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

// However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

// Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

// Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

// Notice
// 1.The length of words1 and words2 will not exceed 1000.
// 2.The length of pairs will not exceed 2000.
// 3.The length of each pairs[i] will be 2.
// 4.The length of each words[i] and pairs[i][j] will be in the range [1, 20].


public class Solution {
    /**
     * @param words1: a list of string
     * @param words2: a list of string
     * @param pairs: a list of string pairs
     * @return: return a boolean, denote whether two sentences are similar or not
     */
    public boolean isSentenceSimilarity(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length){
            return false;
        }
        Map<String ,Set<String>> map = new HashMap();
        for(List<String> list : pairs){
            map.computeIfAbsent(list.get(0) , x -> new HashSet()).add(list.get(1));
            map.computeIfAbsent(list.get(1) , x -> new HashSet()).add(list.get(0));
        }
        for(int i = 0 ; i < words1.length ; i++){
            if(!words1[i].equals(words2[i])){
                if(!map.get(words1[i]).contains(words2[i])){
                    return false;
                }
            }
        }
        return true;
        
    }
}