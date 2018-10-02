
// 134. LRU Cache
// Design and implement a data structure for Least Recently Used (LRU) cache. 
// It should support the following operations: get and set.

// get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
// set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
//  it should invalidate the least recently used item before inserting a new item.



public class LRUCache {
    /*
    * @param capacity: An integer
    */
    class ListNode{
        int key , val;
        ListNode pre , next;
        ListNode(int key ,int val){
            this.key = key;
            this.val = val;
            this.pre = this.next = null;
        }
    }
    HashMap<Integer , ListNode> map;
    ListNode head;
    ListNode tail;
    int cap;
    public LRUCache(int capacity) {
        cap = capacity;
        map = new HashMap();
        head = new ListNode(-1 ,-1);
        tail = new ListNode(-2 ,-2);
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
        else{
            ListNode cur = map.get(key);
            cur.next.pre = cur.pre;
            cur.pre.next = cur.next;

            cur.next = head.next;
            head.next.pre = cur;
            head.next = cur;
            cur.pre = head; 
            return cur.val;
        }
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int val) {
        if(map.containsKey(key)){
            ListNode cur = map.get(key);
            cur.val = val; // over write the val !

            cur.next.pre = cur.pre;
            cur.pre.next = cur.next;

            cur.next = head.next;
            head.next.pre = cur;
            head.next = cur;
            cur.pre = head;    
        }    
        else{
            ListNode cur = new ListNode(key , val);
            if(map.size() < cap){  
                map.put(key , cur);
                cur.next = head.next;
                head.next.pre = cur;
                head.next = cur;
                cur.pre = head;
            }
            else{
                map.remove(tail.pre.key);
                map.put(key , cur);
                tail.pre.pre.next = tail;
                tail.pre = tail.pre.pre;
        
                cur.next = head.next;
                head.next.pre = cur;
                head.next = cur;
                cur.pre = head;

            }
        }
    }
}



































public class LRUCache {
    /*
    * @param capacity: An integer
    */
    HashMap<Integer , ListNode> map;
    ListNode head;
    ListNode tail;
    int size;
    class ListNode{
        int key , value;
        ListNode pre , next;    
        ListNode(int key , int value){
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null; 
        }      
    }
    
    public LRUCache(int capacity) {
        this.size = capacity;
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
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode cur = map.get(key);
    
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;

        cur.next = tail;
        cur.pre = tail.pre;
        tail.pre.next = cur;
        tail.pre = cur;
        return cur.value;
    }
    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        if(map.containsKey(key)){
            ListNode cur = map.get(key);
            cur.value = value;
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;

            cur.next = tail;
            cur.pre = tail.pre;
            tail.pre.next = cur;
            tail.pre = cur;
            
        }
        else{
            if(size > map.size()){
                ListNode cur = new ListNode(key , value);
                map.put(key , cur);
                cur.next = tail;
                cur.pre = tail.pre;
                tail.pre.next = cur;
                tail.pre = cur;
            }
            else{
                ListNode cur = new ListNode(key , value);
                map.remove(head.next.key);
                head.next.next.pre = head;
                head.next = head.next.next;
                
                map.put(key , cur);
                cur.next = tail;
                cur.pre = tail.pre;
                tail.pre.next = cur;
                tail.pre = cur;
            
            }
        }
    }
}