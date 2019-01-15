// 1. 二叉搜索树中离目标最近的若干个数字，还有一个想不起来了
// 2. 设计单机键值存储，纠结了半天加锁分布式没时间答了
// 3. 管理者聊天
// 4. 设计单机延迟任务执行器，纠结了半天加锁分布式没时间答了. 1point3acres
// 5. 设计内存数据结构存键值，每个键有过期时间。难点在于怎么用细粒度锁，到最后也没给出满意的结果，不知道面试官到底想要什么，另一个面试官看我做不出来非常开心。

// 6. 岛屿数量，求树高-baidu 1point3acres
// 所有写码轮都是原题还是比较简单的。所有设计都炸了，怎么上锁这种问题一钻进去就没时间干别的了，和想象中的系统设计面试完全不同。

// 1.设计分布式key val 存储，聊了会，感觉面试官想要的是分布式文件系统，反正顺着面试官的意思搞了。准备了好几次follow up，面试官都没有问。一直在问一些基本的序列化问题。好无聊
// 2. 设计灵隐， 主要在朋友关系计算。
// 3. 设计dealy task scheduler。不然用数据库，随便扯了会，当时准备了好几个follow up，但面试官都没有问。。。有点小郁闷
// 4. 算法。。。忘了。。。
// 5. 经理面
// 6. 最后感觉体力不太行了。算法碰到一个国人大哥，人很好，都是些基本题。谢谢大哥。感觉是当天面的最好的一轮了。

// azxcazxc 发表于2018-11-11 08:13
// delay task scheduler明显考多线程间交互，和数据库有啥关系。。

// 看具体场景吧。有的task scheduler 希望有永久存储，queue的服务器挂了重启也不会丢失数据，多线程处理也方便点。
// delay queue的话一般delay一般都比较短时间就无所谓了。那个面试官刚开始和我说的场景比较像一般task scheduler，比如有时要delay一两天，
// 所以第一反应就是加数据库存储了。

// 两轮coding题都是原题。
// 第一轮：-baidu 1point3acres
//     1. 给了2个sorted array，问并集合集。
//     2. 数岛
// 第二轮：-baidu 1point3acres
//     1. 幺儿起. From 1point 3acres bbs
//     2. 三八零
// 系统设计：大家都知道的top k exception，就不赘述了。
// 其他轮就是讨论项目和一些bq问题。

// 中午跟着印度小姐姐去了印度餐厅吃午饭，下午面试官很惊讶的告诉我那是最难吃的食堂，哭&#128546;。。

// 第一轮，国人大哥，我迟到了，说明了原因。大哥也挺理解的。说没关系，这轮是experience，project deep dive。要其他轮就够呛了。然后各种聊天，之前做的项目什么的，画图说明。
// 然后大哥出了个题，关于linkedin data infrasture的一个问题。问我怎么设计。

// 第二轮，代码，国女。Leetcode 716 Max Stack. 中间各种讨论。不同的解法什么的。先给了两个Stack的 解法。然后又是TreeMap的解法。
// . 1point3acres
// 第三轮，设计，韩国大叔，问了个消息队列的设计问题。开始扯了一大堆什么，问了requirement，从哪些方面考虑之类的。结果后来，他说你别扯这么多，设计个单机的，
// 主要侧重在功能如何实现，Schema和API之类如何实现。中间走了不少弯路。教训是，一定先要把问题弄明白。上来就套Design那一套行不通

// 第四轮，代码，白男，Word Search I  Leetcode原题。没太多好struggle的，解决之，然后分析复杂度。

// 第五轮，设计，三哥，上来没太多废话，题目设计append only datastore like HDFS。听到题目还心中窃喜，因为对这块比较了解。哪知道三锅明显就不想让我过。
// 一路各种打断，发问，challenge，各种挑毛病。他说的那些，我马上就准备说得。
// 他就抢先挑出来。形成一种事态就是他带着我走。我很有种冲动跟他说let me finish。后来应该找HR 投诉的。

// 第六轮，设计，三哥，设计metrics collection and monitor system， 收集host的1second，1minute，1hour metrics。如何scale，
// 如果是10000 hosts怎么收集。提供各种方案，kafka，HDFS，pub-sub model。各种分析优缺点，然后go over workflow。这轮还算比较顺利。

// 1. 给一个字符串S ,看是否能够包括一列目标子串[s1, s2, ..., sn]并且子串在S中位置不重合
// 2. 给一列数[n1, n2, ...]可能有元素重复，返回所有可能的排列
// 3. 设计一个文本数据库，对于query 高效的返回某个key在数据库中出现的所有位置, 出现次数是否大于k
// 4. behavior question: 你在项目中遇到过最难的问题是什么，跟经理意见不一致怎么办，等等
// 5.设计一个传感器网络，有大量的传感器实时发送数据，access point 向这些传感器发送query,如何快速得到结果

// 2018(10-12月) FrontEndEng 本科 全职@linkedin -猎头- Onsite | Other | 在职跳槽
// 七轮，早上10点到下午四点半
// 第一轮，设计Autocomplete前端
// 第二轮，0，1组成的二维数组，1代表wall，输出从[x0, y0]到[x1, y1]的最短路径
// 第三轮，午饭，是一轮要交feedback的interview，会问一些background
// 第四轮，简单计算器布局（显示屏，数字键，功能键），实现简单运算（没有括号，运算符没有优先级，比如1 + 2 * 3，先计算1 + 2，再计算乘3，最后结果是9）
// 第五轮，设计hangman
// 第六轮，问最喜欢的app，linkedin有什么要改进的地方，平时上那些技术网站论坛
// 第七轮，manager面，behavior question

// 1. Manager 聊天，就是问些bq和兴趣之类的。
// 2. Technical comminication
// 3. 蠡口药私酒/蠡口留就
// 4. 蠡口药物以/蠡口而无私
// 5. 系统设计：top k exceptions

// round 1:  ALL O(1)
// round 2: tech communication. From 1point 3acres bbs
// round 3: tiny url
// round 4: experiences, behavior
// round 5: 两题，
// 1. 出图中连通分量
// 2. nested iterator.

// 一面698+merge interval
// 二面paint house II，follow-up有环
// 三面behavior面，面试官把我实习经历往死里问。让我改实习project的design，感觉活生生面成了system design。
// 四面传说中的Software Design & Architecture轮。我以为是解决scalability的问题，结果问了一个经典OOD，设计高层电梯调度系统。注意是高层。
// 这轮我完全没准备过，面的一塌糊涂，其实前三轮我面的还不算差，这轮算是血崩，估计挂在这轮了。. 

// onsite 4轮
// 第一轮BQ 甚至问到了我为什么选择现在的学校。。扯了半天还被说你这个答案hire committee不会满意的 我？？？？ 英国人，面试官人很好
// 第二轮 问了word ladder2 巴基斯坦小姐姐+中国人小哥，扯了半天优化
// 中饭见证了LinkedIn的食堂真的好。。。onsite下来没有公司可以比。。。
// 第三轮 中国小哥 系统设计 购物网站
// 第四轮manager级别不知道哪里人 算法 题目不记得了。。。但是最后因为面试官习惯写java。。。我用的c++还起了一点分歧。。。

// . 第一小题忘了:-(， 第二小题，李扣716, O(n) 解法比较简单，卡在了O(logn)解法，. check 1point3acres for more.
// 2. 设计graph题， 给定一个int[] getFridend(int user)，O(1) complexity, 求两个users 是不是一级联系，二级联系和三级联系。先在local解，后来问图很大，怎么scale到多个machine上. From 1point 3acres bbs
// 3. 李抠 尔舞溜， 散溜溜，感觉答得还行. From 1point 3acres bbs
// 4. 设计题，实现线程池/ExecutorService，跑需要延时的任务。面试官说答得挺好。
// 5. 设计题，event 
// publish and query. 存event id和内容，内容大小不定。然后query API是getEventSince(id, int bufferSize), 求怎么存储数据可以更利于high concurrency。
// 这题答得不太好，要了好多hint。最后面试官还说到想问lock，但是没时间了。


// 6. 和经理面基。culture轮

// maxStack，findCelebrity，max points on a line, print leafs in a binary tree， 系统设计面的是design calendar



// coding：
// 第一輪:  蠡口 四散兒 和 蠡口 武器， 第一題做的很順利，但是第二題面試官一直追問我代碼裏面的一個邏輯問我爲什麽這麽寫，
// 我們討論了將近10分鐘這個問題，導致代碼差點沒有寫完，感覺這輪的這個溝通解釋邏輯的問題很可能是挂點。
// 第二輪： 蠡口 兒妻兒 和 蠡口 傘流流， 感覺這輪還是比較順利的，第一題和面試官説了最優解，但是面試官說不需要寫那個，寫nlogk複雜度的就可以了（關於複雜度我好像口誤説錯了 説成了klogk但是面試官好像也沒細問），然後follow up是如何不用pq. 1point3acres
// desgin：
// 設計一個blocking system，系統可以返回given ip是不是有毒的，但是系統本身不能檢測，需要call 第三方api， 
// 這個裏面有個trade off 沒説清楚沒説出來 感覺可能會挂，就是call 第三方api的次數和準確性的問題，
// 因爲沒有經驗，想了半天都沒想出來，最後在面試官的提醒下，説到了call 第三方api可能會花錢之類的，然後最後落到了因爲資源有限就只能查詢 top k ip了（kafka）。
// 然後是tech talk 和 host mangaer， 這兩輪説的都一般，尤其是host manager， 因爲是最後一輪太累了加上有點緊張，感覺有些東西可能沒説明白 甚至面試官可能誤解了。。



// KV设计。延时Task。TOP K。Monitor/Alert。