// 228Middle of Linked List
// Find the middle node of a linked list.

// Example
// Given 1->2->3, return the node with value 2.

// Given 1->2, return the node with value 1.

// Challenge
// If the linked list is in a data stream, can you find the middle without iterating the linked list again?

/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param head: the head of linked list.
     * @return: a middle node of the linked list
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head ,fast = head;
        while(fast != null && fast.next!= null){
            fast = fast.next.next;
            if(fast != null){
                slow = slow.next;
            }
            
        }
        return slow;
    }
}


public class Solution {
    /*
     * @param head: the head of linked list.
     * @return: a middle node of the linked list
     */
    public ListNode middleNode(ListNode head) {
        if(head == null) return null;
        ListNode slow  = head;
        ListNode fast = head.next;
        while(fast!= null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}