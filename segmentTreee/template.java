public class Solution {
    /* you may need to use some attributes here */

    /*
    * @param A: An integer array
    */
    class SegmentTreeNode{
        int start , end ;
        long sum;
        SegmentTreeNode left;
        SegmentTreeNode right;
        SegmentTreeNode(int start , int end , long sum){
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = this.right = null;
        }
    }
    SegmentTreeNode root;
    public Solution(int[] nums) {
        root = build(0 , nums.length - 1 , nums);
    }
    private SegmentTreeNode build(int start , int end , int[] nums){
        if(start > end ){
            return null;
        }
        SegmentTreeNode cur = new SegmentTreeNode(start , end , nums[start]);
        if(start == end){
            return cur;
        }
        int mid = (start + end) / 2 ;
        cur.left = build(start , mid , nums);
        cur.right = build(mid + 1, end , nums);
        cur.sum = cur.left.sum + cur.right.sum;
        return cur;
    }

    /*
     * @param start: An integer
     * @param end: An integer
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
       
        return helper(root , start , end );
    }
    private long helper(SegmentTreeNode root , int start , int end){
        if(root == null){
            return 0;
        }
        if(start <= root.start && root.end <= end){
            return root.sum;
        }
        int mid = (root.start + root.end) / 2;
        int ans = 0;
        if(mid >= start){//left max = mid
            ans += helper(root.left , start , end);
        }
        if(mid + 1 <= end){ //right min = mid + 1
            ans += helper(root.right , start , end);
        }
        return ans;
    }

    /*
     * @param index: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void modify(int index, int value) {
        modifyHelper(root ,index ,value);
    }
    private void modifyHelper(SegmentTreeNode root , int index ,int value){
        // if(root == null){
        //     return;                
        // }
        if(root.start == root.end && root.start == index){
            root.sum = value;
            return;
        }
        
        int mid = (root.start + root.end) / 2;
        if(index <= mid){
            modifyHelper(root.left , index , value);
        }
        else{
            modifyHelper(root.right , index , value);
        }
        root.sum =root.left.sum + root.right.sum;
    }
}