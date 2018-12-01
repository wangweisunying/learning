    int findRectangleCt(List<int[]> list) {
        HashMap<Integer, Set<Integer>> map = new HashMap();
        for (int[] axis : list) {
            map.computeIfAbsent(axis[1], x -> new HashSet<>()).add(axis[0]);
        }
        List<Integer> yList = new ArrayList(map.keySet());
        int res = 0;
        for (int i = 0; i < yList.size() - 1; i++) {
            for (int j = i + 1; j < yList.size(); j++) {
                int y1 = yList.get(i);
                int y2 = yList.get(j);
                int ct = 0;
                for (int x1 : map.get(y1)) {
                    if (map.get(y2).contains(x1)) {
                        ct++;
                    }
                }
                if (ct > 0) { // 利用组合方法求个数 找到所有平行且相等的边 从中选2条 C n 2;
                    res += ct * (ct - 1) / 2;
                }
            }
        }
        return res;
    }



    int findMaxRectangleArea(List<int[]> list) {
        HashMap<Integer, Set<Integer>> map = new HashMap();
        for (int[] axis : list) {
            map.computeIfAbsent(axis[1], x -> new HashSet<>()).add(axis[0]);
        }
        List<Integer> yList = new ArrayList(map.keySet());
        int res = 0;
        for (int i = 0; i < yList.size() - 1; i++) {
            for (int j = i + 1; j < yList.size(); j++) {
                int y1 = yList.get(i);
                int y2 = yList.get(j);
                int maxX= -1;
                int minX = Integer.MAX_VALUE;
                for (int x1 : map.get(y1)) {
                    if (map.get(y2).contains(x1)) {
                        minX = Math.min(minX, x1);
                        maxX = Math.max(maxX, x1);
                    }
                }
                if (minX != -1 && maxX != Integer.MAX_VALUE && minX != maxX) {
                    res = Math.max( res , Math.abs(y1 - y2) * Math.abs(maxX - minX));
                }
            }
        }
        return res;
    }