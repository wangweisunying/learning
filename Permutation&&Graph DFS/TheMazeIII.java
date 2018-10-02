// There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r),
//  but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. 
//  The ball will drop into the hole if it rolls on to the hole.

// Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance.
//  The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included).
//   Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, 
//   you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

// The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls.
//  The ball and the hole coordinates are represented by row and column indexes.

// Example
// Input:
// a maze represented by a 2D array

// 0 0 0 0 0
// 1 1 0 0 1
// 0 0 0 0 0
// 0 1 0 0 1
// 0 1 0 0 0

// ball coordinate (rowBall, colBall) = (4, 3)
// hole coordinate (rowHole, colHole) = (0, 1)

// Output:"lul"


public class Solution {
    /**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */

    class Point{
        int[] axis = new int[2];
        String path;
        int len;
        Point(int[] axis , String path , int len){
            this.axis[0] = axis[0];
            this.axis[1] = axis[1];
            this.path = path;
            this.len = len;
        } 
    }
    int row;
    int col;
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        row = maze.length;
        col = maze[0].length;
        // down left right up  字典序
        int[] deltaX = {1,  0, 0 , -1};
        int[] deltaY = {0, -1 , 1 , 0};
        String[] dir = {"d" , "l" , "r" , "u"};
        boolean[][] visited = new boolean[row][col];
        TreeMap<Integer , TreeSet<String>> map = new TreeMap();
                
        Queue<Point> que = new LinkedList();
        que.offer(new Point(ball , "" , 0));
        
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i ++){
                Point p = que.poll();
                visited[p.axis[0]][p.axis[1]] = true;               
                for(int j = 0 ; j < 4 ; j ++){
                    int[] tmp = {p.axis[0] + deltaX[j] , p.axis[1] + deltaY[j]};
                    String curPath = p.path;
                    int ct = p.len;
                    boolean flag = true;
                    while(inBound(tmp) && maze[tmp[0]][tmp[1]] == 0){
                        ct++;
                        if(flag){
                            curPath += dir[j];
                        }
                        flag = false;
                        if(tmp[0] == hole[0] && tmp[1] == hole[1]){
                            if(map.keySet().contains(ct)){
                                map.get(ct).add(curPath);
                            }
                            else{
                                map.put(ct , new TreeSet());
                                map.get(ct).add(curPath);
                            }                   
                        }
                        tmp[0] += deltaX[j];
                        tmp[1] += deltaY[j];

                    }
                    tmp[0] -= deltaX[j];
                    tmp[1] -= deltaY[j];
                    if(!visited[tmp[0]][tmp[1]]){
                        que.offer(new Point(tmp , curPath ,ct));
                    }
                }

            }

        }
        if(map.keySet().isEmpty()){
            return "impossible";
        }
        return map.get(map.firstKey()).first();
    }
    private boolean inBound(int[] tmp) {
        if (tmp[0] >= 0 && tmp[0] < row && tmp[1] >= 0 && tmp[1] < col) {
            return true;
        }
        return false;
    }
}