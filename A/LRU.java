
// 134. LRU Cache
// Design and implement a data structure for Least Recently Used (LRU) cache.
//  It should support the following operations: get and set.

// get(key) - Get the value (will always be positive) of the key 
// if the key exists in the cache, otherwise return -1.
// set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
// it should invalidate the least recently used item before inserting a new item.

public class LRUCache {
    /*
    * @param capacity: An integer
    */
    HashMap<Integer ,ListNode> map;
    ListNode head;
    ListNode tail;
    int capacity;
    class ListNode{
        int key , val;
        ListNode pre , next;
        ListNode(int key , int val){
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap();
        this.head = new ListNode(-1,-1);
        this.tail = new ListNode(-1,-1);
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
        ListNode cur = map.get(key);
    
        
        cur.next.pre = cur.pre;
        cur.pre.next = cur.next;

        cur.next = head.next;
        cur.pre = head;
        head.next.pre = cur;
        head.next = cur;


        return cur.val;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int val) {
        if(map.containsKey(key)){
            ListNode cur = map.get(key);
            cur.val = val;
            
            cur.next.pre = cur.pre;
            cur.pre.next = cur.next;

            cur.next = head.next;
            cur.pre = head;
            head.next.pre = cur;
            head.next = cur;
        }
        else{
            ListNode cur = new ListNode(key , val);
            if(map.size() == capacity){
                map.remove(tail.pre.key);
                tail.pre.pre.next = tail;
                tail.pre = tail.pre.pre;
            }
            map.put(key , cur);
            cur.next = head.next;
            cur.pre = head;
            head.next.pre = cur;
            head.next = cur;
        }
        
    }
}









public class LRUCache {

    HashMap<Integer, ListNode> map;
    ListNode dummy, last;

    private class ListNode {

        int key, value;
        ListNode pre, next;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }
    int size;

    public LRUCache(int size) {
        this.size = size;
        map = new HashMap();
        dummy = new ListNode(-1, -1);
        last = dummy;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode tmp = map.get(key);
            if(tmp.next != null){
                tmp.pre.next = tmp.next;
                tmp.next.pre = tmp.pre; //4
                tmp.next = null;
                tmp.pre = last;
                last.next = tmp;
                last = tmp;
                map.put(key, tmp);
                
            }
            return map.get(key).value;

        }
        
        return -1;
    }

    public void set(int key, int value) {
        if (size > 0) {
            if (map.containsKey(key)) {
                ListNode tmp = map.get(key);
                tmp.value = value;
                if(tmp.next != null){
                    tmp.pre.next = tmp.next;
                    tmp.next.pre = tmp.pre; //4
                    tmp.next = null;
                    tmp.pre = last;
                    last.next = tmp;
                    last = tmp;
                    map.put(key, tmp);
                }
            } else {
                ListNode cur = new ListNode(key, value);
                last.next = cur;            
                cur.pre = last;
                last = cur;
                map.put(key, cur);
                size--;
            }

        } else {
            if (map.containsKey(key)) {
                ListNode tmp = map.get(key);
                tmp.value = value;
                if(tmp.next != null){
                    tmp.pre.next = tmp.next;
                    tmp.next.pre = tmp.pre; 
                    tmp.next = null;
                    tmp.pre = last;
                    last.next = tmp;
                    last = tmp;
                    map.put(key, tmp);
                }
            } else {
                map.remove(dummy.next.key);
                dummy.next = dummy.next.next;
                if(map.size() > 1){
                    dummy.next.pre = dummy;
                }
                ListNode cur = new ListNode(key, value);
                last.next = cur;
                cur.pre = last;
                last = cur;
                map.put(key, cur);

            }

        }
    }
}

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
