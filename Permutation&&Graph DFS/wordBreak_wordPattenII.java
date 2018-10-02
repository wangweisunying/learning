

public List<List<String>> wordBreak(String s , int n){
        List<List<String>> res = new ArrayList();
        if(s == null || s.length() == 0){
            return res;
        }
        dfs(res , s , n , new ArrayList() , 0);
        return res;
    }
private void dfs(List<List<String>> res ,String s , int remain , List<String> cur , int index){
    System.out.println(cur);
    System.out.println(index);
    System.out.println(remain);
    if(index == s.length() && remain == 0){    
        res.add(new ArrayList(cur));
        return;
        
    }
    for(int i = index ; i < s.length() ; i ++){
        if(remain == 0 ){
            break;
        }
        cur.add(s.substring(index , i + 1));
        dfs(res,s, remain - 1 , cur , i + 1 );   
        cur.remove(cur.size() - 1);
    }   
}