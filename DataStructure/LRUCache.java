// //  LRUCache
// // Design and implement a data structure for Least Recently Used (LRU) cache. 
// It should support the following operations: get and set.

// // get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
//  otherwise return -1.
// // set(key, value) - Set or insert the value if the key is not already present.
//  When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.


public class LRUCache {
    /*
    * @param capacity: An integer
    */
    class ListNode{
        int key , val;
        ListNode pre;
        ListNode next;
        ListNode(int key , int val){
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }
    HashMap<Integer , ListNode> map;
    ListNode head;
    ListNode tail;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap();
        head = new ListNode(-1 , -1);
        tail = new ListNode(-1 , -1);
        head.next = tail;
        tail.pre = head;

        // do need to put head and tail into the map
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
     
// // get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
//  otherwise return -1.
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode cur = map.get(key);
        cur.next.pre = cur.pre;
        cur.pre.next = cur.next;

        cur.next = tail;
        cur.pre = tail.pre;

        tail.pre.next = cur;
        tail.pre = cur;
        return cur.val;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        if(get(key) != -1){
            map.get(key).val = value;
            return;
        }
        if(capacity == map.size()){
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.pre = head;
        }
        ListNode node = new ListNode(key , value);
        map.put(key , node);
        node.next = tail;
        node.pre = tail.pre;
        tail.pre.next = node;
        tail.pre = node;
    }
}


//
public class LRUCache {
    class ListNode{
        ListNode pre;
        ListNode next;
        int key;
        int val;
        ListNode(int key , int val){
            this.key = key;
            this.val = val;
            pre = null;
            next = null;
        }
        
    }
    int capacity;
    ListNode head ;
    ListNode tail ; 
    HashMap<Integer , ListNode> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new ListNode(-1 , -1);
        tail = new ListNode(-1 , -1);
        map = new HashMap();
        head.next = tail;
        tail.pre = head;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        //remove cur node from listNode  && move to tail 
        ListNode cur = map.get(key);
        cur.next.pre = cur.pre;
        cur.pre.next = cur.next;
        moveToTail(cur);
        return cur.val;
    }

    public void set(int key, int value) {
        if (get(key) != -1) {
            map.get(key).val = value;
            return;
        }
        if(map.size() == capacity){
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.pre = head;
        }
        ListNode node = new ListNode(key , value);
        map.put(key , node);
        moveToTail(node);
    }
    private void moveToTail(ListNode cur){
        cur.next = tail;
        cur.pre = tail.pre;
        cur.pre.next = cur;
        tail.pre = cur;
    }
}