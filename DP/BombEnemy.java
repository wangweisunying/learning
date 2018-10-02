// Bomb Enemy
// Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
// The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.

// Example
// Given a grid:

// 0 E 0 0
// E 0 W E
// 0 E 0 0
// return 3. (Placing a bomb at (1,1) kills 3 enemies)

public class Solution {
    /**
     * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return: an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;

        int[][] up = new int[m][n];
        int[][] left = new int[m][n];
        int[][] down = new int[m][n];
        int[][] right = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if (grid[i][j] == 'E') {
                        up[i][j] = 1;
                    } else {
                        up[i][j] = 0;
                    }

                }
                if (j == 0) {
                    if (grid[i][j] == 'E') {
                        left[i][j] = 1;
                    } else {
                        left[i][j] = 0;
                    }

                }

                if (i > 0 && grid[i][j] == '0') {
                    up[i][j] = up[i - 1][j];
                }
                if (j > 0 && grid[i][j] == '0') {
                    left[i][j] = left[i][j - 1];
                }
                if (i > 0 && grid[i][j] == 'W') {
                    up[i][j] = 0;

                }
                if (j > 0 && grid[i][j] == 'W') {
                    left[i][j] = 0;
                }
                if (i > 0 && grid[i][j] == 'E') {
                    up[i][j] = up[i - 1][j] + 1;

                }
                if (j > 0 && grid[i][j] == 'E') {

                    left[i][j] = left[i][j - 1] + 1;
                }
            }
        }

        for (int k = m - 1; k >= 0; k--) {
            for (int l = n - 1; l >= 0; l--) {
                if (k == m - 1) {
                    if (grid[k][l] == 'E') {
                        down[k][l] = 1;
                    } else {
                        down[k][l] = 0;
                    }

                }
                if (l == n - 1) {
                    if (grid[k][l] == 'E') {
                        right[k][l] = 1;
                    } else {
                        right[k][l] = 0;
                    }

                }

                if (k < m - 1 && grid[k][l] == '0') {
                    down[k][l] = down[k + 1][l];

                }
                if (k < m - 1 && grid[k][l] == 'W') {
                    down[k][l] = 0;

                }
                if (k < m - 1 && grid[k][l] == 'E') {
                    down[k][l] = down[k + 1][l] + 1;

                }
                if (l < n - 1 && grid[k][l] == '0') {

                    right[k][l] = right[k][l + 1];

                }
                if (l < n - 1 && grid[k][l] == 'W') {

                    right[k][l] = 0;

                }
                if (l < n - 1 && grid[k][l] == 'E') {

                    right[k][l] = right[k][l + 1] + 1;

                }
            }
        }
        int max = 0;
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {

                if (grid[x][y] == '0') {
//                    System.out.println(left[x][y]);
//                    System.out.println(up[x][y] + down[x][y] + left[x][y] + right[x][y]);
                    max = Math.max(max, up[x][y] + down[x][y] + left[x][y] + right[x][y]);
                }

            }
        }
        return max;
    }
}
