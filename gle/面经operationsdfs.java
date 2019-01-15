public class DFS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DFS test = new DFS();
        System.out.println(test.getRes(new int[]{1 ,2 ,3 ,4} , new String[]{"+" , "-" , "*" ,"/"}));
        
    }
    
    private List<String> getRes(int[] nums , String[] op){
        List<String> res = new ArrayList();
        dfs(res , nums , op , 0  , 0 , "");
        return res;
    }
    private void dfs(List<String> res, int[] nums , String[] op , int index , int numIndex ,String cur ){
        if(numIndex == nums.length){
            res.add(cur.substring(1, cur.length()));
            return;
        }
        for(int i = index ; i < op.length ; i++){
            dfs(res , nums , op , i , numIndex + 1 , cur + op[i] + nums[numIndex]);
        }
    }
    
}