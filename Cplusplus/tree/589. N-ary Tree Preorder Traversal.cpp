// 589. N-ary Tree Preorder Traversal
// Easy

// 163

// 26

// Favorite

// Share
// Given an n-ary tree, return the preorder traversal of its nodes' values.

// For example, given a 3-ary tree:

 



 

// Return its preorder traversal as: [1,3,5,6,2,4].



// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};


#include <vector>
using namespace std;
class Solution {
public: 
    vector<int> res;
    vector<int> preorder(Node* root) {
        if(root == NULL) return res;
        res.push_back(root -> val);
        for(Node* node : root-> children){
            preorder(node);
        }
        return res;
    }
};



class Solution {
public:
    vector<int> res;
    vector<int> postorder(Node* root) {
        if(root == NULL) return res;
        for(Node* node : root-> children){
            postorder(node);
        }
        res.push_back(root -> val);
        return res;
    }
};