// // 1. 给你一个String （I am Jack and my father is Jimmy. I like wearing Jack and Jone's. ）， 
// 一个exclude list， 让你给出出现频率最高或者并列高的词(不Case sensitive, Jack和jack算一个词，
// 都出现的话等于算jack出现两次). (而不是大家之前说的给一个k，然后求出出现频率最高的k个）,
//  比如Jack和Jone都是频率最高且出现三次，那么return [Jack, Jone]。这里有个很tricky的地方就是Jone's是两个词，
//  Jone和s。 这个题很崩溃，在ide运行都是没错的，一上机就是nullpointer，最后只能含着泪提交了，最后出错的代码我贴出来，
//  大家可以参考一下。我是把string切好然后放到hashmap里，一边加一边去除在exclude的词，然后用一个max来记录最大的频率，最后 
//   for (Map.Entry<String, Integer> entry : map.entrySet()) {
// //                         if (entry.getValue() == max). more info on 1point3acres
// //                                 ans.add(entry.getKey());
// //                 } 来获得所有在这个map里有最大频率的词加到result list 里。. 
// // 给了一个string，和一个要排除的list，返回频率最高的词的list，题目不难，用Map就能做，但是一定要看仔细题目。 
// // 这题有两个版本，大小写敏感和不敏感。如果你的test case 23只能过22个的话，仔细看一下题干, 看看有没有一句话说：除了英文字母以外其他的全部算作空格，
// 所以split string的时候记得把数字也去掉。
// // most frequency words
// //    - 把text 都转小写， 注意记得要把不是字母的替换掉
// //    - 根据空格split 字
// //    - 数一遍，记录每个字的次数
// //    - 找到最大的都放进vector
// // 注意输入为0的edge case


// 2. 就是给你一个log file， 让你根据每一行的第二个string来进行排序，然后只排含字母的，
// （如果含有数字的话全都放在bottom，顺序按照原顺序不变）。这道题我是自己写了一个subclass，两个attribute，
//  id就是第一个substring， content就是第二个以及以后的sub string， 然后自己override一下自带的compareTo的方法，数字和字母比
//  ，就把字母放前，字母和字母比，就用原生的string的compareTo，数字和数字比，就原封不动。如果字母和字母相等，那就比较他们的id，
//  用原生的compareTo。最后排序我用的bubble sort，实在没时间写快排或者merge sort了。也只过了17/24的case，也不知道错在哪儿，代码也会分享
// logfile log file排序有什么特殊设定嚒？字母在前 数字在后 写一个comparator就行。分析一下后半部分是数字是字母的情况
// log file按照顺序排列，字母优先，不分大小写


// 3. 求到原点（0，0）最短距离那个.


// 4. 给定一个integer数组（无序）和2个node值，求构建bst(不可调整树）后，两个node的距离。
// Given a list of numbers, construct a BST from it and find the distance between two nodes.
// int bstDistance(int[] values, int node1, int node2)
// 第一个判断invalid的node1 and node2, 第二个判断一下node1或者node2是不是等于root，尝试一下testcase{1}，1，1，1这样。。. 留学申请论坛-一亩三分地
// 我刚开始没有考虑到node1或者node2可能是root，所以只pass了8 out 11，后来加了这个就过了。。。

// 5. 给定一个string 和K值，求包含 k个不同字符的子串集合。

// 6. 二维数组，货车送货，0不可行，1可行，9为终点，求最短。input是list<list>, bfs.
// 题目的意思是要去送货，假设是在一个二维的地图上，图上的点也是用List<List<Integer>>表示。每个坐标上的点有三种，0，1，2. 0表示可以到达， 1表示没有路可以去，2表示是要送的货的终点。
// 每次都是从{0，0}这个点出发。最后要求求出最短的路径。应该是跟利口那道bfs的题一样的。

// 7.给两个List，给一个最大值，在这两个list里各找一个组成一堆，求最接近最大值，但不大于最大值的所有pair，有点像two sum smaller。
// 先是用了把两个list排序然后用两个pointer 逼近的方法，但是有test case跑不过，最后写了个burute force 才全部跑过的。估计是有重复，只能用暴力解。