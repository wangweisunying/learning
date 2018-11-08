// 711. Number of Distinct Islands II
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
// You may assume all four edges of the grid are surrounded by water.

// Count the number of distinct islands. An island is considered to be the same as another
//  if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

// Example 1:
// 11000
// 10000
// 00001
// 00011
// Given the above grid map, return 1. 

// Notice that:
// 11
// 1
// and
//  1
// 11
// are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
// Example 2:
// 11100
// 10001
// 01001
// 01110
// Given the above grid map, return 2.

// Here are the two distinct islands:
// 111
// 1
// and
// 1
// 1

// Notice that:
// 111
// 1
// and
// 1
// 111
// are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
// Note: The length of each dimension in the given grid does not exceed 50.



[[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0,0,0],[0,1,0,0,0,0,0,0,1,1,1,0,0,0,0],[1,1,1,0,0,0,0,0,0,1,0,0,0,0,0],[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0]]


// [0,0,0,1,1,0,0,0,0,0,0,1,1,0,0],
// [0,0,0,0,1,1,0,0,0,0,0,1,1,1,0],
// [0,0,0,0,0,1,0,0,0,0,0,1,1,0,0],
// [0,0,0,0,0,1,1,0,0,0,1,1,1,0,0],
// [0,0,1,0,0,0,1,0,0,0,1,1,1,0,0],
// [1,1,1,0,0,0,0,0,0,0,0,0,0,0,0],
// [0,0,1,1,1,0,0,0,0,0,0,0,1,0,0],
// [0,0,0,0,1,1,0,0,0,0,0,0,1,0,0],
// [0,0,0,0,0,1,0,0,0,0,0,1,1,0,1],
// [0,0,0,0,0,0,0,0,0,0,0,1,1,1,1]



// In Java, we manipulate the coordinates directly. The 8 rotations and reflections of each point are (x, y), (-x, y), (x, -y), (-x, -y), (y, x), (-y, x), (y, -x), (-y, -x).

public class Solution {

    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    int[] deltaX = {0, 0, 1, -1};
    int[] deltaY = {1, -1, 0, 0};
    int[][] trans = {{1 , 1},{-1 ,1},{1,-1},{-1,-1}};
    int m, n;
    Comparator compare;
    public int numDistinctIslands2(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        compare = new Comparator<int[]>(){
            @Override
            public int compare(int[] arr1 , int[] arr2){
                if(arr1[0] == arr2[0]){
                    return arr1[1] - arr2[1];
                }
                return arr1[0] - arr2[0];

            }

        };
        HashSet<String> set = new HashSet();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    List<int[]> list = new ArrayList();
                    dfs(grid, i, j, list);
                    Collections.sort(list , compare);
                    if(set.isEmpty()){
                        set.add(getPath(list));
                    }
                    else{
                        boolean found = false;
                        for(int k = 0 ; k < 4 ; k++){
                            if(set.contains(getPath(transfer(list , trans[k] , true))) || set.contains(getPath(transfer(list , trans[k] , false)))){
                                found = true;
                                break;
                            }
                        }
                        if(!found){
                            set.add(getPath(list));
                        }
                    }
                }
            }
        }
        return set.size();
    }
    private List<int[]> transfer(List<int[]> list , int[] trans , boolean ss){
        List<int[]> res = new ArrayList();
        if(ss){
            for(int i = 0 ; i < list.size() ; i++){
                res.add(new int[]{list.get(i)[0] * trans[0] ,list.get(i)[1] * trans[1]});
            }
        }
        else{
            for(int i = 0 ; i < list.size() ; i++){
                res.add(new int[]{list.get(i)[1] * trans[0],list.get(i)[0] * trans[1]});
            }
        }
        Collections.sort(res , compare);
        return res;
    }

    private String getPath(List<int[]> list){
        String res ="s";
        int x = list.get(0)[0];
        int y = list.get(0)[1];
        for(int i = 1 ; i < list.size() ; i++){
            res += (list.get(i)[0] - x) + "_" + (list.get(i)[1] - y);
            x = list.get(i)[0];
            y = list.get(i)[1];
        }
        return res;
    }
    private void dfs(int[][] grid, int i, int j, List<int[]> list) {
        if (grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        list.add(new int[]{i, j});
        for (int k = 0; k < 4; k++) {
            int x = i + deltaX[k];
            int y = j + deltaY[k];
            if (x < 0 || y < 0 || x >= m || y >= n) {
                continue;
            }
            dfs(grid, x, y, list);
        }
    }
}























class Solution {
    int[][] grid;
    boolean[][] seen;
    ArrayList<Integer> shape;

    public void explore(int r, int c) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length &&
                grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            shape.add(r * grid[0].length + c);
            explore(r+1, c);
            explore(r-1, c);
            explore(r, c+1);
            explore(r, c-1);
        }
    }

    public String canonical(ArrayList<Integer> shape) {
        String ans = "";
        int lift = grid.length + grid[0].length;
        int[] out = new int[shape.size()];
        int[] xs = new int[shape.size()];
        int[] ys = new int[shape.size()];

        for (int c = 0; c < 8; ++c) {
            int t = 0;
            for (int z: shape) {
                int x = z / grid[0].length;
                int y = z % grid[0].length;
                //x y, x -y, -x y, -x -y
                //y x, y -x, -y x, -y -x
                xs[t] = c<=1 ? x : c<=3 ? -x : c<=5 ? y : -y;
                ys[t++] = c<=3 ? (c%2==0 ? y : -y) : (c%2==0 ? x : -x);
            }

            int mx = xs[0], my = ys[0];
            for (int x: xs) mx = Math.min(mx, x);
            for (int y: ys) my = Math.min(my, y);

            for (int j = 0; j < shape.size(); ++j) {
                out[j] = (xs[j] - mx) * lift + (ys[j] - my);
            }
            Arrays.sort(out);
            String candidate = Arrays.toString(out);
            if (ans.compareTo(candidate) < 0) ans = candidate;
        }
        return ans;
    }

    public int numDistinctIslands2(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set shapes = new HashSet<String>();

        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[0].length; ++c) {
                shape = new ArrayList();
                explore(r, c);
                if (!shape.isEmpty()) {
                    shapes.add(canonical(shape));
                }
            }
        }

        return shapes.size();
    }
}