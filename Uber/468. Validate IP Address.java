// 468. Validate IP Address
// Medium


// Share
// Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

// IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

// Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

// IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":").
//  For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits 
//  and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

// However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. 
// For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

// Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

// Note: You may assume there is no extra space or special characters in the input string.

// Example 1:
// Input: "172.16.254.1"

// Output: "IPv4"

// Explanation: This is a valid IPv4 address, return "IPv4".
// Example 2:
// Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

// Output: "IPv6"

// Explanation: This is a valid IPv6 address, return "IPv6".
// Example 3:
// Input: "256.256.256.256"

// Output: "Neither"

// Explanation: This is neither a IPv4 address nor a IPv6 address.


class Solution {
    public String validIPAddress(String IP) {
        if(IPv4(IP)) return "IPv4";
        if(IPv6(IP)) return "IPv6";
        return "Neither";
    }
    private boolean IPv4(String s){
        String x = s.replaceAll("\\.","");
        if(!x.matches("[0-9]*"))return false;
        int ct = 0;
        for(char c : s.toCharArray()){
            if(c == '.')++ct;
        }
        if(ct != 3) return false;

        String[] arr = s.split("\\.");
        if(arr.length != 4) return false;
        for(String str : arr){
            if(str.length() > 4 ) return false;
            if(str.length() == 0) return false;
             if(str.length() > 1 && str.startsWith("0")) return false;
            if(Integer.parseInt(str) > 255 || Integer.parseInt(str) < 0) return false;
        }
        return true;
    }
    private boolean IPv6(String s){
        String x = s.replaceAll(":","");
        if(!x.matches("[a-fA-F0-9]*"))return false;
        int ct = 0;
        for(char c : s.toCharArray()){
            if(c == ':')++ct;
        }
        if(ct != 7) return false;


        String[] arr = s.split(":");
        if(arr.length != 8) return false;
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i].length() == 5 ){
                return i == 0 && arr[i].startsWith("0");
            }
            if(arr[i].length() == 0 || arr[i].length() > 4) return false;
        }
        return true;
    }
}