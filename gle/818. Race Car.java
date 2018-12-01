// 818. Race Car
// DescriptionHintsSubmissionsDiscussSolution
// Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)

// Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).

// When you get an instruction "A", your car does the following: position += speed, speed *= 2.

// When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)

// For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.

// Now for some target position, say the length of the shortest sequence of instructions to get there.

// Example 1:
// Input: 
// target = 3
// Output: 2
// Explanation: 
// The shortest instruction sequence is "AA".
// Your position goes from 0->1->3.
// Example 2:
// Input: 
// target = 6
// Output: 5
// Explanation: 
// The shortest instruction sequence is "AAARA".
// Your position goes from 0->1->3->7->7->6.



// For the input 5, we can reach with only 7 steps: AARARAA. Because we can step back.

// Let's say n is the length of target in binary and we have 2 ^ (n - 1) <= target < 2 ^ n
// We have 2 strategies here:
// 1. Go pass our target , stop and turn back
// We take n instructions of A.
// 1 + 2 + 4 + ... + 2 ^ (n-1) = 2 ^ n - 1
// Then we turn back by one R instruction.
// In the end, we get closer by n + 1 instructions.

// 2. Go as far as possible before pass target, stop and turn back
// We take N - 1 instruction of A and one R.
// Then we take m instructions of A, where m < n

//BFS + 剪枝 可以用位运算优化
class Solution {
    public int racecar(int target) {
        if(target == 0) return 0;
        Queue<int[]> que = new LinkedList();
        Set<String> set = new HashSet();
        set.add("0_-1");
        set.add("0_1");
        que.offer(new int[]{0 , 1});
        int step = 0;
        while(!que.isEmpty()){
            int size = que.size();
            step++;
            for(int i = 0 ; i < size ; i++){
                int[] cur = que.poll();
                String forward = String.valueOf(cur[0] + cur[1]) + "_" + (cur[1] * 2);
                String backward = cur[0] + "_" + (cur[1] > 0 ? -1 : 1);
                if(cur[0] + cur[1] == target || cur[0] == target){
                    return step;
                }
                if(!set.contains(forward)){
                    set.add(forward);
                    //BFS + 剪枝
                    if(Math.abs(cur[0] + cur[1]) < 2 * target && Math.abs(cur[1] * 2) < 2 * target){
                        que.offer(new int[]{cur[0] + cur[1] , cur[1] * 2});
                    }
                }
                if(!set.contains(backward)){
                    set.add(backward);
                    if(Math.abs(cur[0]) < 2 * target && Math.abs((cur[1] > 0 ? -1 : 1)) < 2 * target){
                        que.offer(new int[]{cur[0] , (cur[1] > 0 ? -1 : 1)});
                    }
                    
                }
            }
        }
        return -1;
    }
}





//错误
class Solution {
    public int racecar(int target) {
        int[] step = {0};
        int pos = 0;
        int speed = 1;
        while(target > pos){
            pos += speed;
            speed *= 2;
            step[0]++;
        }
        if(target == pos){
            return step[0];
        }

        recursion(target , pos - speed/2 , pos ,step);
        return step[0];
    }
    private boolean recursion(int target , int s , int e , int[] step){
        if(target == s || target == e){
            return true;
        }
        step[0]++;
        if(target - s <= e - target){
            int pos = s;
            int speed = 1;
            while(target > pos){
                pos += speed;
                speed *= 2;
                step[0]++;
            }
            System.out.println(step[0]);
            if(recursion(target , pos - speed/2 , pos ,step)){
                return true;
            }
        }
        else{
            int pos = e;
            int speed = -1;
            while(target < pos){
                pos += speed;
                speed *= 2;
                step[0]++;
            }
            if(recursion(target , pos, pos - speed/2 , step)){
                return true;
            }
        }
        return false;
    }
}