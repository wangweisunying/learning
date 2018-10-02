//    s = abc3[a] return abcaaa
//    s = 3[abc] return abcabcabc
//    s = 4[ac]dy, return acacacacdy
//    s = 3[2[ad]3[pf]]xyz, return adadpfpfpfadadpfpfpfadadpfpfpfxyz

public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        Stack<Object> stack = new Stack();
        int number = 0;
        char[] arr = s.toCharArray();
        
        for(char ch : arr){
            if(Character.isDigit(ch)){
                number = number * 10  + (ch - '0');
            }
            else if(ch == '['){
                stack.push(Integer.valueOf(number));
                number = 0;
            }
            else if(ch == ']'){
                String tmp = popStack(stack);
                int ct = (Integer)stack.pop();
                for(int i = 0 ; i < ct ; i++){
                    stack.push(tmp);
                }
            }
            else{
                stack.push(String.valueOf(ch));
            }
            
        }
        return popStack(stack);
    }
    private String popStack(Stack<Object> stack){
        String res = "";
        while(!stack.isEmpty() && (stack.peek() instanceof String)){
            res = stack.pop() + res;
        }   
        return res;
    }
}


public class Solution {
    /**
     * @param s  an expression includes numbers, letters and brackets
     * @return a string
     */
    public String expressionExpand(String s) {
        Stack<Object> stack = new Stack<>();
        int number = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            } else if (c == '[') {
                stack.push(Integer.valueOf(number));
                number = 0;
            } else if (c == ']') {
                String newStr = popStack(stack);
                Integer count = (Integer) stack.pop();
                for (int i = 0; i < count; i++) {
                    stack.push(newStr);
                }
            } else {
                stack.push(String.valueOf(c));
            }
        }
        
        return popStack(stack);
    }
    
    private String popStack(Stack<Object> stack) {
        // pop stack until get a number or empty
        Stack<String> buffer = new Stack<>();
        while (!stack.isEmpty() && (stack.peek() instanceof String)) {
            buffer.push((String) stack.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        while (!buffer.isEmpty()) {
            sb.append(buffer.pop());
        }
        return sb.toString();
    }
} 








public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        String res = "";
        String subString = "";
        int level = 0 ;
        int number = 0;
        
        char[] arr = s.toCharArray();
    
        for(char ch : arr){
            if(ch == '['){
                if(level > 0){
                    subString += ch;
                }
                level++;
            }else if(ch == ']'){
                level--;
                if(level > 0 ){
                    subString += ch;
                }
                else{
                    String tmp = expressionExpand(subString);
                    for(int i = 0 ; i < number ; i++){
                        res += tmp;
                    }   
                    number = 0;
                    subString = "";
                }
            }
            else if(ch >= '0' && ch < '9'){
                if(level == 0){
                    number = number * 10 + (ch - '0');
                }
                else{
                    subString += ch; 
                }
            }
            else{
                if(level == 0){
                    res += ch; 
                }   
                else{
                    subString += ch;
                }

            }

        }

    }
}




// version 2: Recursion
public class Solution {
    /**
     * @param s  an expression includes numbers, letters and brackets
     * @return a string
     */
    public String expressionExpand(String s) {
        String res = "";
        String subString = "";
        int level = 0;
        int number = 0;
        char[] arr = s.toCharArray();
        
        for(char c : arr){  
            if(c == '['){
                if(level > 0){ //level == 0 , if meet ], ] won't go inside the substring but start recursion
                    subString += c;
                }
                level++;
            }
            else if(c == ']'){
                level--;
                if(level == 0){
                   String subRes = expressionExpand(subString);
                   for(int i = 0 ; i < number ; i++){
                       res += subRes;
                   }
                   number = 0;
                   subString = "";
                }
                else{
                   subString += c;
                }
            }
            else if(c >= '0' && c <= '9'){ // number
                if(level == 0){
                    number = 10 * number + (c - '0'); // has multipe number
                }
                else{
                    subString += c;
                }
            }
            else{ // letter
                if(level == 0){
                    res += c;
                }else{
                    subString += c;
                } 
            
            }
        }

        return res;
    }
}



