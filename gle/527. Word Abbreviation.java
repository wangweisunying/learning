// 527. Word Abbreviation
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

// Begin with the first character and then the number of characters abbreviated, which followed by the last character.
// If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character
// until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
// If the abbreviation doesn't make the word shorter, then keep it as original.
// Example:
// Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
// Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
// Note:
// Both n and the length of each word will not exceed 400.
// The length of each word is greater than 1.
// The words consist of lowercase English letters only.
// The return answers should be in the same order as the original array.

//starght forward 做法
class Solution {
    public List<String> wordsAbbreviation(List<String> dict) {
        List<String> res = new ArrayList();
        int offset = 1;
        for(String word : dict){
            res.add(makeAb(word , offset));
        }
        HashMap<String , List<Integer>> map = new HashMap();
        boolean flag = true;
        while(flag){
            map.clear();
            flag = false;
            for(int i = 0 ; i < res.size() ; i++){
                map.computeIfAbsent(res.get(i) , x -> new ArrayList()).add(i);    
            }
            ++offset;
            for(List<Integer> list : map.values()){
                // System.out.println(list);
                if(list.size() <= 1){
                    continue;
                }
                flag = true;
                for(int index : list){
                    res.set(index , makeAb(dict.get(index) , offset));
                }
            }
        }
        return res;
    }
    private String makeAb(String word , int len){
        // word  wo1d;
        if(word.length() <= 3 || word.length() - len == 2 )  return word;
        StringBuilder sb = new StringBuilder();
        sb.append(word.substring(0 , len));
        sb.append(word.length() - len - 1);
        sb.append(word.charAt(word.length() - 1 ));
        return sb.toString(); 
    }
}





















   public List<String> wordsAbbreviation(List<String> dict) {
        int len=dict.size();
        String[] ans=new String[len];
        int[] prefix=new int[len];
        for (int i=0;i<len;i++) {
            prefix[i]=1;
            ans[i]=makeAbbr(dict.get(i), 1); // make abbreviation for each string
        }
        for (int i=0;i<len;i++) {
            while (true) {
                HashSet<Integer> set=new HashSet<>();
                for (int j=i+1;j<len;j++) {
                    if (ans[j].equals(ans[i])) set.add(j); // check all strings with the same abbreviation
                }
                if (set.isEmpty()) break;
                set.add(i);
                for (int k: set) 
                    ans[k]=makeAbbr(dict.get(k), ++prefix[k]); // increase the prefix
            }
        }
        return Arrays.asList(ans);
    }

    private String makeAbbr(String s, int k) {
        if (k>=s.length()-2) return s;
        StringBuilder builder=new StringBuilder();
        builder.append(s.substring(0, k));
        builder.append(s.length()-1-k);
        builder.append(s.charAt(s.length()-1));
        return builder.toString();
    }