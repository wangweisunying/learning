// 403. Frog Jump
// DescriptionHintsSubmissionsDiscussSolution
// A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. 
// The frog can jump on a stone, but it must not jump into the water.

// Given a list of stones' positions (in units) in sorted ascending order,
//  determine if the frog is able to cross the river by landing on the last stone. Initially,
//  the frog is on the first stone and assume the first jump must be 1 unit.

// If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

// Note:

// The number of stones is ≥ 2 and is < 1,100.
// Each stone's position will be a non-negative integer < 231.
// The first stone's position is always 0.
// Example 1:

// [0,1,3,5,6,8,12,17]

// There are a total of 8 stones.
// The first stone at the 0th unit, second stone at the 1st unit,
// third stone at the 3rd unit, and so on...
// The last stone at the 17th unit.

// Return true. The frog can jump to the last stone by jumping 
// 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
// 2 units to the 4th stone, then 3 units to the 6th stone, 
// 4 units to the 7th stone, and 5 units to the 8th stone.
// Example 2:

// [0,1,2,3,4,8,9,11]

// Return false. There is no way to jump to the last stone as 
// the gap between the 5th and 6th stone is too large.



// Algorithm

// In the DP Approach, 
// we make use of a hashmap mapmap which contains key:valuekey:value pairs such that keykey refers to the position at which
//  a stone is present and valuevalue is a set containing the jumpsizejumpsize which can lead to the current stone position.
//   We start by making a hashmap whose keykeys are all the positions at which a stone is present and the valuevalues 
//   are all empty except position 0 whose value contains 0. Then, we start traversing the elements(positions) of the given stone array in sequential order.
//    For the currentPositioncurrentPosition, for every possible jumpsizejumpsize in the valuevalue set, 
//    we check if currentPosition + newjumpsizecurrentPosition+newjumpsize exists in the mapmap, 
//    where newjumpsizenewjumpsize can be either jumpsize-1jumpsize−1, jumpsizejumpsize, jumpsize+1jumpsize+1.
//     If so, we append the corresponding valuevalue set with newjumpsizenewjumpsize. We continue in the same manner.
//      If at the end, the valuevalue set corresponding to the last position is non-empty, we conclude that reaching the end is possible,
//       otherwise, it isn't.



// Use map to represent a mapping from the stone (not index) to the steps that can be taken from this stone.

// so this will be

// [0,1,3,5,6,8,12,17]

// {17=[], 0=[1], 1=[1, 2], 3=[1, 2, 3], 5=[1, 2, 3], 6=[1, 2, 3, 4], 8=[1, 2, 3, 4], 12=[3, 4, 5]}

// Notice that no need to calculate the last stone.

// On each step, we look if any other stone can be reached from it, if so,
//  we update that stone's steps by adding step, step + 1, step - 1. If we can reach the final stone, we return true. No need to calculate to the last stone.


class Solution {
    public boolean canCross(int[] stones) {
        HashMap<Integer , Set<Integer>> map = new HashMap();
        for(int stone : stones) map.put(stone , new HashSet());
        map.get(0).add(0);
        for(int i = 0 ; i < stones.length ; i++){
            for(int step : map.get(stones[i])){
                // if(map.containsKey(stones[i] + step - 1) && step - 1 > 0) map.get(stones[i] + step - 1).add(step - 1);
                // if(map.containsKey(stones[i] + step)) map.get(stones[i] + step).add(step);
                // if(map.containsKey(stones[i] + step + 1)) map.get(stones[i] + step + 1).add(step + 1);
                for(int j = step - 1 ; j <= step + 1 ; j++){
                    // j > 0 需要一直往后走 ， 如果j == 0, 相当于原地修改 map.get(stones[i]) 会抛出 java.util.ConcurrentModificationException
                    if(j > 0 && map.containsKey(stones[i] + j)){
                        map.get(stones[i] + j).add(j);
                    }
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() > 0;
    }
}

public class Solution {
    public boolean canCross(int[] stones) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }
        map.get(0).add(0);
        for (int i = 0; i < stones.length; i++) {
            for (int k : map.get(stones[i])) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && map.containsKey(stones[i] + step)) {
                        map.get(stones[i] + step).add(step);
                    }
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() > 0;
    }
}