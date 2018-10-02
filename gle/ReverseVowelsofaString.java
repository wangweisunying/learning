// a，e，i，o，u


// Reverse Vowels of a String
// Write a function that takes a string as input and reverse only the vowels of a string.

// Example
// Example 1:
// Given s = "hello", return "holle".

// Example 2:
// Given s = "lintcode", return "lentcodi".

public class Solution {
    /**
     * @param s: a string
     * @return: reverse only the vowels of a string
     */
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        HashSet<Character> set = new HashSet(Arrays.asList('a' , 'e' , 'i' , 'o' , 'u' , 'A' , 'E' , 'I' , 'O' , 'U'));
        int i = 0 , j = s.length() - 1;
        while(i <= j){
            while( i <= j && !set.contains(arr[i])){
                i++;
            }
            while( i<=j && !set.contains(arr[j])){
                j--;
            }
            if(i <= j){
                char tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i ++;
                j --;
            }  
        } 
        return new String(arr);
    }
}