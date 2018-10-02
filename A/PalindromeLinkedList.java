// Palindrome Linked List
// Implement a function to check if a linked list is a palindrome.

// Example
// Given 1->2->1, return true

// Challenge
// Could you do it in O(n) time and O(1) space?


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
     * @param head: A ListNode.
     * @return: A boolean.
     */
    public boolean isPalindrome(ListNode head) {
        //find middian
        if(head == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        

        ListNode mid = slow;
        
        ListNode pre = slow;
        ListNode rev = slow.next;
        if(rev == null){
            return slow.val == head.val;
        }
        
        slow = slow.next;
        while(slow != null){
            slow = slow.next;
            rev.next = pre;
            pre = rev;
            rev = slow;
        }
        
        while(head != mid && pre != mid){

            if(head.val != pre.val){
                System.out.println(head.val);
                System.out.println(pre.val);
                return false;
            }
            System.out.println("head" + head.val);
            System.out.println("pre" + pre.val);
            head = head.next;
            pre = pre.next;
        }
        if(head != mid){
            return head.val == mid.val;
        }
        return true;



    }
}




public class Solution {
    /**
     * @param head a ListNode
     * @return a boolean
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        
        ListNode middle = findMiddle(head);
        middle.next = reverse(middle.next);
        
        ListNode p1 = head, p2 = middle.next;
        while (p1 != null && p2 != null && p1.val == p2.val) {
            p1 = p1.next;
            p2 = p2.next;
        }
        
        return p2 == null;
    }
    
    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        
        return prev;
    }
}








public class Solution {
    /**
     * @param head: A ListNode.
     * @return: A boolean.
     */
    public boolean isPalindrome(ListNode head) {
        ListNode reverse = null;
        ListNode pre = null;
        ListNode start = head;
        while(head != null){
            reverse = new ListNode(head.val);
            reverse.next = pre;
            pre = reverse; 
            head = head.next;
        }
        while(start != null){
            if(start.val != reverse.val){
                return false;
            }
            start = start.next;
            reverse = reverse.next;
        }
        return true;
        
    }
}