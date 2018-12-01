// 850. Rectangle Area II
// DescriptionHintsSubmissionsDiscussSolution
// We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2] , 
// where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.

// Find the total area covered by all rectangles in the plane.  Since the answer may be too large, return it modulo 10^9 + 7.



// Example 1:

// Input: [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
// Output: 6
// Explanation: As illustrated in the picture.
// Example 2:

// Input: [[0,0,1000000000,1000000000]]
// Output: 49
// Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 = (-7)^2 = 49.
// Note:

// 1 <= rectangles.length <= 200
// rectanges[i].length = 4
// 0 <= rectangles[i][j] <= 10^9
// The total area covered by all rectangles will never exceed 2^63 - 1 and thus will fit in a 64-bit signed integer.



// [[0,0,1000000000,1000000000]]
// Output:
// 0
// Expected:
// 49\




// [[0,0,2,2],[1,1,3,3]]
// Output:
// 8
// Expected:
// 7

//sweep line , 1. traverse from the top to bottom , add all the open rec and close rec to the Array ,
// ,2 .use a honzonal line get all the x Axis interval , merge the interval if they are overlapping
// 3 .delte any of the rec close when met close flag

class Solution {
    public int rectangleArea(int[][] rectangles) {
        int n = rectangles.length;
        int open = 0 , close = 1;
        int mod = 1_000_000_007;
        int[][] events = new int[2 * n][4];
        int index = 0;
        
        
        for(int i = 0 ; i < rectangles.length ; i++){
            int[] rec = rectangles[i];
            events[index++] = new int[] {open ,  rec[3] , rec[0] ,rec[2]};
            events[index++] = new int[] {close , rec[1] , rec[0] ,rec[2]};
        }
        Arrays.sort(events , (a , b) -> (b[1] - a[1]));
        
        long res = 0;
        int preY = events[0][1];
        List<int[]> xList = new ArrayList();
        for(int[] event : events){
            int curY = event[1] , x1 = event[2] , x2 = event[3]; 
            
            
            //mergeInterval in Xlist
            Collections.sort(xList ,(a , b) -> (a[0] - b[0]));
            List<int[]> mergetList = merge(xList);
            //   
            
            for(int[] pair : mergetList){
                res += ((long)( preY - curY) * (long)(pair[1] - pair[0]));
                // System.out.println((long)( preY - curY) * (long)(pair[1] - pair[0]));
            } 
            //这里不需要merge只需要求出 实际线段的长度即可
            // for (int[] xs: active) {
            //     cur = Math.max(cur, xs[0]);
            //     query += Math.max(xs[1] - cur, 0);
            //     cur = Math.max(cur, xs[1]);
            // }
            
            if(event[0] == open){
                xList.add(new int[]{x1 , x2});
                 
            }
            else{
                for(int i = 0 ; i < xList.size() ; i++){
                    if(xList.get(i)[0] == x1 && xList.get(i)[1] == x2){
                        xList.remove(i);
                        break;
                    }
                }
            }
            preY = curY;
        }
        res %= mod;
        return (int)res;
    }
    private List<int[]> merge(List<int[]> xList){
        if(xList.isEmpty() || xList.size() == 1) return xList;
        List<int[]> res = new ArrayList();
        int s = xList.get(0)[0] , e = xList.get(0)[1];
        for(int i = 1 ; i < xList.size() ; i++){
            if(xList.get(i)[0] > e){
                res.add(new int[]{s , e});
                s = xList.get(i)[0];
                e = xList.get(i)[1];
            }
            else{
                e = Math.max(e , xList.get(i)[1]);  
            } 
        }
        res.add(new int[]{s , e});
        return res;
    }
}



























class Solution {
    public int rectangleArea(int[][] rectangles) {
        int OPEN = 0, CLOSE = 1;
        int[][] events = new int[rectangles.length * 2][];
        int t = 0;
        for (int[] rec: rectangles) {
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
        }

        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> active = new ArrayList();
        int cur_y = events[0][0];
        long ans = 0;
        for (int[] event: events) {
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];

            // Calculate query
            long query = 0;
            int cur = -1;
            for (int[] xs: active) {
                cur = Math.max(cur, xs[0]);
                query += Math.max(xs[1] - cur, 0);
                cur = Math.max(cur, xs[1]);
            }

            ans += query * (y - cur_y);

            if (typ == OPEN) {
                active.add(new int[]{x1, x2});
                Collections.sort(active, (a, b) -> Integer.compare(a[0], b[0]));
            } else {
                for (int i = 0; i < active.size(); ++i)
                    if (active.get(i)[0] == x1 && active.get(i)[1] == x2) {
                        active.remove(i);
                        break;
                    }
            }

            cur_y = y;
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }
}