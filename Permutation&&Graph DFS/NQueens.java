// The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

// Given an integer n, return all distinct solutions to the n-queens puzzle.

// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

// Example
// There exist two distinct solutions to the 4-queens puzzle:

// [
//   // Solution 1
//   [".Q..",
//    "...Q",
//    "Q...",
//    "..Q."
//   ],
//   // Solution 2
//   ["..Q.",
//    "Q...",
//    "...Q",
//    ".Q.."
//   ]
// ]


public class Solution {
    /*
     * @param n: The number of queens
     * @return: All distinct solutions
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<Integer>> res = new ArrayList();
        boolean[] visited = new boolean[n];
        dfs(res , n , visited, new ArrayList());
        return drawChess(res , n);
    }
    private List<List<String>> drawChess(List<List<Integer>> res , int n){
        List<List<String>> out = new ArrayList();
            for(int i = 0 ; i < res.size() ; i ++){
                List<String> tmplist = new ArrayList();
                for(int j = 0 ; j < res.get(i).size() ; j ++){
                    StringBuilder tmp = new StringBuilder();
                    for(int k = 0 ; k < n ; k++){
                        if(k == res.get(i).get(j)){
                            tmp.append("Q");
                        }
                        else{
                            tmp.append(".");
                        }
                    }
                    tmplist.add(tmp.toString());
                }
                out.add(tmplist);
            }
        
        return out;
    }

    private void dfs(List<List<Integer>> res , int n , boolean[] visited , List<Integer> cur){
        if(cur.size() == n){
            res.add(new ArrayList(cur));
        }
        for(int i = 0 ; i < n ; i++){
            if(visited[i]){
                continue;
            }
            
            //judge slope
            if(!judge(cur , i)){
                continue;
            }

            cur.add(i);
            visited[i] = true;
            dfs(res , n , visited , cur);
            visited[i] = false;
            cur.remove(cur.size() - 1);
        }
    }
    private boolean judge(List<Integer> cur , int i){
        int sum = cur.size() + i ; // x + y
        int minus = cur.size() - i ; // x - y
        
        for(int x1 = 0 ; x1 < cur.size() ; x1 ++){
            if(x1 + cur.get(x1) == sum || x1 - cur.get(x1) == minus){
                return false;
            }
        }
        return true;
    }
}