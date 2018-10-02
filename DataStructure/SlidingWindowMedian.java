// Sliding Window Median
// Given an array of n integer, and a moving window(size k),
//  move the window at each iteration from the start of the array, find the median of the element inside the window at each moving.
//  (If there are even numbers in the array, return the N/2-th number after sorting the element in the window. )

// Example
// For array [1,2,7,8,5], moving window size k = 3. return [2,7,7]

// At first the window is at the start of the array like this

// [ | 1,2,7 | ,8,5] , return the median 2;

// then the window move one step forward.

// [1, | 2,7,8 | ,5], return the median 7;

// then the window move one step forward again.

// [1,2, | 7,8,5 | ], return the median 7;

// Challenge
// O(nlog(n)) time


// [1045,1352,809,429,991,1285,81,1383,1406,1786,1661,1059,729,1651,108,1608]
// 8

[991,991,991,1045,1059,1059,1059,1059,1059]
Expected
[991,991,991,1285,1285,1285,1383,1383,1406]
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    
    class Node implements Comparable<Node>{
        int id , val;
        Node(int id  , int val){
            this.id = id;
            this.val = val;
        }
        public int compareTo(Node other) {
        Node a =(Node)other;
        if (this.val == a.val) {
            return this.id - a.id;
        }
        return this.val - a.val;
        }
    }

    
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        TreeSet<Node> minHeap = new TreeSet(); //before
        TreeSet<Node> maxHeap = new TreeSet(); //after
        List<Integer> list = new ArrayList();
        
        if(k == 0){
            return list;
        }
    
        int minSize = (k + 1) / 2; //min is the larger  one 
        for(int i = 0 ; i < k - 1 ; i++){
            if(minHeap.size() < minSize){
                minHeap.add(new Node(i ,nums[i]));
            }
            else{   
                maxHeap.add(new Node(i ,nums[i]));
            }
            if(minHeap.size() == minSize){
                if(maxHeap.size() > 0 && maxHeap.first().val < minHeap.last().val){
                    Node first = maxHeap.first();
                    Node last = minHeap.last();
                    maxHeap.remove(first);
                    minHeap.remove(last);
                    maxHeap.add(last);
                    minHeap.add(first);
                }
            }
        }

        for(int i = k - 1 ; i < nums.length; i++){
            if(minHeap.size() < minSize){
                minHeap.add(new Node(i ,nums[i]));
            }
            else{   
                maxHeap.add(new Node(i ,nums[i]));
            }
            if(minHeap.size() == minSize){
                if(maxHeap.size() > 0 && maxHeap.first().val < minHeap.last().val){
                    Node first = maxHeap.first();
                    Node last = minHeap.last();
                    maxHeap.remove(first);
                    minHeap.remove(last);
                    maxHeap.add(last);
                    minHeap.add(first);
                }
            }
            
            list.add(minHeap.last().val);

            Node oldNode = new Node(i - k + 1 ,nums[i - k + 1]);
            if(minHeap.contains(oldNode)){
                minHeap.remove(oldNode);
            }
            else{
                maxHeap.remove(oldNode);
            }            
        }
        return list;
        
    }
}



public class Solution {
    /**
	 * @param nums
	 *            : A list of integers.
	 * @return: The median of the element inside the window at each moving.
	 */
	public  ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
	    int n = nums.length;
        TreeSet<Node> minheap = new TreeSet<Node>();
        TreeSet<Node> maxheap = new TreeSet<Node>();
        ArrayList<Integer> result = new ArrayList<Integer> ();
        
        if (k == 0)
            return result;
        
        int half = (k+1)/2; //size一定大于0
        for(int i=0; i< k - 1; i++) {//加到倒数第二个此时还没有开始计算median
            add(minheap, maxheap, half, new Node(i, nums[i]));
        }
        for(int i=k-1; i<n; i++) {
            add(minheap, maxheap, half, new Node(i, nums[i]));
            result.add(minheap.last().val);
            remove(minheap,maxheap, new Node(i-k+1, nums[i-k+1]));
        }
        System.out.println(nums.length);
        System.out.println(result.size());
        return result;
    }
    
    void add(TreeSet<Node>minheap, TreeSet<Node> maxheap, int size, Node node) {
        
        //将答案等分加入min 和 max heap  , 并排序 ，首先加满minheap ,再加满max heap ， 可能maxheap.size + 1 = minheap ,无论何时minheap。last() 就是答案
        if (minheap.size()<size) {
            minheap.add(node);
        }
        else {
            maxheap.add(node);
        }
        if (minheap.size()==size) {
            if (maxheap.size()>0 && minheap.last().val>maxheap.first().val) {
                Node s = minheap.last();
                Node b = maxheap.first();
                minheap.remove(s);
                maxheap.remove(b);
                minheap.add(b);
                maxheap.add(s);
            }
        }
    }
    
    void remove(TreeSet<Node>minheap, TreeSet<Node> maxheap, Node node) {
        if (minheap.contains(node)) {
            minheap.remove(node);
        }
        else {
            maxheap.remove(node);
        }
    }
}

class Node implements Comparable<Node>{
    int id;
    int val;
    Node(int id, int val) {
        this.id = id;
        this.val = val;
    }
    public int compareTo(Node other) {
        Node a =(Node)other;
        if (this.val == a.val) {
            return this.id - a.id;
        }
        return this.val - a.val;
    }
}