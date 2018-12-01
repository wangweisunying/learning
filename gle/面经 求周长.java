/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Wei Wang
 */
public class QiuZhouChang {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] M ={{0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 0},
                    {0, 1, 0, 1, 0},
                    {0, 1, 1, 1, 0},
                    {0, 0, 0, 0, 0}};

        QiuZhouChang test = new QiuZhouChang();
        System.out.println(test.getRes(M, new int[]{1,1}));
    }

    public int getRes(int[][] M, int[] axis) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int[] deltaX = {0, 0, 1, -1};
        int[] deltaY = {1, -1, 0, 0};
        int m = M.length;
        int n = M[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[axis[0]][axis[1]] = true;
        Queue<int[]> que = new LinkedList();
        que.offer(axis);
        int ct = 0;
        while (!que.isEmpty()) { 
            
            int[] cur = que.poll();
            System.out.println(cur[0] + " : " + cur[1]);
            
            for (int i = 0; i < 4; i++) {
                ct++;
                int x = cur[0] + deltaX[i];
                int y = cur[1] + deltaY[i];
                if (outBound(x, y, m, n)) {
                    continue;
                }
                if(M[x][y] != 1){
                    continue;
                }
                //if it has neighbors ct --;
                ct--;
                if (visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                que.offer(new int[]{x , y});
                
            }
            System.out.println(ct);
        }
        return ct;

    }

    private boolean outBound(int x, int y, int m, int n) {
        if (x < 0 || y < 0 || x >= m || y >= n) {
            return true;
        }
        return false;
    }
}
