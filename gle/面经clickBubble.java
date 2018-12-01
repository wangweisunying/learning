// union find tread all connected point as the whole and connected them together , res += ct - 1;

int[] f;
int m , n;
private int getMaxBubble2(int[][] nums){
    m = nums.length;
    n = nums[0].length;
    f = new int[m * n];
    for(int i = 0 ; i < m * n ; i++){
        f[i] = i;
    }
    
    System.out.println(Arrays.toString(f));
    for(int i = 0 ; i < m ; i++){
        for(int j = 0 ;j < n ; j++){
            if(nums[i][j] == 1){
                int tmpI = i;
                int tmpJ = j; 
                j++;
                while(j < n && nums[i][j] == 0){
                    ++j;
                }
                if(j < n){
                    union( tmpI * tmpJ + tmpJ , i * j + j );
                }
                i = tmpI;
                j = tmpJ;
            }
        }
    }
    
    for(int i = 0 ; i < n ; i++){
        for(int j = 0 ;j < m ; j++){
            if(nums[j][i] == 1){
                int tmpI = i;
                int tmpJ = j; 
                j++;
                while(j < m && nums[j][i] == 0){
                    ++j;
                }
                if(j < m){
                    union( tmpI * tmpJ + tmpI , i * j + i );
                }
                i = tmpI;
                j = tmpJ;
            }
        }
    }
    
    
    
    for(int i = 0 ; i < m ; i++){
        for(int j = 0 ;j < n ; j++){
            find(i * j + j);
        }
    }

    HashMap<Integer ,Integer> map = new HashMap();
    for(int i : f){
        map.put(i , map.getOrDefault(i , 0) + 1);
    }
    System.out.println(Arrays.toString(f));
    int res = 0;
    for(int i : map.values()){
        res += i - 1;
    }
    return res;
} 
    
    


// dfs
    int m , n;
private int getMaxBubble(int[][] nums){
    m = nums.length;
    n = nums[0].length;
    int[] row = new int[m];
    int[] col = new int[n];
    for(int i = 0 ; i < m ; i++){
        for(int j = 0 ; j < n ;j++){
            if(nums[i][j] == 1){
                row[i]++;
                col[j]++;
            }
        }
    }

    int[] max = new int[1];
    dfs(nums , row , col , 0 , max);
    return max[0];
}
private void dfs(int[][] nums , int[] row , int[] col , int ct , int[] max){
    max[0] = Math.max(max[0] , ct);
    for(int i = 0 ; i < m ; i++){
        for(int j = 0 ; j < n ;j++){
            if(nums[i][j] == 1){
                if(row[i] > 1 && col[j] > 1){
                    row[i]--;
                    col[j]--;
                    nums[i][j] = 0;
                    dfs(nums , row , col ,ct + 1 , max);
                    nums[i][j] = 1;
                    row[i]++;
                    col[j]++;
                }
            }
        }
    }
}