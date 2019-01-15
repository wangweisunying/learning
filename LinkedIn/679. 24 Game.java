// 679. 24 Game
// Hard

// 285

// 68

// Favorite

// Share
// You have 4 cards each containing a number from 1 to 9. You need to jsudge whether they could operated through *, /, +, -, (, ) to get the value of 24.

// Example 1:
// Input: [4, 1, 8, 7]
// Output: True
// Explanation: (8-4) * (7-1) = 24
// Example 2:
// Input: [1, 2, 1, 2]
// Output: False
// Note:
// The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
// Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
// You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

class Solution {
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList(Arrays.asList(nums));
        return dfs(list);
    }
    private boolean dfs(List<Double> list){
        if(list.size() == 1){
            return Math.abs(list.get(0) - 24.0) <= 0.01;
        }
        
        for(int i = 0 ; i < list.size() - 1 ; i++){
            for(int j = i + 1 ; j < list.size() ; j++){
                List<Double> can = new ArrayList();
                for(int k = 0 ; k < list.size() ; k++){
                    if(k != i && k != j) can.add(list.get(k));  
                }
                for(double num : getCan(list.get(i) , list.get(j))){
                    can.add(num);
                    if(dfs(can)) return true;
                    can.remove(can.size() - 1);
                }
            }
        }
        return false;
    }
    private List<Double> getCan(double x , double y){
        List<Double> list = new ArrayList();
        list.add(x + y);
        list.add(x - y);
        list.add(y - x);
        list.add(x * y);
        if(y != 0) list.add(x / y);
        if(x != 0) list.add(y / x);
        return list;
    }
}