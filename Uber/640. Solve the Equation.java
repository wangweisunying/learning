// 640. Solve the Equation
// DescriptionHintsSubmissionsDiscussSolution
// Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

// If there is no solution for the equation, return "No solution".

// If there are infinite solutions for the equation, return "Infinite solutions".

// If there is exactly one solution for the equation, we ensure that the value of x is an integer.

// Example 1:
// Input: "x+5-3+x=6+x-2"
// Output: "x=2"
// Example 2:
// Input: "x=x"
// Output: "Infinite solutions"
// Example 3:
// Input: "2x=x"
// Output: "x=0"
// Example 4:
// /"-99x=99"
// Your stdout
// [-100, 0]
// [0, 99]
// Your answer
// "x=0"

class Solution {
    public String solveEquation(String equation) {
        String[] arr = equation.split("=");
        int[] left = helper(arr[0]);
        int[] right = helper(arr[1]);
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        if(left[0] == right[0]){
            if(left[1] == right[1]) return "Infinite solutions";
            else return "No solution";
        }
        else{
            if(left[0] < right[0]){
                return "x=" + (left[1] - right[1]) / (right[0] - left[0]);  
            }
            else{
                return "x=" + (left[1] - right[1]) / (right[0] - left[0]);
            }
        }
        
    }
    private int[] helper(String str){
      
        int sum = 0;
        int sumX = 0;
        String oper = "";
        for(int i = 0 ; i < str.length() ; i++){
            if(Character.isDigit(str.charAt(i))){
                int tmp = 0;
                while(i < str.length() && Character.isDigit(str.charAt(i))){
                    tmp = tmp * 10 + (str.charAt(i) - '0');
                    ++i;
                }
                if(i < str.length() && str.charAt(i) == 'x'){
                    if(oper.equals("")) sumX = tmp;
                    if(oper.equals("+")) sumX += tmp;
                    if(oper.equals("-")) sumX -= tmp;
                }
                else{
                    if(oper.equals("")) sum = tmp;
                    if(oper.equals("+")) sum += tmp;
                    if(oper.equals("-")) sum -= tmp;
                }
                oper = "";
                --i;
            }
            else if(str.charAt(i) == '+' || str.charAt(i) == '-'){
                oper = "" + str.charAt(i);
            }
            else{
                if(oper.equals("")){
                    if(i - 1 >= 0 && str.charAt(i - 1) == '0'){
                        
                    }
                    else if(sumX == 0 ) sumX = 1;
                    
                }
                if(oper.equals("+")) sumX += 1;
                if(oper.equals("-")) sumX -= 1;
            }
        }
        return new int[]{sumX , sum};
    }
}