// 857. Minimum Cost to Hire K Workers
// DescriptionHintsSubmissionsDiscussSolution
// There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

// Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

// Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
// Every worker in the paid group must be paid at least their minimum wage expectation.
// Return the least amount of money needed to form a paid group satisfying the above conditions.

 

// Example 1:

// Input: quality = [10,20,5], wage = [70,50,30], K = 2
// Output: 105.00000
// Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
// Example 2:

// Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
// Output: 30.66667
// Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
 

// Note:

// 1 <= K <= N <= 10000, where N = quality.length = wage.length
// 1 <= quality[i] <= 10000
// 1 <= wage[i] <= 10000
// Answers within 10^-5 of the correct answer will be considered correct.

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double[][] workers = new double[wage.length][2];
        for(int i = 0 ; i < wage.length ; i++){
            workers[i][0] = quality[i];
            workers[i][1] = (double)wage[i]/quality[i];
        }

        Arrays.sort(workers , (a , b) ->(Double.compare(a[1] , b[1])));
        double min = Double.MAX_VALUE;
        double qSum = 0;
        Queue<Double> que = new PriorityQueue<>();
        for(double[] worker : workers){
            qSum += worker[0];
            que.offer(-worker[0]);
            if(que.size() > K) qSum += que.poll();
            if(que.size() == K){
                min = Math.min(min , worker[1] * qSum);
            }
        }
        return min;
    }
}
























class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double[][] total = new double[quality.length][2];
        
        for(int i = 0 ; i < wage.length ; i++){
            total[i][0] = (double)quality[i];
            total[i][1] = ((double)wage[i] / (double)quality[i]);
        }
        Arrays.sort(total , (a , b) -> (Double.compare(a[1] , b[1])));
        double min = Double.MAX_VALUE;
        Queue<Double> que = new PriorityQueue();
        double qSum = 0;
        for(double[] cur : total){
            que.offer(-cur[0]);
            qSum += cur[0];
            //将最大quality的元素剔除
            if(que.size() > K){
                qSum += que.poll();
            }
            if(que.size() == K){
                min = Math.min(min , qSum * cur[1]);
            }
        }
        return min;
    }
}






// "1. Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group."
// So for any two workers in the paid group,
// we have wage[i] : wage[j] = quality[i] : quality[j]
// So we have wage[i] : quality[i] = wage[j] : quality[j]
// We pay wage to every worker in the group with the same ratio compared to his own quality.

// "2. Every worker in the paid group must be paid at least their minimum wage expectation."
// For every worker, he has an expected ratio of wage compared to his quality.

// So to minimize the total wage, we want a small ratio.
// So we sort all workers with their expected ratio, and pick up K first worker.
// Now we have a minimum possible ratio for K worker and we their total quality.

// As we pick up next worker with bigger ratio, we increase the ratio for whole group.
// Meanwhile we remove a worker with highest quality so that we keep K workers in the group.
// We calculate the current ratio * total quality = total wage for this group.

// We redo the process and we can find the minimum total wage.
// Because workers are sorted by ratio of wage/quality.
// For every ratio, we find the minimum possible total quality of K workers.

// Time Complexity
// O(NlogN) for sort.
// O(NlogK) for priority queue.


// 数学问题 ， 寻找最小的比率（wage / quality）  使得 结果 = quality * 比率（wage / quality）;
// 按 w /q 排序之后不需要担心 低于minum pay ，整体的工资比率在提高， 所以唯一影响结果的是 quliaty ， 越小越好， 需要遍历整个数组 比较得出最小值，剔除最大的qulity的人（pay 的最多 ！）

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        double[][] workers = new double[n][2];
        for(int i = 0 ; i < n ; i++){
            workers[i][0] = (double)(wage[i]) / quality[i];
            workers[i][1] = (double)quality[i];
        }
        Arrays.sort(workers , (a , b) -> ( Double.compare(a[0] , b[0])));
        Queue<Double> que = new PriorityQueue();
        double res = Integer.MAX_VALUE;
        int qSum = 0;
        for(double[] worker : workers){
            qSum += worker[1];
            //巧妙运用pq 将最大的quality 顶到最前面删掉！  
            que.offer(-worker[1]);
            if(que.size() > K){
                qSum += que.poll();
            }
            if(que.size() == K) res = Math.min(res , worker[0] * qSum ); 
        }
        return res;
        
    }
}