    //give a list of axis find the max rectangle area 
    
    //use a hashset<String> to record the point find any two and find other 2 whether in this rectangel
    public int maxRectangle(List<int[]> list) {
        if (list == null || list.size() < 4) {
            return 0;
        }

        int max = 0;
        HashSet<String> set = new HashSet();
        for (int[] axis : list) {
            set.add(axis[0] + "_" + axis[1]);
        }
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i)[0] == list.get(j)[0] || list.get(i)[1] == list.get(j)[1]) {
                    continue;
                }
                String axis1 = list.get(i)[0] + "_" + list.get(j)[1];
                String axis2 = list.get(j)[0] + "_" + list.get(i)[1];
                if (set.contains(axis1) && set.contains(axis2)) {
                    max = Math.max(max, Math.abs((list.get(i)[0] - list.get(j)[0]) * (list.get(i)[1] - list.get(j)[1])));
                }
            }
        }
        System.out.println(max);
        return max;
    }


    //follow up if the rectangle dont parallal to the axis 
    // one approch bare dfs find 4 points from list and judge whether they are rectangel
    // same idea this time use 3 points from list and judge 
    // 1.whether it is 90 degree 
    // 2.other point in therset 利用中点求出第4条边！

    
        public int maxRectangle(List<int[]> list) {
        if (list == null || list.size() < 4) {
            return 0;
        }

        int max = 0;
        HashSet<String> set = new HashSet();
        for (int[] axis : list) {
            set.add(axis[0] + "_" + axis[1]);
        }
        for (int i = 0; i < list.size() - 2; i++) {
            for (int j = i + 1; j < list.size() - 1; j++) {
                for(int k = j + 1 ; k < list.size() ; k++){
                    int[] axis1 = list.get(i);
                    int[] axis2 = list.get(j);
                    int[] axis3 = list.get(k);
                    //judge is 90 degree;
                    int a = (axis1[0] - axis2[0]) * (axis1[0] - axis2[0])  +  (axis1[1] - axis2[1]) * (axis1[1] - axis2[1]);
                    int b = (axis3[0] - axis2[0]) * (axis3[0] - axis2[0])  +  (axis3[1] - axis2[1]) * (axis3[1] - axis2[1]);
                    int c = (axis1[0] - axis3[0]) * (axis1[0] - axis3[0])  +  (axis1[1] - axis3[1]) * (axis1[1] - axis3[1]);
                    int max = Math.max(a , Math.max(b , c));
                    int[] len = {a , b , c};
                    Arrays.sort(len);
                    if(len[0] + len[1] != len[2]){
                        continue;
                    }
                    int[] s1 , s2 , s3;
                    int area ;
                    if(max == a){
                        s1 = axis1;
                        s2 = axis2;
                        s3 = axis3;
                        area = Math.sqrt(b) * Math.sqrt(c);
                    }
                    else if(max == b){
                        s1 = axis2;
                        s2 = axis3;
                        s3 = axis1;
                        area = Math.sqrt(a) * Math.sqrt(c);
                    }
                    else{
                        s1 = axis1;
                        s2 = axis3;
                        s3 = axis2; 
                        area = Math.sqrt(b) * Math.sqrt(a);
                    }
                    
                    int[] mid = {(s1[0]+ s2[0]) / 2, (s1[1] + s2[1]) / 2};
                    String fourth = (2 * mid[0] - s3[0])  + "_" + (2 * mid[1] - s3[1]);
                    if(set.contains(fourth)){
                        max = Math.max(max , area );
                    }
                }
            }
        }
        System.out.println(max);
        return max;
    }