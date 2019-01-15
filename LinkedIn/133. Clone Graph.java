// 133. Clone Graph
// DescriptionHintsSubmissionsDiscussSolution
// Given the head of a graph, return a deep copy (clone) of the graph. Each node in the graph contains a label (int) and a list (List[UndirectedGraphNode]) of its neighbors. There is an edge between the given node and each of the nodes in its neighbors.


// OJ's undirected graph serialization (so you can understand error output):
// Nodes are labeled uniquely.

// We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 

// As an example, consider the serialized graph {0,1,2#1,2#2,2}.

// The graph has a total of three nodes, and therefore contains three parts as separated by #.

// First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
// Second node is labeled as 1. Connect node 1 to node 2.
// Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 

// Visually, the graph looks like the following:


/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

 // 从1个点找到所有点 //hashset dfs or bfs
// 复制所有的点 // hashmap store relation map old -> new
// 复制所有的边 //iterate the set and use hashmap quick find the relation ship
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        Set<UndirectedGraphNode> set = new HashSet();
        Queue<UndirectedGraphNode> que = new LinkedList();
        set.add(node);
        que.offer(node);
        while(!que.isEmpty()){
            UndirectedGraphNode cur = que.poll();
            for(UndirectedGraphNode next : cur.neighbors){
                if(set.contains(next))continue;
                que.offer(next);
                set.add(next);
            }
        }
        
        Map<UndirectedGraphNode , UndirectedGraphNode> map = new HashMap();
        for(UndirectedGraphNode x : set) map.put(x , new UndirectedGraphNode(x.label));



        for(UndirectedGraphNode x : set){
            for(UndirectedGraphNode next : x.neighbors){
                map.get(x).neighbors.add(map.get(next));    
            }
        }
        return map.get(node);
    }
}
public class Solution {
    /*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
// 从1个点找到所有点 //hashset
// 复制所有的点 // hashmap store relation map old -> new
// 复制所有的边 //use hashmap quick find the relation ship
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null){
            return null;
        }
        //find all nodes
        Queue<UndirectedGraphNode> que = new LinkedList();
        HashSet<UndirectedGraphNode> nodes_set = new HashSet();
        que.offer(node);
        nodes_set.add(node);
        while(!que.isEmpty()){
            UndirectedGraphNode node_unit = que.poll();
            for(UndirectedGraphNode neighbor : node_unit.neighbors){
                if(!nodes_set.contains(neighbor)){
                    nodes_set.add(neighbor);
                    que.offer(neighbor);   
                }
            }
        }

        //copy all the nodes using hashmap for connection use
        HashMap<UndirectedGraphNode , UndirectedGraphNode> linkMap = new HashMap();
        for(UndirectedGraphNode node_unit : nodes_set){
            linkMap.put(node_unit , new UndirectedGraphNode(node_unit.label));
        }
        
        //connect all the nodes
        for(UndirectedGraphNode node_unit : nodes_set){
            for(UndirectedGraphNode neighbor : node_unit.neighbors){
                linkMap.get(node_unit).neighbors.add(linkMap.get(neighbor));
            } 
        }
        return linkMap.get(node);


    }
}


public class Solution {
    /*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
// 从1个点找到所有点 //hashset dfs or bfs
// 复制所有的点 // hashmap store relation map old -> new
// 复制所有的边 //use hashmap quick find the relation ship
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null){
            return null;
        }
        //find all nodes
        HashSet<UndirectedGraphNode> nodes_set = new HashSet();
        dfs(node , nodes_set);
        
        // while(!que.isEmpty()){
        //     UndirectedGraphNode node_unit = que.poll();
        //     for(UndirectedGraphNode neighbor : node_unit.neighbors){
        //         if(!nodes_set.contains(neighbor)){
        //             nodes_set.add(neighbor);
        //             que.offer(neighbor);   
        //         }
        //     }
        // }

        //copy all the nodes using hashmap for connection use
        HashMap<UndirectedGraphNode , UndirectedGraphNode> linkMap = new HashMap();
        for(UndirectedGraphNode node_unit : nodes_set){
            linkMap.put(node_unit , new UndirectedGraphNode(node_unit.label));
        }
        
        //connect all the nodes
        for(UndirectedGraphNode node_unit : nodes_set){
            for(UndirectedGraphNode neighbor : node_unit.neighbors){
                linkMap.get(node_unit).neighbors.add(linkMap.get(neighbor));
            } 
        }
        return linkMap.get(node);
    }
    private void dfs(UndirectedGraphNode node , HashSet<UndirectedGraphNode> nodes_set){
        nodes_set.add(node);
        for(UndirectedGraphNode neighbor : node.neighbors){
            if(nodes_set.contains(neighbor)){
                continue;
            }
            dfs(neighbor , nodes_set);
        }
    }
}