// 399. Evaluate Division
// DescriptionHintsSubmissionsDiscussSolution
// Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

// Example:
// Given a / b = 2.0, b / c = 3.0. 
// queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
// return [6.0, 0.5, -1.0, 1.0, -1.0 ].

// The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

// According to the example above:

// equations = [ ["a", "b"], ["b", "c"] ],
// values = [2.0, 3.0],
// queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
// The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String , Map<String , Double>> map = new HashMap();
        for(int i = 0 ; i < values.length ; i++){
            map.computeIfAbsent(equations[i][0] , x -> new HashMap()).put(equations[i][1] , values[i]);
            map.computeIfAbsent(equations[i][1] , x -> new HashMap()).put(equations[i][0] , 1.0 / values[i]);
        }
        System.out.println(map);
        double[] res = new double[queries.length];
        for(int i = 0 ; i < queries.length ; i++){
            res[i] = dfs(map , queries[i][0] , queries[i][1] , new HashSet(Arrays.asList(queries[i][0])) , 1.0);
        }
        return res;
    }
    private double dfs(Map<String , Map<String , Double>> map , String start, String end , Set<String> visited , double cur){
        
        if(!map.containsKey(start)) return -1.0;
        if(start.equals(end)) return cur;
        double res = -1.0;
        for(String next : map.get(start).keySet()){
            if(visited.contains(next)) continue;
            visited.add(next);
            res = dfs(map , next , end , visited , cur * map.get(start).get(next));
            if(res != -1) return res;
        }
        return res;
    }
}

[["a","b"],["e","f"],["b","e"]]
[3.4,1.4,2.3]
[["b","a"],["a","f"],["f","f"],["e","e"],["c","c"],["a","c"],["f","e"]]
Output:
[-1.0,10.948,1.0,1.0,-1.0,-1.0,0.71429]
Expected:
[0.29412,10.948,1.0,1.0,-1.0,-1.0,0.71429]

{a={b=3.4}, b={a=0.29411764705882354, e=2.3}, e={b=0.4347826086956522, f=1.4}, f={e=0.7142857142857143}}