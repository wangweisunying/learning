
// Intersection of Two Linked Lists
// Write a program to find the node at which the intersection of two singly linked lists begins.

// Example
// The following two linked lists:

// A:          a1 → a2
//                    ↘
//                      c1 → c2 → c3
//                    ↗            
// B:     b1 → b2 → b3
// begin to intersect at node c1.

// Challenge
// Your code should preferably run in O(n) time and use only O(1) memory.

// Notice
// If the two linked lists have no intersection at all, return null.
// The linked lists must retain their original structure after the function returns.
// You may assume there are no cycles anywhere in the entire linked structure.



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
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        HashSet<Integer> setA = new HashSet();
        HashSet<Integer> setB = new HashSet();
        while(headA != null && headB != null){
            if(!setB.contains(headA.val)){
                setA.add(headA.val);
                headA = headA.next;
            }
            else{
                return headA;
            }
            if(!setA.contains(headB.val)){
                setB.add(headB.val);
                headB = headB.next;
            }
            else{
                return headB;
            }
        }
        while(headB != null){
            if(!setA.contains(headB.val)){
                setB.add(headB.val);
                headB = headB.next;
            }
            else{
                return headB;
            }
        }
        while(headA != null){
            if(!setB.contains(headA.val)){
                setA.add(headA.val);
                headA = headA.next;
            }
            else{
                return headA;
            }
        }        
        return null;
    }
}


public class Solution {
    /**
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode 
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        // get the tail of list A.
        ListNode node = headA;
        while (node.next != null) {
            node = node.next;
        }
        node.next = headB;
        ListNode result = listCycleII(headA);
        node.next = null;
        return result;
    }
    
    private ListNode listCycleII(ListNode head) {
        ListNode slow = head, fast = head.next;
        
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            
            slow = slow.next;
            fast = fast.next.next;
        }
        
        slow = head;
        fast = fast.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}