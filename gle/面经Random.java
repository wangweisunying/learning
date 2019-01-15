// // 给一个长度为n的数组，所有相同的元素在数组中都是相邻的，for example 1,1,1,3,4,0,0,0,0,0,9，
// // 让你返回任意一个出现次数大于n/k的元素，k是随便一个常数。确认细节之后先提出one pass，小哥说可以让我开始写，写完让分析时间空间复杂度，
// // // 然后问如何优化。可以先确定候选的list，因为任意给定k的情况下，候选值最多为k个，之后二分确定start end position，复杂度从logN

// // 请问能再解释一下第三题的优化吗？ 给定k，答案最多有k个这个我理解。 如何确定候选list，二分法的作用能解 ...

// // 跟面试官沟通的结果是 把所有候选答案在原数组的位置存到一个list里 然后对每一个候选位置 递归计算这个值的长度 过程类似二分 但是无序的数组 
// // 所以只要确定每一次调用这个函数的时候你的mid是不是这个数就行了 
// // 其实不用这么麻烦 后来想了一下 假设位置是index 直接看他前k个后k个元素是不是和他一样就行了 不过当时没想到 面试官也没提可能也没care




// 1. 给你一个webpage和里面有的link (link是另一个webpage), 让你求出通过第一个webpage能访问到的所有webpage的size, webpage里有webpage的size信息
// Follow up: 给一个有所有node的set, 如何求root webpage

// 2。check 版本号 输入是两个string num followed by dot followed by int 看哪个更新。
// follow up：如何validate string

// 3. Implement hash map 面试官讲了一堆有的没的我还以为要写新几个的hashmap class 同时来override hashmap某些function, 然后分别调用，结果问了半天发现就是implement hashmap

// 4. 给一个byte[] read（） 让你implement byte read(int size)来read给定size的数据  让你考虑各种可能性
// follow up: 如果数据是  1 3 2 4 0 1要你decode 成 3 4 4 （要考虑数据很大的情况，不能直接copy，要使用iterator）
// follow up反过来encode


//假定一个code repository，很多class name，比如MyClass, AnotherClass, YourNewClass, 然后给一个CamelCase的object的名字，check这个object是不是valid，比如AC, AnC, AnoC等都是valid （match AnotherClass), 
// M也是valid (match MyClass) 但是AoC，YC就不valid。follow-up是如果这个repository的所有class都给定，然后会有频繁的request，应该怎么处理。

// 第一轮 实现State Machine，里面有多个Event，每个Event可以有多个状态转移，比如A->B，D->C，但在同一个Event名字E1里面每个初状态只有一个目标状态
// （不能既有A->B还有A->C）。整个系统只有一个State。要求实现 AddEvent(), SetInitiateState(), ChangeState(), ShowCurrentState()，
// 并自定义数据结构存储Event，要求每个操作时间复杂度尽可能低。

// 第二轮 面经题 给一个家谱（或者说由多个家谱组成的森林），问两个节点a和b是否有血缘关系，自定义数据结构。家谱中的每个人最多有两个parent。

// 第三轮 刷题网站 巴吴散 变种。道路的长度无限，要求输出每个车队车辆的数目。（我给了O(N2)的解，面试官表示可以了，也没继续问我FollowUp。剩下的时间我们就聊天了。
// 我回家研究后发现这题有O(N)解法）

// 第四轮 刷题网站 酒要伞 超难题猫和老鼠。猫和老鼠每一步可以停下来不走，如果和局则判猫输。

// 第五轮 博士研究讨论





// 第一题是给定Google园区地图和自行车分布位置，和你在的位置，如果找到离自己最近自行车。这个可以用DFS或者BFS搜索
// 然后就是如果是同时间给多个人找自行车该怎么做，我先想的是最简单的一个一个来，大叔举了个例子不同顺序的话可能给的不是最优解，最后我又改成多个人同时BFS的方法


// 第二也是一个白人小哥。题感觉比较难，我现在还不知应该怎么做。题目是在第一象限里面，两个function--Setvalue（x，y，value）会设置一个点的值，x，y范围是0-INT-MAX.
// getsum（x1,y1,x2,y2)计算在这个矩形内部的所有点的和。
// 问你如何实现这两个函数。我一开始想说用pre-calculated-sum-in-retangle的方法，但是这个matrix又大又稀疏。所以楼主也不知道怎么解。
// 最后硬着头皮写了个基于Set<x>和hashmap<intx,set<y,val>>结构的方法。


// 第三个是印度三哥，这个有点扯。三哥不仅迟到了，还要求远程，用视频面试。题目是ladder&snake。由于摄像头和whiteboard成一定角度，而且距离有2-3米，而且图像是左右颠倒的，
// 我也不清楚哪里三哥可以看到，很犹豫不知道是就这样说说怎么想的还是具体写代码，交流很受限。而且他还没有一个meetingroom，看他抱着笔记本一会跑到这里一会跑到那里。
// 大概过了半个小时之后直接断线了。
// 第三轮面试断线后和recruiter说了一下三哥面试的情况，recruiter说他已经和三哥联系了，三哥说不影响，断线也是在final-mintue断线的，我一看心里拔凉拔凉的。
// 断线之后，先是屏幕上显示说在重试连接，过了一两分钟没反应，我又开始摆货了一会那个视频系统看是否还有可能连上，确认不知道如何操作后，我百无聊赖的坐了一会，
// 最后才想起来应该和recruiter说一下情况。
// 面试的房间信号不好，打完email后试了好几次才最后发过去。发完email的时候距离面试结束还有6分钟。所以加起断线应离结束至少有15分钟左右。
// 我觉得以后有遇到这样情况的，应该一开始就直接拒绝通过视频面试。就算不断线，有订好的meetingroom，相信还是比不上面对面交流。


// 第四个还是白人大哥，题目非常简单，可能是我没有明白他想考察什么。题目就是很简单的在一个buffer里面填充一个矩形出来。我后来想想可能他想考函数的signiture的设计。


// 最后一个是一个华人小姐姐。出了一个word-complention，也就是使用Trie，完成insert和search+BFS/DFS。


// LC 2 ;  follow up： try to use as fewer rounds as possible （two pointer）
// like LC 490: but the maze if infinitely large, want the minimum path length; followup: there are obstacles (two-way BFS)
// like LC Meeting rooms: given 1 worker, several valid job intervals, and a new job interval to add, want to check whether it is still valid; 
// follow up: given k workers, several valid job intervals, and a new job interval to add, want to check whether it is still valid
// tic tac toe: check winner; follow up: LC 794





// 第二轮 语速比较快的美国小哥
// 题目的大意是有一个盗贼叫阿里巴巴，我们的目的就是要抓住它。
// 现在有一堆洞    1 2 3 4 5 6 7 8，阿里巴巴一开始可以在任意的洞内，每次他可以选择往两边逃窜。  题目的input是，一个integer 代表山洞的数量， 一个array（代表我们捕捉阿里巴巴的策略），array中每个index代表当天我们检查的山洞。
// 让我们return 这个策略是否能成功
// 比如 input：(3, [2, 2])   是return true的
// 因为阿里巴巴 一开始只可能在 1、2、3 三个山洞之间。 如果它在2，那么第一天就抓到了，如果他在1、3 那么第二天也会抓到。

// 小哥一开始给的input有一些问题。 不过最后也是解出来了。
// 这轮的解法让小哥很惊艳，他说一般人拿到就先找path然后bfs做了，你这个解法很好，很短也很有效率，我第一次见。 然后让我问了问题，拍了照满意的走了。
// 可能在hire - strong hire之间吧


// 第二轮种，我想到的解法是：求解每一天不被抓到的情况下可能的位置.
// 比如一共3个山洞，策略是[2,2]
// 那么为了避免第一天被抓，第一天可能的位置是[1,3]
// 从[1,3]开始走一次，只能走到[2]，也就是第二天位置可能的位置是2，
// 但是为了避免第二天被抓，第二天的可能位置变成了[]，所以这个肯定能抓到
// 整个过程是：前一天不被抓的位置==》运动一次的所有可能位置==》去掉当天会被检查的山洞
// 所以一个循环就可以搞定了



public class Alibaba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Alibaba ali = new Alibaba();
        System.out.println(ali.survive(new int[]{1,2,3,2,2}, 3));
    }

    private boolean survive(int[] arr, int n) {
        Set<Integer> poss = new HashSet();
        for (int j = 1; j <= n; j++) {
            if (arr[0] != j) {
                poss.add(j);
            }
        }
        poss = findAllPos(poss , n);
        for (int i = 1; i < arr.length; i++) {
            if (poss.contains(arr[i])) {
                poss.remove(arr[i]);
            }
            if (poss.isEmpty()) {
                return true;
            }
            poss = findAllPos(poss , n );
        }
        return false;
    }

    private Set<Integer> findAllPos(Set<Integer> poss , int n) {
        Set<Integer> res = new HashSet();
        for (int i : poss) {
            if (i + 1 <= n) {
                res.add(i + 1);
            }
            if (i - 1 >= 1) {
                res.add(i - 1);
            }
        }
        return res;
    }

}





// // 第一轮，国人小哥。给一个list of integer，问你last k integers 的均值，去吃top 10% 的数字和bottom 10% 的数字。return 一个list of integer，代表每一段的均值。
// // 相当于第一段index range是0->k， 第二段index range是 1 -> k+1....依次类推啦

// // 第二轮，外国小哥，特别好。就是普通的dfs，找到一个有向图的最短cycle。

// // 第三轮，白人小哥，表情严肃。找到两棵树的nearest common ancestor。我就想的很简单，先遍历其中一个，存下来所有ancestor，然后再遍历另外一个，看有没有common。连续三个follow up,
//  第一个是不用递归，第二个是优化每次对比是不是common ancestor的时间复杂度，第三个有没有办法不用遍历每一个节点就找到common ancestor。我不知道咋写。。。。我刷题有点渣渣。

// // 第四轮，白人不是小哥也不算大叔，表情更加严肃。猜数字的游戏。4位的数字，每次guess一个值要给出return的score，score代表着有几个digit match上了。给你一连串的guess history.
// 要求判断某一个数有没有可能是要猜的数字。

// // 午饭是吃的法餐，scrambled eggs还不错。nice！

// // 总而言之，谷歌还是好公司。感觉我是稳稳的挂了一轮的follow-up。



// 第一轮： 高频 莉蔻八五七  描述不太一样，本质就是雇工人这题
// 第二轮： 没见过，楼主后来准备其他onsite时，发现8月份有人面过这题。
// 输入是 {"manager", A, B} A 是B的manager, {"peer", B, C} B 和C 是peer 关系。
//  问{"isManager", A, C}。这题有点tricky. 楼主说用union find 做。 可是B 有两种关系， A是他的manager, C 是他的peer. 
//  用一个union find 搞不定。最后没办法只好采用多个union find, 有表示peer的，有表示manager的。 当一个peer union find 出现第一个manger 的时候
//  ，要全部peer union find 的人要copy 到新的manger union find. 时间空间都不高效。面试时间有限，最后讨论出解法已经没时间写code了。 感谢亚裔mm 高抬贵手
//  。 楼主后来回家想了想应该用图做，把manage 或peer 的信息存到edge里， 直接dfs 就行。
// 第三轮： 非常直接的dfs 题， 2Dgrid, 有的边是篱笆，不能通过，求篱笆围成的面积。 有个语法bug，亚裔mm 纠缠了一会儿，浪费了点时间，最后code掐点写完。
// 第四轮： 高频 莉蔻三九九，用union find + path compression， 中国哥哥很满意，说还没碰到过这个解法。估计给了strong hire.
// 第五轮： 放水轮。 本以为还会再有coding。没想到问thesis 和以前的工作，楼主辞职多年，加上免面试，做好了五轮coding准备，本想到来着出。
// 凭着残存的记忆讲了讲。最后问了两个很简单的coding 题。感谢印度大哥高抬贵手。










// {"manager", A, B} A 是B的manager, {"peer", B, C}



public class IsManager {

    /**
     * 输入是 {"manager", A, B} A 是B的manager, {"peer", B, C} B 和C 是peer 关系。 //
     * 问{"isManager", A, C}。这题有点tricky. 楼主说用union find 做。
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IsManager test = new IsManager();
        String[][] infor = new String[][]{{"M" , "A" , "B"} ,{"P" , "C" , "B"} , {"M" , "B" , "D"} , {"M" , "D" , "E"} ,{"M" , "E" , "F"}};
        String[][] queries = new String[][]{{"A" , "F"}};
        System.out.println(Arrays.toString(test.isManager(infor, queries)));
        
    }

    private boolean[] isManager(String[][] infor, String[][] queries) {
        Map<String, Map<String, Set<String>>> map = new HashMap();
        for (String[] info : infor) {
            if (info[0].equals("M")) {
                map.computeIfAbsent(info[1], x -> new HashMap<>()).computeIfAbsent("M", x -> new HashSet()).add(info[2]); 
                map.putIfAbsent(info[2], new HashMap());
            }
            if (info[0].equals("P")) {
                map.computeIfAbsent(info[1], x -> new HashMap<>()).computeIfAbsent("P", x -> new HashSet()).add(info[2]);
                map.computeIfAbsent(info[2], x -> new HashMap<>()).computeIfAbsent("P", x -> new HashSet()).add(info[1]);
            }
        }
        
        for(String people : map.keySet()){
            System.out.println(people + " " + map.get(people));
        }
       
        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (!map.containsKey(queries[i][0])) {
                res[i] = false;
                continue;
            }
            if (queries[i][0].equals(queries[i][1])) {
                res[i] = true;
                continue;
            }
            if (!map.get(queries[i][0]).containsKey("M")) {
                res[i] = false;
                continue;
            }
            for (String can : map.get(queries[i][0]).get("M")) {
                
                res[i] = dfs(map, can, queries[i][1], new HashSet(Arrays.asList(can)));
            }
        }
        for(String people : map.keySet()){
            System.out.println(people + " " + map.get(people));
        }
        return res;
    }

    private boolean dfs(Map<String, Map<String, Set<String>>> map, String start, String end, Set<String> visited) {
        if (!map.containsKey(start)) {
            return false;
        }
  
        if (start.equals(end)) {
            return true;
        }
        if(map.get(start).containsKey("M") && map.get(start).get("M").contains(end)) return true;
        if(map.get(start).containsKey("P") && map.get(start).get("P").contains(end)) return true;
        for (Set<String> set : map.get(start).values()) {
            for (String can : set) {
                if (visited.contains(can)) {
                    continue;
                }
                visited.add(can);
                map.get(start).computeIfAbsent("M", x -> new HashSet()).add(can);
                if (dfs(map, can, end, visited)) {
                    map.get(start).computeIfAbsent("M", x -> new HashSet()).add(end);
                    return true;
                }
            }
        }
        return false;
    }

}

