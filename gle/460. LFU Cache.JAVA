// 460. LFU Cache
// DescriptionHintsSubmissionsDiscussSolution
// Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

// get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
// put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item.
//  For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

// Follow up:
// Could you do both operations in O(1) time complexity?

// Example:

// LFUCache cache = new LFUCache( 2 /* capacity */ );

// cache.put(1, 1);
// cache.put(2, 2);
// cache.get(1);       // returns 1
// cache.put(3, 3);    // evicts key 2
// cache.get(2);       // returns -1 (not found)
// cache.get(3);       // returns 3.
// cache.put(4, 4);    // evicts key 1.
// cache.get(1);       // returns -1 (not found)
// cache.get(3);       // returns 3
// cache.get(4);       // returns 4

// ["LFUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
// [[3],
// [1,1],
// [2,2],
// [3,3],
// [4,4],
// [4],4
// [3],3
// [2],2
// [1],-1
// [5,5],
// [1],-1
// [2],2
// [3],3
// [4],-1
// [5]]5
// Output:
// [null,null,null,null,null,4,3,2,-1,null,-1,2,-1,4,5]
// Expected:
// [null,null,null,null,null,4,3,2,-1,null,-1,2,3,-1,5]

public class LFUCache {
    HashMap<Integer, Integer> vals;
    HashMap<Integer, Integer> counts;
    HashMap<Integer, LinkedHashSet<Integer>> lists;
    int cap;
    int min = -1;
    public LFUCache(int capacity) {
        cap = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }
    
    public int get(int key) {
        if(!vals.containsKey(key))
            return -1;
        int count = counts.get(key);
        counts.put(key, count+1);
        lists.get(count).remove(key);
        if(count==min && lists.get(count).size()==0)
            min++;
        if(!lists.containsKey(count+1))
            lists.put(count+1, new LinkedHashSet<>());
        lists.get(count+1).add(key);
        return vals.get(key);
    }
    
    public void set(int key, int value) {
        if(cap<=0)
            return;
        if(vals.containsKey(key)) {
            vals.put(key, value);
            get(key);
            return;
        } 
        if(vals.size() >= cap) {
            int evit = lists.get(min).iterator().next();
            lists.get(min).remove(evit);
            vals.remove(evit);
        }
        vals.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}







class LFUCache {
    Queue<int[]> maxHeap;
    HashMap<Integer , int[]> map;
    int size;
    int id = 0;
    public LFUCache(int capacity) {
        maxHeap = new PriorityQueue<>((a , b) -> (a[2] == b[2] ? a[3] - b[3] : a[2] - b[2] ));
        map = new HashMap();
        size = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1; 
        }
        map.get(key)[2]++;
        map.get(key)[3] = ++id;
        maxHeap.remove(map.get(key));
        maxHeap.offer(map.get(key));
        System.out.println(maxHeap.peek()[0]);
        return map.get(key)[1];
    }
    public void put(int key, int value) {
        if(size == 0){
            return;
        }
        if(map.containsKey(key)){
            map.get(key)[1] = value;            
            map.get(key)[2]++;
            map.get(key)[3] = ++id;
            maxHeap.remove(map.get(key));
            maxHeap.offer(map.get(key));
            return;
        }
   
        int[] cur = {key , value , 1 , ++id};
        if(map.size() == size){
            map.remove(maxHeap.poll()[0]);
        }
        map.put(key , cur);
        maxHeap.offer(cur);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */