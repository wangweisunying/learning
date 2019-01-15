// 706. Design HashMap
// DescriptionHintsSubmissionsDiscussSolution
// Design a HashMap without using any built-in hash table libraries.

// To be specific, your design should include these functions:

// put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
// get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
// remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

// Example:

// MyHashMap hashMap = new MyHashMap();
// hashMap.put(1, 1);          
// hashMap.put(2, 2);         
// hashMap.get(1);            // returns 1
// hashMap.get(3);            // returns -1 (not found)
// hashMap.put(2, 1);          // update the existing value
// hashMap.get(2);            // returns 1 
// hashMap.remove(2);          // remove the mapping for 2
// hashMap.get(2);            // returns -1 (not found) 

// Note:

// All keys and values will be in the range of [0, 1000000].
// The number of operations will be in the range of [1, 10000].
// Please do not use the built-in HashMap library.

// ["MyHashMap","put","put","get","get","put","get", "remove", "get"]
// [[],[1,1],[2,2],[1],[3],[2,1],[2],[2],[2]]
class MyHashMap {

    private Node [] arr;

    private class Node{
        int key;
        int val;
        Node next;
        Node(int key ,int val){
            this.key = key;
            this.val = val;
        }
    }
    /** Initialize your data structure here. */
    public MyHashMap() {
        arr = new Node[10000];
    }
    
    public void put(int key , int val) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node == null){
            arr[hash] = new Node(key ,val);
        }else{
            Node pre = node;
            while(node != null){
                if(node.key == key){
                    node.val = val; 
                    return; 
                }
                pre = node; 
                node = node.next;
            }
            pre.next = new Node(key , val); 
        }
    }
    public int get(int key) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node == null) return -1;
        else{
            while(node != null){
                if(node.key == key)return node.val; 
                node = node.next;
            }
            return -1;
        }
    }
    
    public void remove(int key) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node !=null){
            if(node.key == key){
                arr[hash] = node.next;
            }else {
                Node pre = node;
                node = node.next;
                while(node!= null){
                    if(node.key == key){
                        pre.next = node.next;
                        return;
                    }
                    pre = node; 
                    node = node.next;
                }
            }
        }
    }
    
    private int hash(int key){
        return key % arr.length;
    }
}




class MyHashMap {

    private Node [] arr;

    private class Node{
        int key;
        int val;
        Node next;
        Node(int key ,int val){
            this.key = key;
            this.val = val;
        }
    }

    
    /** Initialize your data structure here. */
    public MyHashMap() {
        arr = new Node[10000];
    }
    
    public void put(int key , int val) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node == null){
            arr[hash] = new Node(key ,val);
        }else{
            while (node.next!=null && node.next.key != key){
                node = node.next;
            }
            if(node.key == key) node.val = val; 
            else node.next = new Node(key , val);
        }
    }
    public int get(int key) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node == null) return -1;
        else{
            while(node != null){
                if(node.key == key)return node.val; 
                node = node.next;
            }
            return -1;
        }
    }
    
    public void remove(int key) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node !=null){
            if(node.key == key){
                arr[hash] = node.next;
            }else {
                while (node.next!=null && node.next.key != key){
                    node = node.next;
                }
                if(node.next !=null){
                    node.next = node.next.next;
                }
            }
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = hash(key);
        Node node = arr[hash];
        if(node !=null){
            if(node.key == key){
                return true;
            }else {
                while (node.next!=null && node.next.key != key){
                    node = node.next;
                }
                if(node.next !=null){
                    return true;
                }
            }
        }
        return false;
    }
    
    private int hash(int key){
        return key % arr.length;
    }
}
