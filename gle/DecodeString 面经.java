   /**
 * ）给一个命令 ls abc_{x, y}_c{d, e}.txt，这个命令会执行ls abc_x_cd.txt， ls abc_x_ce.txt, ls
 * abc_y_cd.txt, ls abc_y_ce.txt， implement一个method将一个String （比如abc_{x, y}_c{d,
 * e}.txt）解析成a list of Strings （比如abc_x_cd.txt， abc_x_ce.txt, abc_y_cd.txt,
 * abc_y_ce.txt)。{}可以有嵌套。
 *
 * @author Wei Wang
 */
   
    private List<String> decode(String s) {
        List<String> res = new ArrayList();
        int level = 0;
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(c == '{'){
                if(level == 0){
                    if(res.isEmpty()) res.add(sb.toString());
                    else{
                        for(int i = 0 ; i < res.size() ; i++) res.set(i, res.get(i) + sb.toString());
                    }
                    sb.setLength(0);
                }
                else sb.append(c);
                level++;
            } 
            else if(c == '}'){
                level--;
                if(level == 0){
                    List<String> list = partition(sb.toString());
                    if(res.isEmpty())res.addAll(list);
                    else{
                        List<List<String>> buffer = new ArrayList();
                        for(String origin : res){
                            for(String next : list){
                                List<String> chunkList = decode(next);
                                List<String> tmp = new ArrayList();
                                for(String tmpS : chunkList){
                                    tmp.add(origin + tmpS);
                                }
                                buffer.add(tmp);
                            }
                        }
                        res.clear();
                        for(List<String> x : buffer) res.addAll(x);
                    }
                    
                    sb.setLength(0);
                }
                else sb.append(c);
            }
            else sb.append(c);
        }
        if(sb.length()!= 0){
            if(res.isEmpty()) res.add(sb.toString());
            else{
                for(int i = 0 ; i < res.size() ; i++) res.set(i, res.get(i) + sb.toString());
            }
        }
        return res;
    }
    //x ,y{x ,y} -> x , y{x,y};
    private List<String> partition(String s) {
        List<String> res = new ArrayList();
        int level = 0 ;
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(c == '{'){
                level++;
            }
            if(c == '}'){
                level--;
            }
            if(level == 0 && c ==','){
                res.add(sb.toString());
                sb.setLength(0);
            }
            else sb.append(c);
        }
        if(sb.length()!= 0) res.add(sb.toString());
        return res;
    }


