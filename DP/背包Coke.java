// Coke machine has several  buttons {200 , 240} , {400 , 410} , {100 , 120} ,  give a range ,check if can be get within the range/


public class Coke {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Coke coke = new Coke();
        int[][] button = new int[][]{{20,30}};
        int[] range = new int[]{100 , 140};
        System.out.println(coke.good(button, range));
        System.out.println(coke.good2(button, range));
    }
    
    
    
    //knap sack
    // f[i][j] stand for true or false from range i  to range j
    private  boolean good(int[][] button, int[] range) {
        boolean[][] f = new boolean[range[1] + 1][range[1] + 1];
        f[0][0] = true;
        for(int i = 0 ; i <= range[1] ; i++){
            for(int j = i ; j <= range[1] ; j++){
                for(int k = 0 ; k < button.length ; k++ ){
                    if(i - button[k][0] >= 0 && j - button[k][1] >=0){
                        f[i][j] |= f[i - button[k][0]][j - button[k][1]];
                    }
                }
            }
        }
        
        for(int i = range[0] ; i <= range[1] ; i++ ){
            for(int j = i ; j <= range[1] ; j++){
                if(f[i][j]) return true;
            }
        }
        return false;
    }
    
//    dfs
    private  boolean good2(int[][] button, int[] range) {
        return dfs(button , range , 0 , new int[]{0 , 0} );
    }
    private boolean dfs(int[][] button , int[] range , int index , int[] cur){
        if(cur[0] >= range[0] && cur[1] <= range[1]){
            return true;
        }
        if(cur[1] > range[1]){
            return false;
        }
        
        for(int i = index ; i < button.length ; i++){
            cur[0] += button[i][0];
            cur[1] += button[i][1];
            if(dfs(button , range , i , cur)){
                return true;
            }
            cur[0] -= button[i][0];
            cur[1] -= button[i][1];
        }
        return false;
    }
}