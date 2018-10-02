// LFU Cache
// LFU (Least Frequently Used) is a famous cache eviction algorithm.

// For a cache with capacity k, if the cache is full and need to evict a key in it, the key with the lease frequently used will be kicked out.

// Implement set and get method for LFU cache.

// Example
// Given capacity=3


// Output
// [10,40,30,-1,10,10,-1,-1,40,50]
// Expected
// [10,40,30,-1,10,10,-1,30,-1,50]

public class LFUCache {
    int size;
    int len;
    TreeSet<Node> set;
    HashMap<Integer , Node> map;
    class Node{
        int key , value , ct , index;
        Node(int key , int value , int index){
            this.key = key;
            this.value = value;
            ct = 1;
            this.index = index;
        }
    }
    
    public LFUCache(int capacity) {
        len = 0;
        this.size = capacity;
        map = new HashMap();
        set = new TreeSet<Node>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                if(n1.ct == n2.ct){
                    return n1.index - n2.index;
                }
                return n1.ct - n2.ct;
            }
        });
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        len++;
        if(map.containsKey(key)){
            Node cur = map.get(key);

            set.remove(cur);
            cur.value = value;
            cur.ct++;
            cur.index = len;
            set.add(cur);
            
        }
        else{
            Node cur = new Node(key , value , len);
            if(map.size() < size){
                map.put(key , cur);
                set.add(cur);
            }
            else{
                if(set.size() == 0){
                    return;
                }
                Node dum = set.pollFirst();
                map.remove(dum.key);
                map.put(key , cur);
                set.add(cur);
            }
        }
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        if(size == 0){
            return -1;
        }
        if(!map.containsKey(key)){
            return -1;
        }
        len++;
        Node cur = map.get(key);
        set.remove(cur);
        cur.ct++;
        cur.index = len;
        set.add(cur);
        return cur.value;
    }
}


