// 794. Valid Tic-Tac-Toe State
// DescriptionHintsSubmissionsDiscussSolution
// A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

// The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

// Here are the rules of Tic-Tac-Toe:

// Players take turns placing characters into empty squares (" ").
// The first player always places "X" characters, while the second player always places "O" characters.
// "X" and "O" characters are always placed into empty squares, never filled ones.
// The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
// The game also ends if all squares are non-empty.
// No more moves can be played if the game is over.
// Example 1:
// Input: board = ["O  ", "   ", "   "]
// Output: false
// Explanation: The first player always plays "X".

// Example 2:
// Input: board = ["XOX", " X ", "   "]
// Output: false
// Explanation: Players take turns making moves.

// Example 3:
// Input: board = ["XXX", "   ", "OOO"]
// Output: false

// Example 4:
// Input: board = ["XOX", "O O", "XOX"]
// Output: true
// Note:

// board is a length-3 array of strings, where each string board[i] has length 3.
// Each board[i][j] is a character in the set {" ", "X", "O"}.

// When turns is 1, X moved. When turns is 0, O moved.
//  rows stores the number of X or O in each row. 
//  cols stores the number of X or O in each column. 
//  diag stores the number of X or O in diagonal. 
//  antidiag stores the number of X or O in antidiagonal. When any of the value gets to 3, it means X wins. When any of the value gets to -3, it means O wins.

// When X wins, O cannot move anymore, so turns must be 1. 
// When O wins, X cannot move anymore, so turns must be 0. Finally, when we return, turns must be either 0 or 1, and X and O cannot win at same time.

class Solution {
    public boolean validTicTacToe(String[] board) {
        int turns = 0;
        boolean xwin = false; 
        boolean owin = false;
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diag = 0;
        int antidiag = 0;
				
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'X') {
                    turns++; rows[i]++; cols[j]++;
                    if (i == j) diag++;
                    if (i + j == 2) antidiag++;
                } else if (board[i].charAt(j) == 'O') {
                    turns--; rows[i]--; cols[j]--;
                    if (i == j) diag--;
                    if (i + j == 2) antidiag--;
                }
            }
        }
		
        xwin = rows[0] == 3 || rows[1] == 3 || rows[2] == 3 || 
               cols[0] == 3 || cols[1] == 3 || cols[2] == 3 || 
               diag == 3 || antidiag == 3;
        owin = rows[0] == -3 || rows[1] == -3 || rows[2] == -3 || 
               cols[0] == -3 || cols[1] == -3 || cols[2] == -3 || 
               diag == -3 || antidiag == -3;
        
        if (xwin && turns == 0 || owin && turns == 1) {
            return false;
        }
        return (turns == 0 || turns == 1) && (!xwin || !owin);
    }
}



class Solution {
    public boolean validTicTacToe(String[] board) {
        int[] ct = new int[2];
        for(String str : board){
            for(char c : str.toCharArray()){
                if(c == 'X') ct[0]++;
                if(c == 'O') ct[1]++;
            }
        }
        if(ct[0] < ct[1] || ct[0] - ct[1] > 1) return false;
        boolean flagX = false;
        boolean flagO = false;
        for(String str : board){
            if(str.equals("XXX")){
                if(flagO) return false;
                flagX = true;
            }
            if(str.equals("OOO")){
                if(flagX) return false;
                flagO = true;
            }
        }
        for(int i = 0 ; i < 3; i++){
            String now = "";
            for(String str : board){
                now += str.charAt(i);
            }  
            if(now.equals("XXX")){
                if(flagO) return false;
                flagX = true;
            }
            if(now.equals("OOO")){
                if(flagX) return false;
                flagO = true;
            }
        }
        String dia1 = ""+board[0].charAt(0)+ board[1].charAt(1)+ board[2].charAt(2);
         if(dia1.equals("XXX")){
                if(flagO) return false;
                flagX = true;
            }
            if(dia1.equals("OOO")){
                if(flagX) return false;
                flagO = true;
            }
        String dia2 = ""+board[2].charAt(0)+ board[1].charAt(1)+ board[0].charAt(2);
         if(dia2.equals("XXX")){
                if(flagO) return false;
                flagX = true;
            }
            if(dia2.equals("OOO")){
                if(flagX) return false;
                flagO = true;
            }
        if(flagX) return ct[0] > ct[1];
        if(flagO) return ct[0] == ct[1];
        return true;
    }
}


["OXX",
 "XOX",
 "OXO"]