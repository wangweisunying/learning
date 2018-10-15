// Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

// How we serialize an undirected graph:

// Nodes are labeled uniquely.

// We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.

// As an example, consider the serialized graph {0,1,2#1,2#2,2}.

// The graph has a total of three nodes, and therefore contains three parts as separated by #.

// First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
// Second node is labeled as 1. Connect node 1 to node 2.
// Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
// Visually, the graph looks like the following:

//    1
//   / \
//  /   \
// 0 --- 2
//      / \
//      \_/
// Example
// return a deep copied graph.

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

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
// 从1个点找到所有点
// 复制所有的点
// 复制所有的边
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null){
            return node;
        }
        HashSet<UndirectedGraphNode> set = new HashSet();
        Queue<UndirectedGraphNode> que = new LinkedList();
        que.offer(node);
        set.add(node);
        
        while(!que.isEmpty()){
            UndirectedGraphNode curNode = que.poll();
            for(UndirectedGraphNode neighbor : curNode.neighbors){
                if(!set.contains(neighbor)){
                    que.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }
        ArrayList<UndirectedGraphNode> nodes = new ArrayList(set);
         
        HashMap<UndirectedGraphNode , UndirectedGraphNode> map = new HashMap();
        for(UndirectedGraphNode n : nodes){
            map.put(n , new UndirectedGraphNode(n.label));
        }
        
        for(UndirectedGraphNode n : nodes){
            UndirectedGraphNode newNode = map.get(n);
            for(UndirectedGraphNode neighbor : n.neighbors){
                UndirectedGraphNode newNeighbor = map.get(neighbor);
                newNode.neighbors.add(newNeighbor);
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
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null){
            return node;
        }
        
        //traversal the graph and get all the nodes 由点及面
        ArrayList<UndirectedGraphNode> nodes = getNodes(node);

        //use HashMap to copy all the nodes
        HashMap<UndirectedGraphNode , UndirectedGraphNode> map = new HashMap();
        for(UndirectedGraphNode n : nodes){
            map.put( n , new UndirectedGraphNode(n.label));
        }
        
        //copy all the edges
        for(UndirectedGraphNode n : nodes){
            UndirectedGraphNode newNode = map.get(n);
            for(UndirectedGraphNode neighbor : n.neighbors){
                UndirectedGraphNode newNeighbor = map.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }

        }
        return map.get(node);
        
    }
    private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node){
        HashSet<UndirectedGraphNode> set = new HashSet();
        Queue<UndirectedGraphNode> que = new LinkedList();
        que.offer(node);
        set.add(node);    
        while(!que.isEmpty()){
            UndirectedGraphNode cur = que.poll();
            for(UndirectedGraphNode tmp : cur.neighbors){
                if(!set.contains(tmp)){
                    que.offer(tmp);
                    set.add(tmp);
                }
            }
            
        }
        return new ArrayList<UndirectedGraphNode>(set);
    }
}


// 从1个点找到所有点
// 复制所有的点
// 复制所有的边

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }

        // use bfs algorithm to traverse the graph and get all nodes.
        ArrayList<UndirectedGraphNode> nodes = getNodes(node);
        
        // copy nodes, store the old->new mapping information in a hash map
        HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
        for (UndirectedGraphNode n : nodes) {
            mapping.put(n, new UndirectedGraphNode(n.label));
        }
        
        // copy neighbors(edges)
        for (UndirectedGraphNode n : nodes) {
            UndirectedGraphNode newNode = mapping.get(n);
            for (UndirectedGraphNode neighbor : n.neighbors) {
                UndirectedGraphNode newNeighbor = mapping.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }
        
        return mapping.get(node);
    }
    
    private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashSet<UndirectedGraphNode> set = new HashSet<>();
        
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            for (UndirectedGraphNode neighbor : head.neighbors) {
                if(!set.contains(neighbor)){
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        
        return new ArrayList<UndirectedGraphNode>(set);
    }
}