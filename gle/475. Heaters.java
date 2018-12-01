// 475. Heaters
// DescriptionHintsSubmissionsDiscussSolution
// Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

// Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all 
// houses could be covered by those heaters.

// So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum
//  radius standard of heaters.

// Note:
// Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
// Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
// As long as a house is in the heaters' warm radius range, it can be warmed.
// All the heaters follow your radius standard and the warm radius will the same.
// Example 1:
// Input: [1,2,3],[2]
// Output: 1
// Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
// Example 2:
// Input: [1,2,3,4],[1,4]
// Output: 1
// Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.




class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        if(houses== null || houses.length == 0) return 0;
        Arrays.sort(heaters);
        int max = 0;
        for(int house : houses){
            max = Math.max(max ,bs(heaters , house));
        }        
        return max;
    }
    private int bs(int[] nums , int target){
        int s = 0;
        int e = nums.length - 1;
        while(s + 1 < e){
            int mid = (e - s) / 2 + s;
            if(target == nums[mid]){
                return 0;
            }
            else if( target < nums[mid]){
                e = mid;
            }
            else{
                s = mid;
            }
        } 
        return Math.min(Math.abs(target - nums[s]) , Math.abs(target - nums[e]));
    }
}






























 public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int maxMin = 0;
        for(int house : houses) {
            int pos = Arrays.binarySearch(heaters, house);
            if(pos >= 0) {
                continue;
            }
            int distance;
            int ip = - (pos + 1);
            if(ip == 0) {
                distance = heaters[0] - house;
            }
            else if(ip == heaters.length) {
                distance = house - heaters[heaters.length - 1];
            }
            else {
                distance = Math.min(heaters[ip] - house, house - heaters[ip-1]);
            }
            maxMin = Math.max(distance, maxMin);
        }
        return maxMin;
    }


    

 public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> set = new TreeSet<>(); 
        for (int heater : heaters)  
            set.add(heater); 
        
        int index = 0, res = 0; 
        for (int house : houses) {
            int dist1 = set.ceiling(house) == null ? Integer.MAX_VALUE : set.ceiling(house) - house; 
            int dist2 = set.floor(house) == null ? Integer.MAX_VALUE : house - set.floor(house); 
            res = Math.max(res, Math.min(dist1, dist2)); 
        }    
        
        return res; 
    }