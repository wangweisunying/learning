// 428. Serialize and Deserialize N-ary Tree
// DescriptionHintsSubmissionsDiscussSolution
// Serialization is the process of converting a data structure or object into a sequence of
//  bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later
//  in the same or another computer environment.

// Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. 
// There is no restriction on how your serialization/deserialization algorithm should work. 
// You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

// For example, you may serialize the following 3-ary tree

//       1
//   3   2   4
// 5   6

 

// as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

// Note:

// N is in the range of [1, 1000]
// Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.




//BFS  use 1_3,3_2,2_0,4_0,5_0,6_0 stand for the string
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<Node> que = new LinkedList();
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                Node cur = que.poll();
                sb.append(cur.val + "_" + cur.children.size() + ",");
                for(Node next : cur.children){
                    que.offer(next);
                }
            }
        }
        return sb.substring(0 , sb.length() - 1);
    }



    class NodeInfo{
        int size;
        Node node;
        NodeInfo(int size , Node node){
            this.size = size;
            this.node = node;
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.length() == 0) return null;
        String[] arr = data.split(",");
        int index = 0 ;
        Node root = new Node(Integer.parseInt(arr[index].split("_")[0]) , new ArrayList());
        int childSize = Integer.parseInt(arr[index].split("_")[1]);
        index++;
        Queue<NodeInfo> que = new LinkedList();
        que.offer(new NodeInfo(childSize , root));
        while(!que.isEmpty() && index < arr.length){
            int size = que.size();
            for(int i = 0 ; i < size ; i++){
                NodeInfo cur = que.poll();
                // if(cur.size == 0) cur.node.children = null;
                // else{
                    for(int j = 0 ; j < cur.size ; j++){
                        String[] nextInfo = arr[index++].split("_"); 
                        Node next = new Node(Integer.parseInt(nextInfo[0]) , new ArrayList());
                        cur.node.children.add(next);
                        que.offer(new NodeInfo(Integer.parseInt(nextInfo[1]) , next));
                    }
                // }
            }
        }
        return root;
    }
}




// Idea: preorder recursive traversal; add number of children after root val, in order to know when to terminate.
// Example: The example in description is serialized as: "1,3,3,2,5,0,6,0,2,0,4,0"

class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<String> list=new LinkedList<>();
        serializeHelper(root,list);
        return String.join(",",list);
    }
    
    private void serializeHelper(Node root, List<String> list){
        if(root==null){
            return;
        }else{
            list.add(String.valueOf(root.val));
            list.add(String.valueOf(root.children.size()));
            for(Node child:root.children){
                serializeHelper(child,list);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty())
            return null;
        
        String[] ss=data.split(",");
        Queue<String> q=new LinkedList<>(Arrays.asList(ss));
        return deserializeHelper(q);
    }
    
    private Node deserializeHelper(Queue<String> q){
        Node root=new Node();
        root.val=Integer.parseInt(q.poll());
        int size=Integer.parseInt(q.poll());
        root.children=new ArrayList<Node>(size);
        for(int i=0;i<size;i++){
            root.children.add(deserializeHelper(q));
        }
        return root;
    }
}



/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/











class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null){
            return null;
        }
        String res = root.val + "";
        String nextString = "";
        
        for(Node next : root.children){
            nextString += serialize(next) + " ";
        }
        if(nextString.length() != 0){
            res += "[" + nextString.substring(0 ,nextString.length() - 1) + "]";
        }
        return  res;
    }
    public Node deserialize(String s) {
        if(s == null){
            return null;
        }
        if(s.length() == 0){
            return new Node();
        }

        Node root = new Node();
        root.children = new ArrayList();
        List<String> list = new ArrayList();
        int val = 0;
        int i = 0;
        while(i < s.length() && Character.isDigit(s.charAt(i))){
            val = val * 10 + (s.charAt(i) - '0');
            i++;
        }
        // System.out.println(val);
        root.val = val;
        if(i + 1 >= s.length() - 1){
            return root;
        }
        
        String next = s.substring(i + 1 , s.length() - 1);
        for(String str : split(next)){
            root.children.add(deserialize(str));
        }
        return root;
    }
    private List<String> split(String s){
        List<String> res = new ArrayList();
        int level = 0 ;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '['){
                sb.append(s.charAt(i));
                level++;
            } 
            else if(s.charAt(i) == ']'){
                sb.append(s.charAt(i));
                level--;
            } 
            else if(s.charAt(i) == ' '){
                if(level == 0){
                    res.add(sb.toString());
                    sb.setLength(0);
                }
                else{
                    sb.append(s.charAt(i));
                }
            }
            else{
                sb.append(s.charAt(i));
            }
        }
        res.add(sb.toString());
        return res;
    }
}


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));