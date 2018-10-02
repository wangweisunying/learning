// Minimum String Array Coverage
// Given a string collection tag_list, an array of strings all_tags, find the smallest all_tags
//  sub-array contains all the string in the tag_list, output the length of this sub-array. If there is no return -1.

// Example
// Given tag_list = ["made","in","china"], all_tags = ["made", "a","b","c","in", "china","made","b","c","d"], return 3.

// Explanation:
// ["in", "china","made"] contains all the strings in tag_list,6 - 4 + 1 = 3.
// Given tag_list = ["a"], all_tags = ["b"], return -1.

// Explanation:
// Does not exist.
// Notice
// 1 <= |tag_list|,|all_tags| <=10000
// All string length <= 50


public class Solution {
    /**
     * @param tagList: The tag list.
     * @param allTags: All the tags.
     * @return: Return the answer
     */
    public int getMinimumStringArray(String[] tagList, String[] allTags) {
        if(allTags.length < tagList.length){
            return -1;
        }
        HashSet<String> t_map = new HashSet();
        HashMap<String , Integer> a_map = new HashMap();
        for(String tmp : tagList){
            t_map.add(tmp);
        }
        int i = 0 , j = 0 , res = Integer.MAX_VALUE;
        for(;i < allTags.length ; i++){
            while(j < allTags.length && a_map.size() != t_map.size()){
                if(!t_map.contains(allTags[j])){
                    j++;
                    continue;
                }
                if(a_map.containsKey(allTags[j])){
                    a_map.put(allTags[j] , a_map.get(allTags[j]) + 1);
                }
                else{
                    a_map.put(allTags[j] , 1);
                }
                j++;
            }
            if(a_map.size() == t_map.size()){
                res = Math.min(res , j - i);
            }
            if(!t_map.contains(all_tags[i])){
                continue;
            }
            if(a_map.get(allTags[i]) == 1){
                a_map.remove(allTags[i]);
            }
            else{
                a_map.put(allTags[i] ,a_map.get(allTags[i]) - 1);
            }
        }
        if(res == Integer.MAX_VALUE){
            return -1;
        }
        return res;
    }

}



public class Solution {
    /**
     * @param tagList: The tag list.
     * @param allTags: All the tags.
     * @return: Return the answer
     */
    public int getMinimumStringArray(String[] tagList, String[] allTags) {
        // Write your code here
        Map<String, Integer> mp1 = new HashMap<String, Integer>();
        Map<String, Integer> mp2 = new HashMap<String, Integer>();
        for(int i = 0; i < tagList.length; i++) {
            mp1.put(tagList[i], 1);
        }
        int ans = allTags.length + 1;
        int l = 0;
        for(int r = 0; r < allTags.length; r++) {
            if(mp1.containsKey(allTags[r])) {
                if(!mp2.containsKey(allTags[r])) {
                    mp2.put(allTags[r], 1);
                } else {
                    mp2.put(allTags[r], mp2.get(allTags[r]) + 1);
                }
                while(l <= r && mp2.size() == tagList.length) {
                    if(r - l + 1 < ans) {
                        ans = r - l + 1;
                    }
                    if(mp1.containsKey(allTags[l])) {
                        if(mp2.get(allTags[l]) == 1) {
                            mp2.remove(allTags[l]);
                        } else {
                            mp2.put(allTags[l], mp2.get(allTags[l]) - 1);
                        }
                    }
                    l++;
                }
            }
        }
        if(ans == allTags.length + 1) {
            return -1;
        }
        return ans;
    }
}