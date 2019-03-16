// 582. Kill Process
// Medium

// 304

// 7

// Favorite

// Share
// Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

// Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, 
// which means this process has no parent process. All the PIDs will be distinct positive integers.

// We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

// Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that
//  when a process is killed, all its children processes will be killed. No order is required for the final answer.

// Example 1:
// Input: 
// pid =  [1, 3, 10, 5]
// ppid = [3, 0, 5, 3]
// kill = 5
// Output: [5,10]
// Explanation: 
//            3
//          /   \
//         1     5
//              /
//             10
// Kill 5 will also kill 10.
// Note:
// The given kill id is guaranteed to be one of the given PIDs.
// n >= 1.



#include<vector>
#include<unordered_map>
using namespace std;
class Solution {
public:
    vector<int> res;
    unordered_map<int , vector<int>> map;
    vector<int> killProcess(vector<int>& pid, vector<int>& ppid, int kill) {
        for(int i = 0 ; i < ppid.size() ; ++i){
            map[ppid[i]].push_back(pid[i]);
        }
        
        dfs(kill);
        return res;
    }
    void dfs(int kill ){
        res.push_back(kill);
        if(map.find(kill) == map.end()){
            return;
        }
        for(int i : map[kill]){
            dfs( i );
        }
    }
};

class Solution {
private:
    unordered_map<int, vector<int>> children;
    void buildChildren (vector<int>& pid, vector<int>& ppid) {
        for (int i = 0; i < pid.size(); i++)
            children[ppid[i]].push_back(pid[i]);
    }
    
    void getAllChildren (int pid, vector<int> &childrenList) {
        childrenList.push_back(pid);
        if (children.count(pid) == 0) return;
        for (auto& child: children[pid])
            getAllChildren(child, childrenList);
    }
    
public:
    vector<int> killProcess(vector<int>& pid, vector<int>& ppid, int kill) {
        buildChildren(pid, ppid);
        vector<int> ans;
        getAllChildren(kill, ans);
        return ans;
    }
};





// class Solution {
//     public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
//         Map<Integer , List<Integer> > map = new HashMap();
//         for(int i = 0 ;i< pid.size() ; i++){
//             map.computeIfAbsent(ppid.get(i) , x -> new ArrayList()).add(pid.get(i));
//         }
//         List<Integer> res = new ArrayList();
//         dfs(res , map , kill);
//         return res;       
//     }
//     private void dfs(List<Integer> res , Map<Integer , List<Integer> > map , int kill){
//         res.add(kill);
//         if(!map.containsKey(kill)){
//             return;
//         }
//         for(int i : map.get(kill)){
//             dfs(res , map, i);
//         }
//     }
// }

//memo search

// class Solution {
//     public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
//         Map<Integer , List<Integer> > map = new HashMap();
//         Map<Integer , List<Integer> > memo = new HashMap();
//         for(int i = 0 ;i< pid.size() ; i++){
//             map.computeIfAbsent(ppid.get(i) , x -> new ArrayList()).add(pid.get(i));
//         }
        
//         return dfs(memo , map , kill);      
//     }
//     private List<Integer> dfs( Map<Integer , List<Integer>> memo , Map<Integer , List<Integer> > map , int kill){
//         if(memo.containsKey(kill)) return memo.get(kill);
//         List<Integer> res = new ArrayList();
//         res.add(kill);
//         if(!map.containsKey(kill)){
//             return res;
//         }
//         for(int i : map.get(kill)){
//             res.addAll(dfs(memo , map, i));
//         }
//         memo.put(kill , res);
//         return res;
//     }
// }