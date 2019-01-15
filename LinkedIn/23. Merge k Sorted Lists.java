// 23. Merge k Sorted Lists
// Hard

// 1851

// 120

// Favorite

// Share
// Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

// Example:

// Input:
// [
//   1->4->5,
//   1->3->4,
//   2->6
// ]
// Output: 1->1->2->3->4->4->5->6
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */




class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> que = new PriorityQueue<>((a,b) ->(a.val - b.val));
        for(ListNode i : lists){
            if(i != null)que.offer(i);
        } 
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        while(!que.isEmpty()){
            ListNode cur = que.poll();
            if(cur.next != null) que.offer(cur.next);
            pre.next = cur;
            pre = pre.next;
        }
        return dummy.next;
    }
}