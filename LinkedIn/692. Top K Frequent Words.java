// 692. Top K Frequent Words

// Given a non-empty list of words, return the k most frequent elements.

// Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

// Example 1:
// Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
// Output: ["i", "love"]
// Explanation: "i" and "love" are the two most frequent words.
//     Note that "i" comes before "love" due to a lower alphabetical order.
// Example 2:
// Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
// Output: ["the", "is", "sunny", "day"]
// Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
//     with the number of occurrence being 4, 3, 2 and 1 respectively.
// Note:
// You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
// Input words contain only lowercase letters.
// Follow up:
// Try to solve it in O(n log k) time and O(n) extra space.


class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Arrays.sort(words);
        List<String> res = new ArrayList();
        int ct =  1;
        String pre = words[0];
        TreeMap<Integer , List<String>> map = new TreeMap<>((a , b) ->(b - a));    
        for(int i = 1 ; i < words.length ; i++){
            if(pre.equals(words[i])){
                ++ct;
            }
            else{
                map.computeIfAbsent(ct , x -> new ArrayList()).add(pre);
                ct = 1;
                pre = words[i];
            }
        }
        map.computeIfAbsent(ct , x -> new ArrayList()).add(pre);
        for(int key : map.keySet()){
            for(String word : map.get(key)){
                if(res.size() == k)break;
                res.add(word);
            }
        }
        return res;
    }
}


class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));
        return candidates.subList(0, k);

    }
}
// ["i", "love", "leetcode", "i", "love", "coding"]
// 2
// Output
// ["i","coding"]
// Expected
// ["i","love"]