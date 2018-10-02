// Insert Delete GetRandom O(1)
// Design a data structure that supports all following operations in average O(1) time.

// insert(val): Inserts an item val to the set if not already present.
// remove(val): Removes an item val from the set if present.
// getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
// Example
// // Init an empty set.
// RandomizedSet randomSet = new RandomizedSet();

// // Inserts 1 to the set. Returns true as 1 was inserted successfully.
// randomSet.insert(1);

// // Returns false as 2 does not exist in the set.
// randomSet.remove(2);

// // Inserts 2 to the set, returns true. Set now contains [1,2].
// randomSet.insert(2);

// // getRandom should return either 1 or 2 randomly.
// randomSet.getRandom();

// // Removes 1 from the set, returns true. Set now contains [2].
// randomSet.remove(1);

// // 2 was already in the set, so return false.
// randomSet.insert(2);

// // Since 2 is the only number in the set, getRandom always return 2.
// randomSet.getRandom();
public class RandomizedSet {
    HashSet<Integer> set;
    ArrayList<Integer> list;
    public RandomizedSet() {
        set = new HashSet();
        list = new ArrayList();
        // do intialization if necessary
    }

    /*
     * @param val: a value to the set
     * @return: true if the set did not already contain the specified element or false
     */
    public boolean insert(int val) {
        boolean res = set.contains(val);
        if(!res){
            set.add(val);
            list.add(val);
        }
        return !res;
    }

    /*
     * @param val: a value from the set
     * @return: true if the set contained the specified element or false
     */
    public boolean remove(int val) {
        boolean res = set.contains(val);
        if(res){
            set.remove(val);
            list.remove(list.indexOf(val));
        }
        return res;
    }

    /*
     * @return: Get a random element from the set
     */
    public int getRandom() {
        int index = (int)Math.floor(Math.random() * list.size());
        return list.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param = obj.insert(val);
 * boolean param = obj.remove(val);
 * int param = obj.getRandom();
 */



















public class RandomizedSet {
    HashSet<Integer> set;
    ArrayList<Integer> list;
    public RandomizedSet() {
        set = new HashSet();
        list = new ArrayList();        
    }

    /*
     * @param val: a value to the set
     * @return: true if the set did not already contain the specified element or false
     */
    public boolean insert(int val) {
        if(set.contains(val)){
            return false;
        }
        else{
            list.add(val);
            set.add(val);
            return true;
        }
    }

    /*
     * @param val: a value from the set
     * @return: true if the set contained the specified element or false
     */
    public boolean remove(int val) {
        if(set.contains(val)){
            set.remove(val);
            list.remove((Object)val);
            return true;
        }
        else{
            return false;
        }
    }

    /*
     * @return: Get a random element from the set
     */
    public int getRandom() {
        return list.get((int)Math.floor(Math.random() * list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param = obj.insert(val);
 * boolean param = obj.remove(val);
 * int param = obj.getRandom();
 */