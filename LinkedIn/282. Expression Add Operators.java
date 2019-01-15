// 282. Expression Add Operators

// Favorite

// Share
// Given a string that contains only digits 0-9 and a target value, 
// return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

// Example 1:

// Input: num = "123", target = 6
// Output: ["1+2+3", "1*2*3"] 
// Example 2:

// Input: num = "232", target = 8
// Output: ["2*3+2", "2+3*2"]
// Example 3:

// Input: num = "105", target = 5
// Output: ["1*0+5","10-5"]
// Example 4:

// Input: num = "00", target = 0
// Output: ["0+0", "0-0", "0*0"]
// Example 5:

// Input: num = "3456237490", target = 9191
// Output: []
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList();
        dfs(num , target , res , "" , 0 , 0 , 0);
        return res;
    }
    private void dfs(String num , int target , List<String> res , String path, int index , long curRes  , long lastPortion){
        if(index == num.length()){
            if(target == curRes){
                res.add(path);
            }
            return;
        }
        for(int i = index ; i < num.length() ; i++){
            if(i != index && num.charAt(index) == '0') break; // 0 只能单独存在于公式中 没有01 ,02 ,03 ,0110 ,所以这里用charAt(index)==0
            long cur = Long.parseLong(num.substring(index , i + 1));
            if(index == 0){
                dfs(num , target , res,  path + cur , i + 1, cur , cur);
            }
            else{
                dfs(num , target , res , path + "+" + cur , i + 1, curRes + cur , cur);
                dfs(num , target , res , path + "-" + cur , i + 1, curRes - cur , -cur);
                dfs(num , target , res , path + "*" + cur , i + 1 ,curRes - lastPortion + lastPortion * cur , lastPortion * cur);
            }
        }
    }
}
// "105"
// 5
// Output
// ["1*0+5","1*5","10-5"]
// Expected
// ["1*0+5","10-5"]





public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
    
    //从position开始 ，枚举 每一个 substring 和 前面的 结合！！！
    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){
        if(pos == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = pos; i < num.length(); i++){
            if(i != pos && num.charAt(pos) == '0') break; // 排除开头为 0 的情况
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if(pos == 0){
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            }
            else{
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);
                
                helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);
                
                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }
}