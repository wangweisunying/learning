// 381. Insert Delete GetRandom O(1) - Duplicates allowed
// DescriptionHintsSubmissionsDiscussSolution
// Design a data structure that supports all following operations in average O(1) time.

// Note: Duplicate elements are allowed.
// insert(val): Inserts an item val to the collection.
// remove(val): Removes an item val from the collection if present.
// getRandom: Returns a random element from current collection of elements. 
// The probability of each element being returned is linearly related to the number of same value the collection contains.
// Example:

// // Init an empty collection.
// RandomizedCollection collection = new RandomizedCollection();

// // Inserts 1 to the collection. Returns true as the collection did not contain 1.
// collection.insert(1);

// // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
// collection.insert(1);

// // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
// collection.insert(2);

// // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
// collection.getRandom();

// // Removes 1 from the collection, returns true. Collection now contains [1,2].
// collection.remove(1);

// // getRandom should return 1 and 2 both equally likely.
// collection.getRandom();


// ["RandomizedCollection","insert","remove","insert","remove","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom"]
// [[],[0],[0],[-1],[0],[],[],[],[],[],[],[],[],[],[]]
// Stdout:
// [0]
// {0=[0]}
// false

// [-1]
// {0=[0], -1=[0]}
// true

class RandomizedCollection {

    /** Initialize your data structure here. */
    Random rand = new Random();
    List<Integer> list;
    Map<Integer , List<Integer>> map;
    public RandomizedCollection() {
        list = new ArrayList();
        map = new HashMap();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        if(map.containsKey(val)){
            map.get(val).add(list.size() - 1);
            return false;
        } 
        else{
            map.put(val , new ArrayList(Arrays.asList(list.size() - 1)));
            return true;
        }
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))return false;
        int index = map.get(val).get(0);
        int index2 = list.size();
        map.get(val).remove(0);
        int tmp = list.get(list.size() - 1);
        list.set(index , tmp);
        list.remove(list.size() - 1);
        map.get(tmp).add(index);
        map.get(tmp).remove(new Integer(index2 - 1));
        if(map.get(val).size() == 0) map.remove(val);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */




//  The problem is a simple extension of the previous problem that did not have duplicates.
//  Instead of storing a single index like in the previous problem, we simply store a collection of indices
//   for all the times that a number appears in the array.

// // Insert() and random() are quite straightforward. 
// For remove(), we take advantage of the fact that adding/removing from a HashSet is O(1)
//  average time. The logic is otherwise similar - swap the index of any one instance 
//  of the item to be removed with the item in the very last place of the array. Update the sets after doing so, and then remove the last item.

// // Thanks to @yubad2000 for the wonderful idea of using a LinkedHashSet for O(1) iteration over large items.
//  An iterator over a normal HashSet is actually O(h/n), where h is table capacity. So it is not a solution to our problem requiring O(1) time.
//   Nor does an ArrayList instead of a HashSet work (I wasted some time on that for a while...).

public class RandomizedCollection {

    ArrayList<Integer> result;
    HashMap<Integer, LinkedHashSet<Integer>> map;
    
    public RandomizedCollection() {
        result = new ArrayList<Integer>();
        map = new HashMap<Integer, LinkedHashSet<Integer>>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        // Add item to map if it doesn't already exist.
        boolean alreadyExists = map.containsKey(val);
        if(!alreadyExists) {
            map.put(val, new LinkedHashSet<Integer>());
        }
        map.get(val).add(result.size());
        result.add(val);
        return !alreadyExists;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) {
            return false;
        }
        // Get arbitary index of the ArrayList that contains val
        LinkedHashSet<Integer> valSet = map.get(val);
        int indexToReplace = valSet.iterator().next();
        
        // Obtain the set of the number in the last place of the ArrayList
        int numAtLastPlace = result.get(result.size() - 1);
        LinkedHashSet<Integer> replaceWith = map.get(numAtLastPlace);
        
        // Replace val at arbitary index with very last number
        result.set(indexToReplace, numAtLastPlace);
        
        // Remove appropriate index
        valSet.remove(indexToReplace);
        
        // Don't change set if we were replacing the removed item with the same number
        if(indexToReplace != result.size() - 1) {
            replaceWith.remove(result.size() - 1);
            replaceWith.add(indexToReplace);
        }
        result.remove(result.size() - 1);
        
        // Remove map entry if set is now empty, then return
        if(valSet.isEmpty()) {
            map.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        // Get linearly random item
        return result.get((int)(Math.random() * result.size()));
    }
}