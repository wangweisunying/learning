// 817. Linked List Components
// We are given head, the head node of a linked list containing unique integer values.

// We are also given the list G, a subset of the values in the linked list.

// Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.

// Example 1:

// Input: 
// head: 0->1->2->3
// G = [0, 1, 3]
// Output: 2
// Explanation: 
// 0 and 1 are connected, so [0, 1] and [3] are the two connected components.
// Example 2:

// Input: 
// head: 0->1->2->3->4
// G = [0, 3, 1, 4]
// Output: 2
// Explanation: 
// 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
// Note:

// If N is the length of the linked list given by head, 1 <= N <= 10000.
// The value of each node in the linked list will be in the range [0, N - 1].
// 1 <= G.length <= 10000.
// G is a subset of all values in the linked list.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// [1,2,0,4,3]
// [3,4,0,2,1]
// Output:
// 2
// Expected:
// 1
//


// [0,1,2]
// [1,0]

// 2
// Expected:
// 1
class Solution {
    public int numComponents(ListNode head, int[] G) {
        HashSet<Integer> set = new HashSet();
        for(int i : G){
            set.add(i);
        }
        HashMap<Integer , List<Integer>> map = new HashMap();
        int u = head.val;
        int v = 0;
        while(head.next != null){
            head = head.next;
            v = head.val;
            if(set.contains(u) && set.contains(v)){
                map.computeIfAbsent(u , x -> new ArrayList()).add(v);
                map.computeIfAbsent(v , x -> new ArrayList()).add(u);
            }
            u = v;
        }
        // System.out.println(map);
        HashSet<Integer> visited = new HashSet();
        int ct = 0;
        for(int key : G){
            if(visited.contains(key)){
                continue;
            }
            dfs(visited , key , map);
            ct++;
        }     
        return ct;
    }
    private void dfs(HashSet<Integer> visited , int key ,HashMap<Integer , List<Integer>> map ){
        if(visited.contains(key)){
            return;
        }
        
        visited.add(key);
        if(!map.containsKey(key)){
            return;
        }
        for(int nei : map.get(key)){
            dfs(visited , nei , map);
        }
    }
}

// {0=[4], 1=[2], 2=[0], 3=[], 4=[3]}





class Solution {
    public int numComponents(ListNode head, int[] G) {
        HashSet<Integer> set = new HashSet();
        for(int i : G){
            set.add(i);
        }

        int ct = 0;
        boolean con = false;
        while(head != null){
            if(set.contains(head.val)){
                if(!con){
                    con = true;
                    ct++;
                }
            }
            else{
                con = false;
            }
            head = head.next;
        }
        return ct;
    }
}
//  0->1->2->3->4
// [0, 3, 1, 4]

//2