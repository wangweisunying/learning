
// Insert into a Cyclic Sorted List
// Given a node from a cyclic linked list which has been sorted, write a function to insert a value into the list such that it remains a cyclic sorted list.
//  The given node can be any single node in the list. Return the inserted new node.

// Example
// Given a list, and insert a value 4:
// 3->5->1
// Return 5->1->3->4

// Notice
// 3->5->1 is a cyclic list, so 3 is next node of 1.
// 3->5->1 is same with 5->1->3



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
Input
3->5->1->2
7
Output
5->1->2->7->3
Expected
7->1->2->3->5

public class Solution {
    /*
     * @param node: a list node in the list
     * @param x: An integer
     * @return: the inserted new list node
     */
    public ListNode insert(ListNode node, int x) {
        if(node == null){
            node = new ListNode(x);
            node.next = node;
            return node;
        }
        ListNode head = node;
        ListNode minNode = node;
        int pre = node.val;
        while(node.next != head){
            if(pre > node.val){
                pre = node.val;
                minNode = node;
            }
            node = node.next;
        }
        head = minNode;
        while(minNode.next != head){
            if(minNode.val <= x && x <= minNode.next.val){ 
                ListNode cur = new ListNode(x);
                cur.next = minNode.next;
                minNode.next = cur;
                return cur;   
            }
            minNode = minNode.next;
        }
        ListNode cur = new ListNode(x);
        cur.next = minNode.next;
        minNode.next = cur;
        return cur;
    }
}


public class Solution {
    /**
     * @param node a list node in the list
     * @param x an integer
     * @return the inserted new list node
     */
    public ListNode insert(ListNode node, int x) {
        // Write your code here
       if (node == null) {
            node = new ListNode(x);
            node.next = node;
            return node;
        }

        ListNode p = node;
        ListNode prev = null;
        do {
            prev = p;
            p = p.next;
            if (x <= p.val && x >= prev.val) { 
                break;
            }
            if ((prev.val > p.val) && (x < p.val || x > prev.val)) {
                break;
            }
        } while (p != node);

        ListNode newNode = new ListNode(x);
        newNode.next = p;
        prev.next = newNode;
        return newNode;
    }
}