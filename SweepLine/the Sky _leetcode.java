//sweeping line 
//up label greatest going out frisrt , down label greatest going out last

class Solution {

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList();
        List<int[]> list = new ArrayList();
        for(int[] x : buildings){
            list.add(new int[]{x[0] , -x[2]}); // trick part up label greatest going out frisrt , down label greatest going out last
            list.add(new int[]{x[1] , x[2]});
        }
        Collections.sort(list,new Comparator<int[]>(){
            @Override 
            public int compare(int[] o1 , int[]o2){
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }    
        });
        

        Queue<Integer> que = new PriorityQueue(new Comparator(){
            @Override
            public int compare(Object o1 ,Object o2){
                return (int)o2 - (int)o1;
            }
        });
        
        que.offer(0);
        int pre = 0;
        for(int[] p : list){
            if(p[1] < 0){
                que.offer(-p[1]);
            }
            else{
                que.remove(p[1]);
            }
            int max = que.peek();
            if(max != pre){
                res.add(new int[]{p[0] , max});
                pre = max;
            }
        }
        return res;
    }
}

public List<int[]> getSkyline(int[][] buildings) {
    List<int[]> result = new ArrayList<>();
    List<int[]> height = new ArrayList<>();
    for(int[] b:buildings) {
        height.add(new int[]{b[0], -b[2]});
        //up label greatest going out frisrt , down label greatest going out last
        height.add(new int[]{b[1], b[2]});
    }
    Collections.sort(height, (a, b) -> {
            if(a[0] != b[0]) 
                return a[0] - b[0];
            return a[1] - b[1];
    });
    Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
    pq.offer(0);
    int prev = 0;
    for(int[] h:height) {
        if(h[1] < 0) {
            pq.offer(-h[1]);
        } else {
            pq.remove(h[1]);
        }
        int cur = pq.peek(); // get the max value after operation
        if(prev != cur) {
            result.add(new int[]{h[0], cur});
            prev = cur;
        }
    }
    return result;
}


class Solution {
    class Point implements Comparable<Point>{
        int axis , val;
        boolean flag;
        Point( int axis , int val , boolean flag ){
            this.axis = axis;
            this.val = val;
            this.flag = flag;
        }
       
        public int compareTo(Point p){
            if(this.axis == p.axis){
                if(this.flag == p.flag){
                    return p.val - this.val; //greater val pop up first
                }
                return this.flag ? -1 : 1; //going up first
            }
            return this.axis - p.axis;
        }

    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList();
        if(buildings == null || buildings.length == 0){
            return res;
        }
        List<Point> list = new ArrayList();
        
        for(int[] building : buildings){
            list.add(new Point(building[0] , building[2] ,true));
            list.add(new Point(building[1] , building[2] ,false));
        }
        TreeMap<Integer ,Integer> map = new TreeMap();
        Collections.sort(list);
        int start = list.get(0).axis;
        map.put(list.get(0).val , 1);
        int ct = 1;
        for(int i = 1 ; i < list.size() ; i++){
            Point p = list.get(i);
            System.out.println(p.val);
            int max = ct == 0 ? 0 : map.lastKey();
            if(p.flag){ 
                if(max < p.val ){
                    res.add(new int[]{start , max});
                    start = p.axis;
                } 
                if(map.containsKey(p.val)){
                    map.put(p.val , map.get(p.val) + 1);
                }
                else{
                    map.put(p.val , 1);
                }
                ct++;
            }
            else{
                if(max == p.val && map.get(p.val) == 1 && p.axis != start){
                    res.add(new int[]{start, max}); 
                    start = p.axis;
                }    
                if(map.get(p.val) == 1){
                    map.remove(p.val);
                }
                else{
                    map.put( p.val , map.get(p.val) - 1 );
                }
                ct--;
            }
        }
        res.add(new int[]{start , 0});
        return res;
    }
}





public List<int[]> getSkyline(int[][] buildings) {
    List<int[]> result = new ArrayList<>();
    List<int[]> height = new ArrayList<>();
    for(int[] b:buildings) {
        height.add(new int[]{b[0], -b[2]});
        //up label greatest going out frisrt , down label greatest going out last
        height.add(new int[]{b[1], b[2]});
    }
    Collections.sort(height, (a, b) -> {
            if(a[0] != b[0]) 
                return a[0] - b[0];
            return a[1] - b[1];
    });
    Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
    pq.offer(0);
    int prev = 0;
    for(int[] h:height) {
        if(h[1] < 0) {
            pq.offer(-h[1]);
        } else {
            pq.remove(h[1]);
        }
        int cur = pq.peek(); // get the max value after operation
        if(prev != cur) {
            result.add(new int[]{h[0], cur});
            prev = cur;
        }
    }
    return result;
}