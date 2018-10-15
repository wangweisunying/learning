// Coke machine has several  buttons {200 , 240} , {400 , 410} , {100 , 120} ,  give a range ,check if can be get within the range/


public class Coke {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(good(new int[][]{ {200 , 240} , {400 , 410} , {100 , 120}} , new int[]{ 310, 360}));
    }
    
    private static boolean good(int[][] button , int[] range){
        
        
        return dfs(button , range , 0 , 0 , 0 );
        
        
    }
    private static boolean dfs(int[][] button , int[] range  , int index , int curLow , int curHigh){
        if(curLow >= range[0] && curHigh <= range[1]){
            return true;
        }
       
        for(int i = index ; i < button.length ; i++){
            if(curHigh + button[i][1] > range[1]){
                continue;
            }
            if(dfs(button , range , i , curLow + button[i][0] , curHigh + button[i][1])){
                return true;
            }
        }
        return false;
    
    }
}