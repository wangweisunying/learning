// 489. Robot Room Cleaner
// DescriptionHintsSubmissionsDiscussSolution
// Given a robot cleaner in a room modeled as a grid.

// Each cell in the grid can be empty or blocked.

// The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

// When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

// Design an algorithm to clean the entire room using only the 4 given APIs shown below.

// interface Robot {
//   // returns true if next cell is open and robot moves into the cell.
//   // returns false if next cell is obstacle and robot stays on the current cell.
//   boolean move();

//   // Robot will stay on the same cell after calling turnLeft/turnRight.
//   // Each turn will be 90 degrees.
//   void turnLeft();
//   void turnRight();

//   // Clean the current cell.
//   void clean();
// }
// Example:

// Input:
// room = [
//   [1,1,1,1,1,0,1,1],
//   [1,1,1,1,1,0,1,1],
//   [1,0,1,1,1,1,1,1],
//   [0,0,0,1,0,0,0,0],
//   [1,1,1,1,1,1,1,1]
// ],
// row = 1,
// col = 3

// Explanation:
// All grids in the room are marked by either 0 or 1.
// 0 means the cell is blocked, while 1 means the cell is accessible.
// The robot initially starts at the position of row=1, col=3.
// From the top left corner, its position is one row below and three columns right.
// Notes:

// The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
// The robot's initial position will always be in an accessible cell.
// The initial direction of the robot will be facing up.
// All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
// Assume all four edges of the grid are all surrounded by wall.


/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

 //must syncronize the robot step to the normal dfs procudure 
class Solution {
     
    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet();
        visited.add(0 + "_" + 0);
        dfs(visited , 0 , 0 , robot , 0);        
    }
    int[] deltaX = {-1 , 0 , 1 , 0};
    int[] deltaY = { 0 , 1, 0 , -1};
    private void dfs(Set<String> visited , int x ,int y ,Robot robot , int dir){
        robot.clean();
        for(int i = 0 ; i < 4 ; i++){
            // default face up so no need to turn at this time  
            int curDir = (dir + i) % 4;
            int xx = x + deltaX[curDir];
            int yy = y + deltaY[curDir];
            String loc = xx + "_" + yy;
            if(visited.contains(loc)){
                robot.turnRight();//syschronize next step
                continue;
            }
            visited.add(loc);
            if(!robot.move()){
                robot.turnRight(); //syschronize next step
                continue;
            }
            dfs(visited , xx , yy , robot , curDir);
            // after doing everything back to the posistion
            robot.turnLeft();
            robot.turnLeft();
            robot.move();
            robot.turnRight();
            robot.turnRight();

            //syncronize the same turn direction for next loop  prepare for the next loop Or previous level's next direction!
            robot.turnRight(); //syschronize next step
        }
    }
}

















class Solution {
    int[] deltaX = {-1, 0 , 1 , 0};
    int[] deltaY = {0, -1 , 0 , 1};
    public void cleanRoom(Robot robot) {
        dfs(robot , new HashSet<String>(Arrays.asList("0_0")) , 0 , 0 , 0);
    }
    private void dfs(Robot robot , HashSet<String> visited , int x ,int y , int dir){        
        robot.clean();
        for(int i = 0 ; i < 4 ;i++){
            String next = (x + deltaX[dir])  + "_" + (y + deltaY[dir]);
            //先判断再move
            if(visited.contains(next)){
                robot.turnRight();
                dir ++;
                dir %= 4;
                continue;
            }
            
            //mvoe 是一个动作
            if(!robot.move()){
                robot.turnRight();
                dir ++;
                dir %= 4;
                continue;
            }
            
            //move成功了
            visited.add(next);
            dfs(robot , visited , x + deltaX[dir] , y + deltaY[dir] , dir);
            //返回原位置
            robot.turnRight();
            robot.turnRight();
            robot.move();
            robot.turnLeft();
            robot.turnLeft();
            
            //下一个位置准备
            robot.turnRight();
            dir++;
            dir %= 4;
        }
        
        
    }
}