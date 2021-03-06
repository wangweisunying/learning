// 128. Longest Consecutive Sequence
// DescriptionHintsSubmissionsDiscussSolution
// Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

// Your algorithm should run in O(n) complexity.

// Example:

// Input: [100, 4, 200, 1, 3, 2]
// Output: 4
// Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.


//只需要去set里不断的去找到左右两边出现的值 并同时删除 ， mantian max when doing this.
public int longestConsecutive(int[] nums) {
    HashSet<Integer> set = new HashSet();
    for(int i : nums) set.add(i);
    int max = 0;
    for(int i = 0 ; i < nums.length ; i++ ){
        if(set.contains(nums[i])){
            int tmp = 1;
            int left = nums[i] - 1;
            int right = nums[i] + 1;
            set.remove(nums[i]);
            while(set.contains(left)){
                set.remove(left--);
                tmp++;  
            }
            while(set.contains(right)){
                set.remove(right++);
                tmp++;  
            }
            max = Math.max(max , tmp);
        }
    }
    return max;
}


















public int longestConsecutive(int[] nums) {
    int res = 0; //cover the nums.length == 0;
    HashMap<Integer ,Integer> map = new HashMap(); // integer num , curMax length
    for(int num : nums){
        if(map.containsKey(num)){
            continue;
        }
        else{
            int left = map.containsKey(num - 1)? map.get(num - 1) : 0;
            int right = map.containsKey(num + 1)? map.get(num + 1) : 0;
            
            int curMaxLen = left + right + 1;
            
            res = Math.max(res , curMaxLen);

            //只需要最外侧的点是最新的值 each loop will only find the boundary point
            map.put(num , curMaxLen);
            map.put(num - left ,curMaxLen);
            map.put(nums + right , curMaxLen);
        }
    }
    return res;
}











// 1 , 2,2, 3 ,5, 6
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        int max = 1;
        int curMax = 1;
        int pre = nums[0];
        for(int i = 1 ; i < nums.length ; i++){
            if(nums[i] == pre){
                continue;
            }
            if(nums[i] == pre + 1){
                curMax ++;
            }
            else{
                max = Math.max(max , curMax);
                curMax = 1;
            }
            pre = nums[i];
        }
        max = Math.max(max , curMax);
        return max;
    }
}




// We will use HashMap. The key thing is to keep track of the sequence length and store that in the boundary points of the sequence. For example, as a result, 
// for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.

// Whenever a new element n is inserted into the map, do two things:

// See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n. Variables left and right will be the length of those two sequences,
//  while 0 means there is no sequence and n will be the boundary point later. Store (left + right + 1) as the associated value to key n into the map.
// Use left and right to locate the other end of the sequences to the left and right of n respectively, and replace the value with the new length.
// Everything inside the for loop is O(1) so the total time is O(n). Please comment if you see something wrong. Thanks.
// [100, 4, 200, 1, 3, 2]
public int longestConsecutive(int[] num) {
    int res = 0;
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int n : num) {
        if (!map.containsKey(n)) {
            int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
            int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
            // sum: length of the sequence n is in
            int sum = left + right + 1;
            map.put(n, sum);
            
            // keep track of the max length 
            res = Math.max(res, sum);
            
            // extend the length to the boundary(s)
            // of the sequence
            // will do nothing if n has no neighbors
            map.put(n - left, sum);
            map.put(n + right, sum);
        }
        else {
            // duplicates
            continue;
        }
    }
    return res;
}