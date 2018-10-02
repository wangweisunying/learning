package maze;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Wei Wang
 */
class Cell {

    int x, y;
    int[] wall;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        wall = new int[]{1, 1, 1, 1};
    }
}

public class MazeGen2 {

    int w, h;
    Cell[][] maze;
    boolean[][] visited;
    int[] dirX = {-1, 1, 0, 0};
    int[] dirY = {0, 0, -1, 1};
    boolean finish;
    boolean found;

    HashMap<Integer, Integer> map;

    MazeGen2(int w, int h) {
        this.w = w;
        this.h = h;
        maze = new Cell[w][h];
        visited = new boolean[w][h];
        map = new HashMap();
        map.put(0, 1);
        map.put(1, 0);
        map.put(2, 3);
        map.put(3, 2);
    }

    public void run() {
        maze[0][0] = new Cell(0, 0);
        maze[0][0].wall[2] = 0;
        visited[0][0] = true;
        dfs(maze[0][0]);
        UI();
        boolean[][] res = solve();
        UI(res);
    }

    private void dfs(Cell start) {
        if (finish) {
            return;
        }
        if (allVisited()) {
            finish = true;
            return;
        }

        HashSet<Integer> set = new HashSet();
        for (int i = 0; i < 4; i++) {
            int index = (int) Math.floor(Math.random() * 4);
            while (set.contains(index)) {
                index = (int) Math.floor(Math.random() * 4);
            }
            set.add(index);

            int x = start.x + dirX[index], y = start.y + dirY[index];
            if (!inBound(x, y)) {
                continue;
            }
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            maze[x][y] = new Cell(x, y);
            start.wall[index] = 0;
            maze[x][y].wall[map.get(index)] = 0;
            dfs(maze[x][y]);
            
            
            
        }

    }

    private boolean inBound(int x, int y) {
        if (x >= 0 && x < w && y >= 0 && y < h) {
            return true;
        }
        return false;
    }

    private boolean allVisited() {
        for (boolean[] tmp : visited) {
            for (boolean now : tmp) {
                if (!now) {
                    return false;
                }
            }
        }
        return true;
    }

    private void UI() {
        JFrame f = new JFrame("Maze");			
        f.setSize(968, 990);    

        Point point = new Point(350, 200);
        f.setLocation(point);
        int grids = w;					        	//行数和列数
        int gridsize = 10;							//单元格的高和宽

        for (int i = 0; i < grids; i++) //外循环控制行
        {
            for (int j = 0; j < grids; j++) //内循环控制列
            {
                JLabel l = new JLabel();			//生成标签实例
                l.setSize(gridsize, gridsize);		//设置标签大小
                l.setLocation( i * gridsize, j * gridsize);	//设置标签位置
                l.setBorder(BorderFactory.createMatteBorder(maze[j][i].wall[0]
                                                            ,maze[j][i].wall[2]
                                                            ,maze[j][i].wall[1]
                                                            ,maze[j][i].wall[3]
                                                            ,Color.black));  //设置边界为黑色
                f.add(l);			
            }
        }
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
    }
    private void UI(boolean[][] res) {
        JFrame f = new JFrame("Maze");			
        f.setSize(968, 990);   

        Point point = new Point(350, 200);
        f.setLocation(point);
        int grids = w;					        	//行数和列数
        int gridsize = 10;							//单元格的高和宽

        for (int i = 0; i < grids; i++) //外循环控制行
        {
            for (int j = 0; j < grids; j++) //内循环控制列
            {
                JLabel l = new JLabel();			//生成标签实例
                l.setSize(gridsize, gridsize);		//设置标签大小
                l.setLocation(i * gridsize, j * gridsize);	//设置标签位置
                
                l.setBorder(BorderFactory.createMatteBorder(maze[j][i].wall[0]
                                                            ,maze[j][i].wall[2]
                                                            ,maze[j][i].wall[1]
                                                            ,maze[j][i].wall[3]
                                                            ,Color.black));  //设置边界为黑色
                if(res[j][i]){
                    l.setBackground(Color.RED);
                }
                
                l.setOpaque(true);
                
                f.add(l);			
            }
        }
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
    }
    

    public static void main(String[] args) {
        MazeGen2 gen2 = new MazeGen2(95, 95);
        gen2.run();
    }
    
    public boolean[][] solve(){
        boolean[][] route = new boolean[w][h];
        route[0][0] = true;
        getRoute(route , 0 , 0);
        return route;
    }
    private void getRoute(boolean[][] route , int x ,int y){
        if(found){
            return;
        }
        if(x == w - 1 && y == h - 1){
            found = true;
            return;
        }
        for(int i = 0 ; i < 4 ; i ++){
            if(maze[x][y].wall[i] == 1){
                continue;
            }
            
            int curX = x + dirX[i] , curY = y + dirY[i];
            if(!inBound(curX , curY)){
                continue;
            }
            if(route[curX][curY]){
                continue;
            }
            route[curX][curY] = true;
            getRoute(route, curX , curY);
            if(found){
                return;
            }
            route[curX][curY] = false;
        }
    }
    
}
    