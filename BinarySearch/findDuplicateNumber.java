// Find the Duplicate Number
// Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. 
// Assume that there is only one duplicate number, find the duplicate one.

// Example
// Given nums = [5,5,4,3,2,1] return 5
// Given nums = [5,4,4,3,2,1] return 4

// Notice
// You must not modify the array (assume the array is read only).
// You must use only constant, O(1) extra space.
// Your runtime complexity should be less than O(n^2).
// There is only one duplicate number in the array, but it could be repeated more than once.




// 基于值的二分法。 count(target) 是递增的！
// 这个题比较好的理解方法是画一个坐标轴：

// x轴是 0, 1, 2, ... n。
// y轴是对应的 <=x 的数的个数，比如 <=0 的数的个数是0，就在（0,0）这个坐标画一个点。<=n 的数的个数是 n+1 个，就在 (n,n+1)画一个点。
// 把所有的点连接起来之后，是一个类似下图的折线：

// 图片

// 我们可以知道这个折线图的有如下的一些属性：

// 大部分时候，我们会沿着斜率为 1 的那条虚线前进
// 如果出现了一些空缺的数，就会有横向的折线
// 一旦出现了重复的数，就会出现一段斜率超过 1 的折线
// 斜率超过 1 的折线只会出现一次
// 试想一下，对比 y=x 这条虚线，当折线冒过了这条虚线出现在这条虚线的上方的时候，一定是遇到了一个重复的数。
// 一旦越过了这条虚线以后，就再也不会掉到虚线的下方或者和虚线重叠。
// 因为折线最终会停在 (n,n+1) 这个位置，如果要从 y=x 这条虚线或者这条虚线的下方到达 (n,n+1) 这个位置，
// 一定需要一个斜率 > 1的折线段，而这个与题目所说的重复的数只有一个就是矛盾的。因此可以证明，斜率超过1 的折线只会出现1次，
// 且会将折线整体带上 y=x 这条虚线的上方。因此第一个在 y=x 上方的 x 点，就是我们要找的重复的数。

// 时间复杂度是 O(nlogn)O(nlogn)
public class Solution {
    /**
     * @param nums: an array containing n + 1 integers which is between 1 and n
     * @return: the duplicate one
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        int s = 1;
        int e = n;
        while(s + 1 < e){
            int mid = (s + e) / 2;
            if(count(nums , mid) <= mid){ //此时 还没到 重复，往上找
                s = mid;
            }
            else{//此时 到 重复，往下找
                s = mid;
                e = mid;
            }
        }
        if(count(nums , s) <= s){
            return e;
        }
        return s;
        
    }
    private int count(int[] nums ,int mid){
        int res = 0;
        for(int i : nums){
            if(i <= mid){
                res++;
            }
        }
        return res;
    }
}
