    public int[] kClosestNumbers(int[] nums, int target, int k) {
   
        int[] res = new int[k];
        int s = 0 , e = nums.length - 1 ,small = -1;
        while(s + 1 < e){
            int m = (s + e) / 2;
            if(nums[m] == target){
                e = m;
            }
            else if(nums[m] < target){
                s = m; //一定要把 s = m 包含进去
            }
            else{
                e = m; 
            }
        }
        if(nums[e] < target){ // find last position
            small = e;
        }
        if(nums[s] < target){
            small = s;
        }
            //
        int l = small , r = small + 1;
        for(int i = 0 ; i < k ; i++){
            if(isLeftCloser(nums,target,l,r)){
                res[i] = nums[l--];
            }
            else{
                res[i] = nums[r++];
            }
        }
        return res;
    }
        private boolean isLeftCloser(int[] A, int target, int left, int right) {
        if (left < 0) {
            return false;
        }
        
        if (right >= A.length) {
            return true;
        }
        
        if (target - A[left] <= A[right] - target) {
            return true;
        }
        
        return false;
    }
}