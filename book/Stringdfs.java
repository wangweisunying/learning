// 给一个string 12345，返回 1.2.3.45, 1.2.34.5, 1.23.4.5, 12.3.4.5.

public List<String> doSth(String s){
    List<String> list = new ArrayList();
    char[] arr = s.toCharArray();
    for(int i = 1 ; i < arr.length ; i++){
        String tmp = "";
        for(int j = 0 ; j < arr.length ; j ++){
            if(i == j){
               tmp = tmp.substring(0 ,tmp.length() - 1); 
            }
            tmp += arr[j] + ".";
        }
        list.add(tmp.substring(0 ,tmp.length() - 1));
    }
    return list;
}


public List<String> doSth(String s){
    List<String> list = new ArrayList();
    char[] arr = s.toCharArray();
    dfs(list , arr ,"", 0);
    return list;
}



// 给出一个数组，元素是eventLog类型的，
// ［［1，start］，［2，start］，［1，message］，［3，start］，［2，end］，［3，end］，［1，end］］。求活动的客服的最大值。活动的客服指的是有了start log还没有结束log的客服。


class EventLog{
    int id;
    String status;
    EventLog(int id , String status){
        this.id = id;
        this.status = status;
    }
}
public int maxId(EventLog[] data){
    TreeSet<Integer> set = new TreeSet();
    for(EventLog log : data){
        if(log.status == "start"){
            set.add(log.id);
        }
        if(log.status == "end"){
            set.remove(log.id);
        }
    }
    return set.last();
    
}