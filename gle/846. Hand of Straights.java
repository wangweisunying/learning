// 846. Hand of Straights
// DescriptionHintsSubmissionsDiscussSolution
// Alice has a hand of cards, given as an array of integers.

// Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

// Return true if and only if she can.

 

// Example 1:

// Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
// Output: true
// Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
// Example 2:

// Input: hand = [1,2,3,4,5], W = 4
// Output: false
// Explanation: Alice's hand can't be rearranged into groups of 4.
 

// Note:

// 1 <= hand.length <= 10000
// 0 <= hand[i] <= 10^9
// 1 <= W <= hand.length

// [1,1,2,2,3,3]
// 3


class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer , Integer> map = new TreeMap();  
        for(int i : hand){
            map.put(i, map.getOrDefault(i , 0) + 1);
        }
        for(int i : map.keySet()){
            if(map.get(i) > 0){
                //用ct 可以少一些判断，把重复判断去除了 比如同一个数出现了多次 ，这时候用ct 只需要计算一轮
                int ct = map.get(i);
                
                for(int j = i ; j < i + W ; j++){
                    if(map.containsKey(j)){
                        map.put( j , map.get(j) - ct);
                    }    
                    else{
                        return false;
                    }
                } 
            }
            
        }
        
        return true;
    }
}

//treMap 利用 每次直接减去全部的ct 来加速！
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        int n = hand.length;
        if(n % W != 0) return false;
        Map<Integer ,Integer> map = new TreeMap();
        for(int i : hand){
            map.put(i , map.getOrDefault( i , 0) + 1);
        }
      
        for(int i : map.keySet()){
            if(map.get(i) > 0){
                int ct = map.get(i);
                for(int j = i ; j <  i + W  ; j++){
                    if(!map.containsKey(j) || map.get(j) <= 0) return false;
                    map.put(j , map.get(j) - ct );
                }
            }
        }
        
        return true;
    }
}