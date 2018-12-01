// 288. Unique Word Abbreviation
// DescriptionHintsSubmissionsDiscussSolution
// An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

// a) it                      --> it    (no abbreviation)

//      1
//      ↓
// b) d|o|g                   --> d1g

//               1    1  1
//      1---5----0----5--8
//      ↓   ↓    ↓    ↓  ↓    
// c) i|nternationalizatio|n  --> i18n

//               1
//      1---5----0
//      ↓   ↓    ↓
// d) l|ocalizatio|n          --> l10n
// Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
//  A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

// Example:

// Given dictionary = [ "deer", "door", "cake", "card" ]

// isUnique("dear") -> false
// isUnique("cart") -> true
// isUnique("cane") -> false
// isUnique("make") -> true
// The description (A word's abbreviation is unique if no other word from the dictionary has the same abbreviation) is clear however a bit twisting. It took me a few "Wrong Answer"s to finally understand what it's asking for.
// We are trying to search for a word in a dictionary. If this word (also this word’s abbreviation) is not in the dictionary OR this word and only it’s abbreviation in the dictionary. We call a word’s abbreviation unique.
// EX:

// 1) [“dog”]; isUnique(“dig”);   
// //False, because “dig” has the same abbreviation with “dog" and “dog” is already in the dictionary. It’s not unique.

// 2) [“dog”, “dog"]; isUnique(“dog”);  
// //True, because “dog” is the only word that has “d1g” abbreviation.

// 3) [“dog”, “dig”]; isUnique(“dog”);   
// //False, because if we have more than one word match to the same abbreviation, this abbreviation will never be unique.


class ValidWordAbbr {
    
    Map<String ,Set<String>> map;
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap();
        for(String str : dictionary){
            map.computeIfAbsent(ab(str) ,x -> new HashSet()).add(str);
        }
        // System.out.println(map);
    }
    private String ab(String str){
        if(str.length() <= 2){
            return str;
        }
        return str.substring(0 , 1) + (str.length() - 2) +str.substring(str.length() - 1);
    }
    
    public boolean isUnique(String word) {
        String now = ab(word);
        if(!map.containsKey(now)){
            return true;
        }
        if(map.get(now).size() > 1){
            return false;
        }
        return map.get(now).contains(word);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */