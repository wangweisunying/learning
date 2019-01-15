// 432. All O`one Data Structure
// DescriptionHintsSubmissionsDiscussSolution
// Implement a data structure supporting the following operations:

// // Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1.
//  Key is guaranteed to be a non-empty string.
// // Dec(Key) - If Key's value is 1, remove it from the data structure. 
// Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing.
//  Key is guaranteed to be a non-empty string.
// // GetMaxKey() - Returns one of the keys with maximal value. 
// If no element exists, return an empty string "".
// // GetMinKey() - Returns one of the keys with minimal value.
//  If no element exists, return an empty string "".





class AllOne {
    class ListNode{
        Set<String> keySet;
        int ct;
        ListNode pre , next;
        ListNode(String key , int ct){
            this.ct = ct;
            this.keySet = new HashSet(Arrays.asList(key));
        }
    }
    ListNode head , tail;
    Map<String , ListNode> map;
    /** Initialize your data structure here. */
    public AllOne() {
        head = new ListNode("head" , -1);
        tail = new ListNode("tail", Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        map = new HashMap();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(!map.containsKey(key)){
            if(head.next.ct == 1){
                head.next.keySet.add(key);
            }
            else{
                
                ListNode newNode = new ListNode(key , 1);
                
                head.next.pre = newNode;
                newNode.next = head.next;
                head.next = newNode;
                newNode.pre = head;
            }
            map.put(key, head.next);  
        } 
        else{
            ListNode cur = map.get(key);
            cur.keySet.remove(key);
            
            
            if(cur.next.ct == cur.ct + 1){
                cur.next.keySet.add(key);
                map.put(key , cur.next);
            }
            else{
                ListNode newNode = new ListNode(key , cur.ct + 1);
                
                cur.next.pre = newNode;
                newNode.next = cur.next;
                cur.next = newNode;
                newNode.pre = cur;
                map.put(key , newNode);
            }
            
            if(cur.keySet.isEmpty()){
                cur.next.pre = cur.pre;
                cur.pre.next = cur.next;
            }

        }
       

    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key)) return;
        ListNode cur = map.get(key);
        cur.keySet.remove(key);
        if(cur.ct == 1){
            map.remove(key);
        } 
        else{
            if(cur.pre.ct == cur.ct - 1){
                cur.pre.keySet.add(key);
                map.put(key , cur.pre);
            }
            else{
                ListNode newNode = new ListNode(key , cur.ct - 1);
                
                cur.pre.next = newNode;
                newNode.pre = cur.pre;
                newNode.next = cur;
                cur.pre = newNode;
                
                map.put(key , newNode);
            }
        }
        if(cur.keySet.isEmpty()){
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;    
        }

    

    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(tail.pre == head) return "";
        return tail.pre.keySet.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(head.next == tail) return "";
        return head.next.keySet.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
// 
class AllOne {

    /** Initialize your data structure here. */
    class Node{
        int val;
        Set<String> keySet;
        Node pre , next;
        Node(int val){
            this.val = val;
            this.keySet = new HashSet();
        }
    }
    Map<String , Integer> map;
    Map<Integer , Node > ctMap;
    Node head , tail;
    public AllOne() {
        map = new HashMap();
        ctMap = new HashMap();
        head = new Node(-1);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(map.containsKey(key)){
            Node curNode = ctMap.get(map.get(key));
            curNode.keySet.remove(key);
            if(curNode.keySet.isEmpty()){
                curNode.pre.next = curNode.next;
                curNode.next.pre = curNode.pre;
                ctMap.remove(map.get(key));
            }
            if(curNode.next.val == map.get(key) + 1 ){
                curNode.next.keySet.add(key);
            }
            else{
                curNode = curNode.keySet.isEmpty()? curNode.pre : curNode;
                Node newNode = new Node(map.get(key) + 1);
                newNode.keySet.add(key);
                curNode.next.pre = newNode;
                newNode.next = curNode.next;
                newNode.pre = curNode;
                curNode.next = newNode;
                
                ctMap.put(map.get(key) + 1 , newNode);
            }
            map.put(key , map.get(key) + 1);
        }
        else{
            map.put(key , 1);
            if(ctMap.containsKey(1)){
                Node curNode = ctMap.get(1);
                curNode.keySet.add(key);
            }
            else{
                Node newNode = new Node(1);
                newNode.keySet.add(key);
                head.next.pre = newNode;
                newNode.next = head.next;
                head.next = newNode;
                newNode.pre = head;
                ctMap.put(1, newNode);
            }
        }
        // System.out.println(map);
        // System.out.println(ctMap);
        // Node s = head;
        // while(s != null){
        //     System.out.print( " ->"+ s.keySet);
        //     s = s.next;
        // }
        // System.out.println();
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(map.containsKey(key)){
            Node curNode = ctMap.get(map.get(key));
            curNode.keySet.remove(key);
            if(curNode.keySet.isEmpty()){
                curNode.pre.next = curNode.next;
                curNode.next.pre = curNode.pre;
                ctMap.remove(map.get(key));
            }
            if(map.get(key) - 1 != 0){
                if(curNode.pre.val == map.get(key) - 1 ){
                    curNode.pre.keySet.add(key);
                }
                else{
                    Node newNode = new Node(map.get(key) - 1);
                    newNode.keySet.add(key);
                    newNode.next = curNode.next;
                    newNode.pre = curNode.pre;
                    curNode.pre.next = newNode;
                    curNode.next.pre = newNode;
                    ctMap.put(map.get(key) - 1 , newNode);
                }    
            }
            map.put(key , map.get(key) - 1);
            if(map.get(key) == 0) map.remove(key);
        }
        // System.out.println(map);
        // System.out.println(ctMap);
        // Node s = head;
        // while(s != null){
        //     System.out.print( " ->"+ s.keySet);
        //     s = s.next;
        // }
        // System.out.println();
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        // System.out.println("max : " + tail.pre.val);
        // System.out.println("max : " + tail.pre.keySet.iterator().next());
        if(tail.pre == head) return "";
        return tail.pre.keySet.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        // System.out.println("min : " + head.next.val);
        // System.out.println("min : " + head.next.keySet.iterator().next());
        if(head.next == tail) return "";
        return head.next.keySet.iterator().next();
    }
}







// insert and delte need O(logN);
class AllOne {

    /** Initialize your data structure here. */ 
    class Node{
        String key;
        int val;
        Node(String key , int val){
            this.key = key;
            this.val = val;
        }
    }
    Map<String , Node> map;
    Queue<Node> maxHeap;
    Queue<Node> minHeap;
    public AllOne() {
        map = new HashMap();
        minHeap = new PriorityQueue<>((a , b) ->(a.val - b.val));
        maxHeap = new PriorityQueue<>((a , b) ->(b.val - a.val));
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Node node = null;
        if(map.containsKey(key)){
            node = map.get(key);
            node.val += 1;
            minHeap.remove(node);
            maxHeap.remove(node);
        }
        else{
            node = new Node(key , 1);
            map.put(key , node);
        }
        minHeap.offer(node);
        maxHeap.offer(node);
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(map.containsKey(key)){
           Node node = map.get(key);
            node.val -= 1;
            minHeap.remove(node);
            maxHeap.remove(node);
            if(node.val == 0){
                map.remove(node);
            }
            else{
                minHeap.offer(node);
                maxHeap.offer(node); 
            }

        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(maxHeap.size() != 0){
            return maxHeap.peek().key;
        }
        return "";
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(minHeap.size() != 0){
            return minHeap.peek().key;
        }
        return "";
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */