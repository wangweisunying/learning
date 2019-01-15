// 2. Add Two Numbers
// DescriptionHintsSubmissionsDiscussSolution
// You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

// You may assume the two numbers do not contain any leading zero, except the number 0 itself.

// Example:

// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 0 -> 8
// Explanation: 342 + 465 = 807.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l11, ListNode l22) {
        
        ListNode node = new ListNode(-1);
        ListNode dummy = node;
        boolean add = false;
        while(l11 != null && l22 != null){
            int sum = add ? l11.val + l22.val + 1 : l11.val + l22.val;
            node.val = sum % 10;
            add = sum >= 10 ? true : false; 
            l11 = l11.next;
            l22 = l22.next;
            if(l11 != null || l22 != null ){
                node.next = new ListNode(-1);
                node = node.next;
            }
            
        }
        while(l11 != null){
            int sum = add ?l11.val + 1 : l11.val;
            node.val = sum % 10;
            add = sum >= 10 ? true : false; 
            l11 = l11.next;
            if(l11 != null ){
                node.next = new ListNode(-1);
                node = node.next;
            }
            
        }
        while(l22 != null){
            int sum = add ?l22.val + 1 : l22.val;
            node.val = sum % 10;
            add = sum >= 10 ? true : false; 
            l22 = l22.next;
            if(l22 != null ){
                node.next = new ListNode(-1);
                node = node.next;
            }
        }
        if(add){
            node.next = new ListNode(1);
        }
        
        return dummy;
    }
}