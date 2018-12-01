class Solution {
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList();
        for(int i : nums){
            list.add((double) i);
        }
        return helper(list );
    }
    private boolean helper(List<Double> list ){
        if(list.size() == 1){
            if(Math.abs(24 - list.get(0)) < 0.01){
                return true;
            }
            return false;
        }
        
        for(int i = 0 ; i < list.size() - 1 ; i++){
            for(int j = i + 1 ; j < list.size() ; j++){
                List<Double> tmp = new ArrayList();
                //一定要用index 如果出现重复的元素会报错！ 不要用for each!
                for(int k = 0 ; k < list.size() ; k++){
                    if(k != i && k != j){
                        tmp.add(list.get(k));
                    }
                }
                for(double cur : generate(list.get(i) , list.get(j))){
                    tmp.add(cur);
                    if(helper(tmp)){
                        return true;
                    }
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
        return false;
    }
    
    private List<Double> generate(double x ,double y){
        List<Double> list = new ArrayList();
        list.add(x + y);
        list.add(x - y);
        list.add(x * y);
        list.add(y - x);
        if(y != 0){
            list.add(x / y);
        }
        if(x != 0){
            list.add(y / x);
        }
        return list;
    }

}





//print the solution


class Solution {
    public void judgePoint24(int[] nums) {
        List<Double> list = new ArrayList();
        for(int i : nums){
            list.add((double) i);
        }
        helper(list);
    }

    String eq = "";
    private void helper(List<Double> list){
        if(list.size() == 1){
            if(Math.abs(24 - list.get(0)) < 0.01){
                System.out.println(equation);
            }
            return ;
        }
        
        for(int i = 0 ; i < list.size() - 1 ; i++){
            for(int j = i + 1 ; j < list.size() ; j++){
                List<Double> tmp = new ArrayList();
                //一定要用index 如果出现重复的元素会报错！ 不要用for each!
                for(int k = 0 ; k < list.size() ; k++){
                    if(k != i && k != j){
                        tmp.add(list.get(k));
                    }
                }
                List<Double> canList = generate(list.get(i) , list.get(j));
                for(int l = 0 ; l < canList.size() ; l++){
                    tmp.add(canList.get(l));
                    eq += listEq.get(l);
                    helper(tmp);
                    eq = eq.substring(0 , eq.length() - listEq.get(l).length());
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
    }
    List<String> listEq = new ArrayList(); 
    private List<Double> generate(double x ,double y){
        List<Double> list = new ArrayList();
        listEq.clear();
        list.add(x + y);
        listEq.add(x + " + " + y);
        list.add(x - y);
        listEq.add(x + " - " + y);
        list.add(x * y);
        listEq.add(x + " * " + y);
        list.add(y - x);
        listEq.add(y + " - " + x);
        if(y != 0){
            list.add(x / y);
            listEq.add(x + " / " + y);
        }
        if(x != 0){
            list.add(y / x);
            listEq.add(y + " / " + x);
        }
        return list;
    }

}