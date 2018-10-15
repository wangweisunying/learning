// 130. Surrounded Regions
// DescriptionHintsSubmissionsDiscussSolution
// Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

// A region is captured by flipping all 'O's into 'X's in that surrounded region.

// Example:

// X X X X
// X O O X
// X X O X
// X O X X
// After running your function, the board should be:

// X X X X
// X X X X
// X X X X
// X O X X
// Explanation:

// Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
//  Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
// Two cells are connected if they are adjacent cells connected horizontally or vertically.

// Input:
// [["O","X","X","O","X"],
// ["X","O","O","X","O"],
// ["X","O","X","O","X"],
// ["O","X","O","O","O"],
// ["X","X","O","X","O"]]
/
// Expected:
// [["O","X","X","O","X"],
// ["X","X","X","X","O"],
// ["X","X","X","O","X"],
// ["O","X","O","O","O"],
// ["X","X","O","X","O"]]


class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

//从边缘往里扩散
public static void solve(char[][] board) {
	if (board == null || board.length == 0)
		return;
	int rows = board.length, columns = board[0].length;
	int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	for (int i = 0; i < rows; i++)
		for (int j = 0; j < columns; j++) {
			if ((i == 0 || i == rows - 1 || j == 0 || j == columns - 1) && board[i][j] == 'O') {
				Queue<Point> queue = new LinkedList<>();
				board[i][j] = 'B';
				queue.offer(new Point(i, j));
				while (!queue.isEmpty()) {
					Point point = queue.poll();
					for (int k = 0; k < 4; k++) {
						int x = direction[k][0] + point.x;
						int y = direction[k][1] + point.y;
						if (x >= 0 && x < rows && y >= 0 && y < columns && board[x][y] == 'O') {
							board[x][y] = 'B';
							queue.offer(new Point(x, y));
						}
					}
				}
			}
		}
	for (int i = 0; i < rows; i++)
		for (int j = 0; j < columns; j++) {
			if (board[i][j] == 'B')
				board[i][j] = 'O';
			else if (board[i][j] == 'O')
				board[i][j] = 'X';
		}

}


class Solution {
    public void solve(char[][] board) {
        if(board == null || board.length < 2 || board[0].length < 2){
            return;
        }

        int[] deX = {1 , -1 , 0 , 0};
        int[] deY = {0 , 0 , 1 , -1};

        HashSet<Integer> set = new HashSet();
        Queue<int[]> que = new LinkedList();
        
        int m = board.length;
        int n = board[0].length;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                List<int[]> list = new ArrayList();
                if(set.contains(i * n + j)){
                    continue;
                }
                if(board[i][j] == 'O'){
                    que.offer(new int[]{i , j});
                    set.add(i * n + j);
                    list.add(new int[]{i , j});
                }
                boolean isOut = true;
                
                while(!que.isEmpty()){
                    int[] cur = que.poll();
                    for(int k = 0 ; k < 4 ; k++){
                        int x = cur[0] + deX[k];
                        int y = cur[1] + deY[k];
                        
                        if(outBound(x , y , m , n)){
                            isOut = false;
                            continue;
                        }
                        if(board[x][y] == 'X'){
                            continue;
                        }
                        if(set.contains(x * n + y)){
                            continue;
                        }
                
                        set.add(x * n + y);
                        que.offer(new int[]{x , y});
                        list.add(new int[]{x , y});
                    }
                }
                if(isOut){
                    for(int[] axis : list){
                        board[axis[0]][axis[1]] = 'X';
                    }
                }
            }
        }
    }
    private boolean outBound(int x , int y , int m ,int n){
        if(x < 0 || y < 0 || x >= m || y >= n){
            return true;
        }
        return false;
    }
}








class Solution {
    int[] f;
    int m , n ;
    public void unionFind(int n){
        f = new int[n + 1]; // use a dummy node stand for the outbound
        for(int i = 0 ; i < n + 1 ; i++){
            f[i] = i;
        }
    }

    public int find(int a){
        if(f[a] == a){
            return f[a];
        }
        return f[a] = find(f[a]);
    }
    public void union(int a , int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){ 
            if(rootA == m * n){ // key point is judge whether there is father equals to outbound , connect to it. 
                f[rootB] = rootA; 
            }
            else{
                f[rootA] = rootB;
            }
        }
    }

    public void solve(char[][] board) {
        if(board == null || board.length < 2 || board[0].length < 2){
            return;
        }
        m = board.length;
        n = board[0].length;
        unionFind(m * n);      
        int i = 0 , j = 0;
        while(i < m){
            if(board[i][0] == 'O'){
                union( i * n , m * n);
            }
            if(board[i][n - 1] == 'O'){
                union(i * n + n - 1 , m * n);
            }
            i++;
        }
        while(j < n){
            if(board[0][j] == 'O'){
                union(j , m * n);
            }
            if(board[m - 1][j] == 'O'){
                union((m - 1) * n + j , m * n);
            }
            j++;
        }
        for(i = 1 ; i < m - 1 ; i++){
            for(j = 1 ; j < n - 1; j++){
                if(board[i][j] == 'O'){
                    
                    if(board[i + 1][j] == 'O'){
                        union( i * n + j , (i + 1) * n + j);
                    }
                    if(board[i - 1][j] == 'O'){
                        union( i * n + j , (i - 1) * n + j);
                    }
                    if(board[i][j + 1] == 'O'){
                        union( i * n + j , i * n + j + 1);
                    }
                    if(board[i][j - 1] == 'O'){
                        union( i * n + j , i * n + j - 1);
                    }
                    
                }
            }
        }

        for(i = 1 ; i < m - 1 ; i++){
            for(j = 1 ; j < n - 1; j++){
                if(board[i][j] == 'O' && find( i *n + j) != m* n){
                    board[i][j] = 'X';
                }
                
            }
        }

    }
}




class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

//从边缘往里扩散
public static void solve(char[][] board) {
	if (board == null || board.length == 0)
		return;
	int rows = board.length, columns = board[0].length;
	int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	for (int i = 0; i < rows; i++)
		for (int j = 0; j < columns; j++) {
			if ((i == 0 || i == rows - 1 || j == 0 || j == columns - 1) && board[i][j] == 'O') {
				Queue<Point> queue = new LinkedList<>();
				board[i][j] = 'B';
				queue.offer(new Point(i, j));
				while (!queue.isEmpty()) {
					Point point = queue.poll();
					for (int k = 0; k < 4; k++) {
						int x = direction[k][0] + point.x;
						int y = direction[k][1] + point.y;
						if (x >= 0 && x < rows && y >= 0 && y < columns && board[x][y] == 'O') {
							board[x][y] = 'B';
							queue.offer(new Point(x, y));
						}
					}
				}
			}
		}
	for (int i = 0; i < rows; i++)
		for (int j = 0; j < columns; j++) {
			if (board[i][j] == 'B')
				board[i][j] = 'O';
			else if (board[i][j] == 'O')
				board[i][j] = 'X';
		}

}