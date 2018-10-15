// Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

// k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

// Example:

// Given this linked list: 1->2->3->4->5

// For k = 2, you should return: 2->1->4->3->5

// For k = 3, you should return: 3->2->1->4->5

// Note:

// Only constant extra memory is allowed.
// You may not alter the values in the list's nodes, only nodes itself may be changed.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return null;
        }
        int ct = 0;
        ListNode ctNode = head;
        while(ctNode != null){
            ct++;
            ctNode = ctNode.next;
        }
        if(ct < k){
            return head;
        }


        int total_ct = ct / k;
        ListNode res = null;
        ListNode[] tmp1 = reverseNode(head , k , new ListNode(-1));
        res = tmp1[0];
        total_ct--;
        ListNode dummy = tmp1[1];
        while(total_ct > 0){
            ListNode[] tmp = reverseNode(dummy.next , k , dummy);
            dummy = tmp[1];
            total_ct --;
        }
        return res;
    }
    private ListNode[] reverseNode(ListNode head , int k , ListNode dummy){
        ListNode tail = head;
        ListNode pre = null;
        int ct = 0;
        while(ct < k && head!= null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            ct++;
        }
        tail.next = head;
        dummy.next = pre;
        return new ListNode[]{pre , tail};
    }
}


