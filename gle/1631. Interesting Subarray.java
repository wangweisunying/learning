
// 1631. Interesting Subarray
// If there are no more than two values in a subarray, the subarray is interesting. Given an array a, find the longest interesting subarray. Output the maximum length.

// Example
// a = [1,2,1,3]
// return 3
// a=[1,1,1,1]
// return 4
// Notice
// the length of a is no more than 1e5
// 0 <= a[i] <= 1e3

public class Solution {
    /**
     * @param a: the array a
     * @return: return the maximum length
     */
    public int maxLen(int[] a) {
        int res = 0;
        HashMap<Integer ,Integer> map = new HashMap();
        int i = 0;
        int j = 0;
        while(j < a.length){
            if(map.containsKey(a[j])){
                map.put(a[j] , map.get(a[j]) + 1);
            }
            else{
                map.put(a[j] , 1);
            }
            while(map.size() > 2){
                if(map.get(a[i]) == 1){
                    map.remove(a[i]);
                }
                else{
                    map.put(a[i],map.get(a[i]) - 1);
                }
                i++;
            }
            res = Math.max(res , j - i + 1);
            j++;
        }
        return res;
    }
}