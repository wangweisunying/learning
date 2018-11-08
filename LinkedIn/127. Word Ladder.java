// 127. Word Ladder
// DescriptionHintsSubmissionsDiscussSolution
// Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

// Only one letter can be changed at a time.
// Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
// Note:

// Return 0 if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.
// You may assume no duplicates in the word list.
// You may assume beginWord and endWord are non-empty and are not the same.
// Example 1:

// Input:
// beginWord = "hit",
// endWord = "cog",
// wordList = ["hot","dot","dog","lot","log","cog"]

// Output: 5

// Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
// return its length 5.
// Example 2:

// Input:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log"]

// Output: 0

// Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.


class Solution {
    public int ladderLength(String start, String end, List<String> wordList) {
        int level = 0;
        
        //用O(1)
        HashSet set = new HashSet(wordList);
        if(!set.contains(end)){
            return level;
        }
        
        
        Queue<String> que = new LinkedList();
        HashSet<String> visited = new HashSet();
        que.offer(start);
        visited.add(start);
        
        while(!que.isEmpty()){
            level++;
            int size = que.size();
            for(int i = 0 ; i < size ;i++){
                String cur = que.poll();
                for(int j = 0 ; j < cur.length() ; j++){
                    for(char ch = 'a' ; ch <= 'z' ; ch ++){
                        String next = cur.substring(0 , j) + ch + cur.substring(j + 1);
                        if(!set.contains(next) || visited.contains(next)){
                            continue;
                        }
                        if(next.equals(end)){
                            return level + 1;
                        }
                        visited.add(next);
                        que.offer(next);
                    }
                }
            }
        }
        return 0;
    }
}