public class twentyFour{
    

    public void getAllSolutions(double[] nums){
        dfs(nums , new boolean[4] , 0 , 0 ,"");
    }
    private void dfs(double[] nums ,boolean[] visited , int ct , double cur,String res ){
        if(ct == 4){
            if(Math.round(cur) == 24){
                System.out.println(res + " = 24");
            }
            return;
        }

        for(int i = 0 ; i < 4 ; i++){
            if(visited[i]){
                continue;
            }
            if(i > 0 ; nums[i] == nums[i - 1] && !visited[i - 1]){
                continue;
            }

            ct++;
            visited[i] = true;
            String tmp = res;
            if(ct == 1){
                res = String.valueOf(nums[i]);
                dfs(nums , visited , ct , nums[i] , res);
                res = tmp;
            }
            else{
                
                res = "(" + res + "+" + nums[i] + ")";
                dfs(nums , visited , ct ,cur + nums[i] , res);
                res = tmp;

                res = "(" + res + "-" + nums[i] + ")";
                dfs(nums , visited , ct , cur - nums[i] , res);
                res = tmp;
                
                res = "(" + nums[i] + "-" + res + ")";
                dfs(nums , visited , ct , nums[i] - cur , res);
                res = tmp;     

                res = "(" + res + "*" + nums[i] + ")";
                dfs(nums , visited , ct , cur * nums[i] , res);
                res = tmp;

                res = "(" + res + "/" + nums[i] + ")";
                dfs(nums , visited , ct , cur / nums[i] , res);
                res = tmp;

                res = "(" + nums[i] + "/" + res + ")";
                dfs(nums , visited , ct , nums[i] / cur , res);
                res = tmp;

            }

            visited[i] = false;
            ct--;
        }


    }
}
