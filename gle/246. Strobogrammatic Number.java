// 246. Strobogrammatic Number
// DescriptionHintsSubmissionsDiscussSolution
// A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

// Write a function to determine if a number is strobogrammatic. The number is represented as a string.

// Example 1:

// Input:  "69"
// Output: true
// Example 2:

// Input:  "88"
// Output: true
// Example 3:

// Input:  "962"
// Output: false

class Solution {
    public boolean isStrobogrammatic(String nums) {
        char[] hash = new char[10];
        Arrays.fill(hash , '#');
        hash[0] = '0';
        hash[1] = '1';
        hash[6] = '9';
        hash[8] = '8';
        hash[9] = '6';

        int s = 0 , e = nums.length() - 1 ;
        while(s < e){
            if(hash[nums.charAt(s++) - '0'] != nums.charAt(e--)){
                return false;
            }
        }
        if(s == e){
            return hash[nums.charAt(s) - '0'] == nums.charAt(s) ;
        }
        return true;
    }
}