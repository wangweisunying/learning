
// The Skyline Problem
// Given N buildings in a x-axis，each building is a rectangle and can be represented by a triple (start, end, height)，where start is the start position on x-axis, end is the end position on x-axis and height is the height of the building. Buildings may overlap if you see them from far away，find the outline of them。

// An outline can be represented by a triple, (start, end, height), where start is the start position on x-axis of the outline, end is the end position on x-axis and height is the height of the outline.

// Building Outline

// Example
// Given 3 buildings：

// [
//   [1, 3, 3],
//   [2, 4, 4],
//   [5, 6, 1]
// ]
// The outlines are：

// [
//   [1, 2, 3],
//   [2, 4, 4],
//   [5, 6, 1]
// ]
// Notice
// Please merge the adjacent outlines if they have the same height and make sure different outlines cant overlap on x-axis.


public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    class Point{
        int axis , flag , height;
        Point(int axis , int flag , int height){
            this.axis = axis;
            this.flag = flag;
            this.height = height;
        } 
    }
    public List<List<Integer>> buildingOutline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList();
        List<Point> list = new ArrayList();
        if (buildings == null || buildings.length == 0) {
            return res;
        }
        for (int[] building : buildings) {
            list.add(new Point(building[0], 0, building[2]));
            list.add(new Point(building[1], 1, building[2]));
        }
        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.axis == p2.axis) {
                    return p1.flag - p2.flag;
                }
                return p1.axis - p2.axis;
            }
        });

        TreeMap<Integer , Integer> map = new TreeMap();
        int s = - 1;
        int cur = -1;
        for(Point p : list){
            //update the map first 
            if(p.flag == 0){
               if(map.containsKey(p.height)){
                    map.put( p.height , map.get(p.height) + 1);
               }    
               else{
                    map.put( p.height , 1);
               }
            }
            else{
                if(map.get(p.height) == 1){
                    map.remove(p.height);
                }else{
                    map.put(p.height , map.get(p.height) - 1);
                }
            }
            
            // doing operations
            if(map.isEmpty()){
                if(s < p.axis){
                    res.add(new ArrayList(Arrays.asList(s, p.axis, cur)));
                }
                s = -1;
                cur = -1;
            }
            else if(cur != map.lastKey()){       
               if( s < p.axis && s!= -1){
                   res.add(new ArrayList(Arrays.asList(s, p.axis, cur)));
               }
               s = p.axis;
               cur = map.lastKey();
            } 
        }
        return res;
    }
}




public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private class Pair implements Comparable {
        int index, height, status;
        public Pair(int index, int height, int status) {
            this.index = index;
            this.height = height;
            this.status = status;
        }
        public int compareTo(Object o) {
            Pair p = (Pair)o;
            if (this.index == p.index) {
                if (this.status == p.status) {
                    return this.height - p.height;
                } else {
                    return this.status - p.status;
                }
            }
            return this.index - p.index;
        }
    }

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        Pair[] pairs = new Pair[buildings.length * 2];
        for (int i = 0; i < buildings.length; ++i) {
            int[] building = buildings[i];
            pairs[i * 2] = new Pair(building[0], building[2], UP);
            pairs[i * 2 + 1] = new Pair(building[1], building[2], DOWN);
        }
        Arrays.sort(pairs);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preHeight = 0;
        int preIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.status == UP) {
                if (!heightMap.containsKey(pair.height)) {
                    heightMap.put(pair.height, 0);
                }
                heightMap.put(pair.height, heightMap.get(pair.height) + 1);
            } else {
                heightMap.put(pair.height, heightMap.get(pair.height) - 1);
                if (heightMap.get(pair.height) == 0) {
                    heightMap.remove(pair.height);
                }
            }

            int currentHeight = heightMap.size() == 0 ? 0 : heightMap.lastKey();
            if (preHeight != currentHeight) {
                if ((pair.index - preIndex) * preHeight != 0) {
                    result.add(getList(preIndex, pair.index, preHeight));
                }
                preIndex = pair.index;
                preHeight = currentHeight;
            }
        }

        return result;
    }

    private List<Integer> getList(int start, int end, int height) {
        List<Integer> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(height);
        return list;
    }
}