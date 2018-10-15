    public  static int getPath(int m, int n) {
        int[][] f = new int[m][n];
        int i = 0, j = 0;

        for (j = 0; j < n ; j++){
            for (i = 0; i <= j && i < m; i++) { //判断越界
                if (i == j) {
                    f[i][j] = 1;
                    continue;
                }
                f[i][j] = f[i][j - 1];
                if (i - 1 >= 0) {
                    f[i][j] += f[i - 1][j - 1];
                }
                if (i + 1 < m) {
                    f[i][j] += f[i + 1][j - 1];
                }
            }
        }
        return f[0][n - 1];
    }
}

//follow up 

    public  static int getPath(int x , int y ,int m, int n) {
        int[][] f = new int[m][n];
        int i = 0, j = 0;

        for (j = y; j < n ; j++){
            for (i = 0; i <= j && i < m; i++) { //
                if (i == j) {
                    f[i][j] = 1;
                    continue;
                }
                f[i][j] = f[i][j - 1];
                if (i - 1 >= 0) {
                    f[i][j] += f[i - 1][j - 1];
                }
                if (i + 1 < m) {
                    f[i][j] += f[i + 1][j - 1];
                }
            }
        }
        return f[0][n - 1];
    }