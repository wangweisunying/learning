// Nuts & Bolts Problem
// Given a set of n nuts of different sizes and n bolts of different sizes. 
// There is a one-one mapping between nuts and bolts. 
// Comparison of a nut to another nut or a bolt to another bolt is not allowed. It means nut can only be compared with bolt and bolt can only be compared with nut to see which one is bigger/smaller.

// We will give you a compare function to compare nut with bolt.

// Example
// Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].

// Your code should find the matching bolts and nuts.

// one of the possible return:

// nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].

// we will tell you the match compare function. If we give you another compare function.

// the possible return is the following:

// nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].

// So you must use the compare function that we give to do the sorting.

// The order of the nuts or bolts does not matter. You just need to find the matching bolt for each nut.
// 根据给的接口compare function，无法进行数组的内部排序，因此可以考虑用nut来对bolt进行partition。
// 如：先取nut 'bc'，对bolt进行排序之后，找到相应的'BC', 并且使得BC 左边都是小的bolt，右边都是大的bolt；
// 然后要用bolt对nut进行同样的操作，通过找到的BC来对nut排序。

// 之后就可以再进一步对左边和右边分别排序。

/**
 * public class NBCompare {
 *     public int cmp(String a, String b);
 * }
 * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
 * if "a" is bigger than "b", it will return 1, else if they are equal,
 * it will return 0, else if "a" is smaller than "b", it will return -1.
 * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
*/
/*

快速排序的核心即为定基准，划分区间。由于这里只能以对方的元素作为基准，故一趟划分区间后仅能得到某一方基准元素排序后的位置，
那通过引入 O(n)O(n)O(n) 的额外空间来对已处理的基准元素进行标记如何呢？这种方法实现起来较为困难
，因为只能对一方的元素划分区间，而对方的元素无法划分区间进而导致递归无法正常进行。

。核心在于：首先使用 nuts 中的某一个元素作为基准对 bolts 进行 partition 操作，随后将 bolts 中得到的基准元素作为基准对 nuts 进行 partition 操作。



*/
// This algorithm first performs a partition by picking last element of bolts array as pivot, 
// rearrange the array of nuts and returns the partition index ‘i’ such that all nuts smaller than nuts[i] are on the left side and all nuts greater than nuts[i] are on the right side. 
// Next using the nuts[i] we can partition the array of bolts. Partitioning operations can easily be implemented in O(n). This operation also makes nuts and bolts array nicely partitioned. 
// Now we apply this partitioning recursively on the left and right sub-array of nuts and bolts.

// As we apply partitioning on nuts and bolts both so the total time complexity will be Θ(2*nlogn) = Θ(nlogn) on average.

// Here for the sake of simplicity we have chosen last element always as pivot. We can do randomized quick sort too.



public class Solution {
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        // write your code here
            if (nuts == null || bolts == null) return;
        if (nuts.length != bolts.length) return;
        qsort(nuts, bolts, compare, 0, nuts.length - 1);
    }

    private void qsort(String[] nuts, String[] bolts, NBComparator compare, 
                       int l, int u) {
        if (l >= u) return;
        // find the partition index for nuts with bolts[l]
        int part_inx = partition(nuts, bolts[l], compare, l, u);
        // partition bolts with nuts[part_inx]
        partition(bolts, nuts[part_inx], compare, l, u);
        // qsort recursively
        qsort(nuts, bolts, compare, l, part_inx - 1);
        qsort(nuts, bolts, compare, part_inx + 1, u);
    }

    int partition(String[] data, String pivot, NBComparator compare, int start, int end){
        int lo = start, hi = end;
        for (int i = start; i <= end; i++){
            if (comp(compare, data[i], pivot) == 0){
                swap(data, lo, i);
                lo++;
                break;
            }
        }
        String now = data[lo];
        while (lo < hi){
            while (lo < hi && comp(compare, data[hi], pivot) >= 0){
                hi--;
            }
            while (lo < hi && comp(compare, data[lo], pivot) <= 0){
                lo++;
            }
            swap(data , lo , hi);
        }
        data[lo] = now;
        return lo;
    }

    int comp(NBComparator compare, String a, String b){
        int ans = compare.cmp(a, b);
        return ans == 2 ? -compare.cmp(b, a) : ans;
    }

    void swap(String[] data, int lo, int hi){
        String tmp = data[lo];
        data[lo] = data[hi];
        data[hi] = tmp;
    }

};


public class Solution {
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        // write your code here
            if (nuts == null || bolts == null) return;
        if (nuts.length != bolts.length) return;
        qsort(nuts, bolts, compare, 0, nuts.length - 1);
    }

    private void qsort(String[] nuts, String[] bolts, NBComparator compare, 
                       int l, int u) {
        if (l >= u) return;
        // find the partition index for nuts with bolts[l]
        int part_inx = partition(nuts, bolts[l], compare, l, u);
        // partition bolts with nuts[part_inx]
        partition(bolts, nuts[part_inx], compare, l, u);
        // qsort recursively
        qsort(nuts, bolts, compare, l, part_inx - 1);
        qsort(nuts, bolts, compare, part_inx + 1, u);
    }

    int partition(String[] data, String pivot, NBComparator compare, int start, int end){
        int lo = start, hi = end;
        int now = lo;
        for (int i = start; i <= end; i++){
            if (comp(compare, data[i], pivot) == 0){
                // swap(data, lo, i);                        
                now = data[i];
                lo++;
                break;
            }
        }
        
        while (lo < hi){
            while (lo < hi && comp(compare, data[hi], pivot) >= 0){
                hi--;
            }
            while (lo < hi && comp(compare, data[lo], pivot) <= 0){
                lo++;
            }
            swap(data , lo , hi);
        }
        return lo;
    }

    int comp(NBComparator compare, String a, String b){
        int ans = compare.cmp(a, b);
        return ans == 2 ? -compare.cmp(b, a) : ans;
    }

    void swap(String[] data, int lo, int hi){
        String tmp = data[lo];
        data[lo] = data[hi];
        data[hi] = tmp;
    }
};