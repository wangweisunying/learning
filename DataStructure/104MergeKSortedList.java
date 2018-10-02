// Merge K Sorted Lists
// Merge k sorted linked lists and return it as one sorted list.

// Analyze and describe its complexity.

// Example
// Given lists:

// [
//   2->4->null,
//   null,
//   -1->null
// ],
// return -1->2->4->null.
/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists == null || lists.size() == 0){
            return null;
        }  
        ListNode dummy = new ListNode(-999);
        ListNode head = dummy;
        Queue<ListNode> que = new PriorityQueue(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode n1 , ListNode n2){
                return n1.val - n2.val;
            }
        });
        for(ListNode list: lists){
            if(list == null){
                continue;
            }
            que.offer(list);
        }
        

        while(!que.isEmpty()){
            ListNode cur = que.poll();
            head.next = cur;
            head = head.next;
            if(cur.next != null){
                que.offer(cur.next);
            }
        }
        return dummy.next;
    }

}






























/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        if(lists.isEmpty()){
            return null;
        }  
        Queue<Integer> que = new PriorityQueue<Integer>(new Comparator(){
                @Override
                public int compare(Object front , Object behind){
                    return (int)front - (int)behind;
                }
        });
// [null,-1->5->11->null,null,6->10->null]
        for(ListNode curNode : lists){
            if(curNode == null){
                continue;
            }
            que.offer(curNode.val);
            ListNode tmp = curNode;
            while(tmp.next != null){
                que.offer(tmp.next.val);
                tmp = tmp.next;
            }
                       
        }
        if(que.size() == 0){
            return null;
        }
        ListNode res = new ListNode(que.poll());
        ListNode pre = res;
        while(!que.isEmpty()){
            int x = que.poll();
            ListNode next = new ListNode(x);
            pre.next = next;
            pre = next;
        }
        return res;
    }
}