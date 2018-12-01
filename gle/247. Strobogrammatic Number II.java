// 247. Strobogrammatic Number II
// DescriptionHintsSubmissionsDiscussSolution
// A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

// Find all strobogrammatic numbers that are of length = n.

// Example:

// Input:  n = 2
// Output: ["11","69","88","96"]


// dfs find all the possible , notince use char[] instead of string , cuz you are adding from outside to inside
class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList();
        dfs(res , new char[n] , 0 , n - 1 , n , new char[][]{{'0' , '0'} , {'1','1'} , {'6','9'} , {'9','6'} , {'8','8'}});
        return res;
    }
    private void dfs(List<String> res , char[] cur , int s , int e ,int len , char[][] arr){
        if(s > e){
            res.add(new String(cur));
            return;
        }
        for(char[] charArr : arr){
            //skip the '0' front
            if(s == 0 && len > 1 && charArr[0] == '0'){
                continue;
            }
            // skip '6' or '9' in the middle
            if(s == e && charArr[0] != charArr[1]){
                continue;
            }
            //char array already included backtracking ,
            cur[s] = charArr[0];
            cur[e] = charArr[1];
            // if(s == e){
            dfs(res , cur , s + 1  , e - 1 , len , arr);   
            // }
            // else{
            //     dfs(res , cur , s + 1  , e - 1 , len , arr);
            // }
        }
    }
}


Input:
4
Output:
["1111","6119","9116","8118","1691","6699","9696","8698","1961","6969","9966","8968","1881","6889","9886","8888"]
Expected:
["1001","1111","1691","1881","1961","6009","6119","6699","6889","6969","8008","8118","8698","8888","8968","9006","9116","9696","9886","9966"]