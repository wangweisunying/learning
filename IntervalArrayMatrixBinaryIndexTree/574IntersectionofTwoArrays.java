// IntersectionofTwoArrays
// Given two arrays, write a function to compute their intersection.

// Example
// Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

// Challenge
// Can you implement it in three different algorithms?

public class Solution {
    /**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] shorter = nums1.length >= nums2.length ? nums2 : nums1;
        int[] longer = nums1.length >= nums2.length ? nums1 : nums2;

        HashSet<Integer> set = new HashSet();
        for(int i : shorter){
            set.add(i);
        }
        HashSet<Integer> res = new HashSet();
        for(int i : longer){
            if(set.contains(i)){
                res.add(i);
            }
        }
        int[] x = new int[res.size()];
        int index = 0;
        for(int i : res){
            x[index ++] = i;
        }
        return x;
    }
}




































public class Solution {
    
    /*
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList();
        Arrays.sort(nums1);
        Arrays.sort(nums2); 
        int i = 0 , j = 0;
        while(i < nums1.length && j < nums2.length){
            if(i > 0 && nums1[i] == nums1[i - 1]){
                i++;
                continue;
            }
            if(j > 0 && nums2[j] == nums2[j - 1]){
                j++;
                continue;
            }
            if(nums1[i] == nums2[j]){
                list.add(nums1[i]);
                i++;
                j++;
                continue;
            }
            if(nums1[i] < nums2[j]){
                i++;
            }
            if(nums1[i] > nums2[j]){
                j++;
            }
        }
        int[] res = new int[list.size()];
        for(int  k = 0 ; k < res.length ;k ++){
            res[k] = list.get(k);
        }
        return res;
    }
}