// 150. Evaluate Reverse Polish Notation
// DescriptionHintsSubmissionsDiscussSolution
// Evaluate the value of an arithmetic expression in Reverse Polish Notation.

// Valid operators are +, -, *, /. Each operand may be an integer or another expression.

// Note:

// Division between two integers should truncate toward zero.
// The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
// Example 1:

// Input: ["2", "1", "+", "3", "*"]
// Output: 9
// Explanation: ((2 + 1) * 3) = 9
// Example 2:

// Input: ["4", "13", "5", "/", "+"]
// Output: 6
// Explanation: (4 + (13 / 5)) = 6
// Example 3:

// Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
// Output: 22
// Explanation: 
//   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
// = ((10 * (6 / (12 * -11))) + 17) + 5
// = ((10 * (6 / -132)) + 17) + 5
// = ((10 * 0) + 17) + 5
// = (0 + 17) + 5
// = 17 + 5
// = 22

// use stack;
class Solution {
    HashSet<String> set = new HashSet(Arrays.asList("+","-","*","/"));
    public int evalRPN(String[] arr) {
        Stack<Integer> stack = new Stack();
        for(int i = 0 ; i < arr.length ; i++){
            if(!set.contains(arr[i])){
                stack.push(Integer.parseInt(arr[i]));
            }
            else{
                int b = stack.pop();
                int a = stack.pop();
                int x = 0;
                if(arr[i].equals("+")){
                    x = a + b; 
                }
                if(arr[i].equals("-")){
                    x = a - b;
                }
                if(arr[i].equals("*")){
                    x = a * b;
                }
                if(arr[i].equals("/")){
                    x = a / b;
                }
                stack.push(x);
            }
        }
        return stack.pop();
    }
}









//tle recursion modify arr is too costly;
class Solution {
    HashSet<String> set = new HashSet(Arrays.asList("+","-","*","/"));
    public int evalRPN(String[] arr) {
        if(arr.length == 1){
            return Integer.parseInt(arr[0]);
        }
        int res = 0;
        int i = 0;
        for(; i < arr.length ; i++){
            if(set.contains(arr[i])){
                break;    
            }
        }
        String x ="";
        if(arr[i].equals("+")){
            x = Integer.parseInt(arr[i - 2]) + Integer.parseInt(arr[i - 1]) + ""; 
        }
        if(arr[i].equals("-")){
            x = Integer.parseInt(arr[i - 2]) - Integer.parseInt(arr[i - 1]) + "";
        }
        if(arr[i].equals("*")){
            x = Integer.parseInt(arr[i - 2]) * Integer.parseInt(arr[i - 1]) + "";
        }
        if(arr[i].equals("/")){
            x = Integer.parseInt(arr[i - 2]) / Integer.parseInt(arr[i - 1]) + "";
        }

        String[] newArr = new String[arr.length - 2];
        
        int tmp = 0;
        for(int j = 0 ; j + tmp < arr.length ; j++){
            if(j == i - 2){
                newArr[j] = x;
                tmp = 2;
                continue;
            }
            newArr[j] = arr[j + tmp];
        }
        return evalRPN(newArr);
    }
}