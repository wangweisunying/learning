// 658. Find K Closest Elements
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order.
//  If there is a tie, the smaller elements are always preferred.

// Example 1:
// Input: [1,2,3,4,5], k=4, x=3
// Output: [1,2,3,4]
// Example 2:
// Input: [1,2,3,4,5], k=4, x=-1
// Output: [1,2,3,4]
// Note:
// The value k is positive and will always be smaller than the length of the sorted array.
// Length of the given array is positive and will not exceed 104
// Absolute value of elements in the array and x will not exceed 104
// UPDATE (2017/9/19):
// The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.


//BS + LINKEDlIST!!
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> res = new LinkedList();
        if(arr == null || arr.length == 0) return res;
        int s = 0 , e = arr.length;
        while(s + 1 < e){
            int mid = (e - s) / 2 + s; 
            if(arr[mid] < x){
                s = mid;
            }
            else{
                e = mid;
            }
        }
        
        while(res.size() < k){
            while(res.size() < k && s >=0 && e < arr.length){
                if(Math.abs(arr[s] - x) <= Math.abs(arr[e] - x)) res.addFirst(arr[s--]);
                else res.addLast(arr[e++]);
            }
            while(res.size() < k && s >=0 ){
                res.addFirst(arr[s--]);
            }
            while(res.size() < k &&  e < arr.length ){
                res.addLast(arr[e++]);
            }
        }
     
        return res;
        
    }
}