// 394. Decode String
// DescriptionHintsSubmissionsDiscussSolution
// Given an encoded string, return it's decoded string.

// The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
// Note that k is guaranteed to be a positive integer.

// You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

// Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers,
//  k. For example, there won't be input like 3a or 2[4].

// Examples:

// s = "3[a]2[bc]", return "aaabcbc".
// s = "3[a2[c]]", return "accaccacc".
// s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

class Solution {
    public String decodeString(String s) {
        Stack<String> stack = new Stack();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while(index < s.length()){
            if(Character.isDigit(s.charAt(index))){
                int ct = 0;
                while(Character.isDigit(s.charAt(index))){
                    ct = 10 * ct + (s.charAt(index) - '0');
                    index ++;
                }
                //这里index 不用++ 因为已经到下一个了
                stack.push(ct + "");
            }
            else if(Character.isLetter(s.charAt(index))){
                sb.append(s.charAt(index++));
            }
            else if(s.charAt(index) == '['){
                stack.push(sb.toString());
                sb.setLength(0);
                index++;
            }
            else{
                String pre = stack.pop();
                int loop = Integer.parseInt(stack.pop());
                String tmp = "";
                while(loop > 0){
                    pre += sb.toString();
                    loop--;
                }
                sb.setLength(0);
                sb.append(pre);
                index++;
            }
        }
        return sb.toString();
    }
}









//push the number into the stack fisrt  when met "[" push curString into the stack , 
// when met "]" pop the pre String first , then the repeat number ,
//  loop curString times ,add to the preString as curString


class Solution {
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        Stack<String> stack = new Stack();
        for(int i = 0 ; i < arr.length ; i++){
            if(Character.isLetter(arr[i])){
                sb.append(arr[i]);
            }
            else if(Character.isDigit(arr[i])){
                int ct = 0;
                while(Character.isDigit(arr[i])){
                    ct = ct * 10 + (arr[i] - '0');
                    i++;
                }
                stack.push("" + ct);
                i-=1;// trick 
            }
            else if(arr[i] == '['){
                stack.push(sb.toString());
                sb.setLength(0);
            }
            else{
                String pre = stack.pop();
                int times = Integer.parseInt(stack.pop());
                for(int j = 0 ; j < times ; j++){
                    pre+= sb.toString();    
                }
                sb.setLength(0);
                sb.append(pre);
            }

        }
        return sb.toString();

    }
}



class Solution {
    public String decodeString(String s) {
        Stack<String> stack = new Stack();
        String res = "";
        int index = 0;
        while(index < s.length()){
            if(Character.isDigit(s.charAt(index))){
                int ct = 0;
                while(Character.isDigit(s.charAt(index))){
                    ct = ct * 10 + s.charAt(index) - '0'; // 
                    index++;
                }
                stack.push(String.valueOf(ct));
            }
            else if(Character.isLetter(s.charAt(index))){
                res += s.charAt(index);
                index++;
            }
            else if(s.charAt(index) == '['){  
                stack.push(res);
                res = "";
                index ++;
            }
            else{ // s.charAt(index) == ']'
                String pre = stack.pop();
                StringBuilder builder = new StringBuilder(pre);
                int ct = Integer.parseInt(stack.pop());
                for(int i = 0 ; i < ct ; i++){
                    builder.append(res);
                }
                res = builder.toString();
                index ++;
            }
            
        }
        
        return res;
    }
}