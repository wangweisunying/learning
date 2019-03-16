// 429. N-ary Tree Level Order Traversal
// Easy

// 172

// 22

// Favorite

// Share
// Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

// For example, given a 3-ary tree:

 



 

// We should return its level order traversal:

// [
//      [1],
//      [3,2,4],
//      [5,6]
// ]
 

// Note:

// The depth of the tree is at most 1000.
// The total number of nodes is at most 5000.



// Definition for a Node.

#include <vector>
#include <queue>
using namespace std;
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
class Solution {
public:
	vector<vector<int>> levelOrder(Node* root) {
		vector<vector<int>> res;
        if(root == NULL) return res;
		queue<Node*> que;
		que.push(root);
		while (!que.empty()) {
			vector<int> curVector;
			int size = que.size();
			for (int i = 0; i < size; ++i) {
				Node* cur = que.front();
				que.pop();
				curVector.push_back(cur->val);
				for (Node* node : cur->children) {
					que.push(node);
				}
			}
			res.push_back(curVector);
		}
		return res;
	}
};



