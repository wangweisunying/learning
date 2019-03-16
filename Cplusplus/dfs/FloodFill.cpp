// 733. Flood Fill
// Easy

// 353

// 85

// Favorite

// Share
// An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

// Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

// To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, 
// plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

// At the end, return the modified image.

// Example 1:
// Input: 
// image = [[1,1,1],
        // [1,1,0],
        // [1,0,1]]
// sr = 1, sc = 1, newColor = 2
// Output: [[2,2,2],
        // [2,2,0],
        // [2,0,1]]
// Explanation: 
// From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
// by a path of the same color as the starting pixel are colored with the new color.
// Note the bottom corner is not colored 2, because it is not 4-directionally connected
// to the starting pixel.
// Note:

// The length of image and image[0] will be in the range [1, 50].
// The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
// The value of each color in image[i][j] and newColor will be an integer in [0, 65535].



//dfs 
#include <vector>
#include <list>
using namespace std;
class Solution {
private:
    vector<int> deltaX = { 1 , -1, 0 , 0};
    vector<int> deltaY = { 0 , 0 , 1 , -1};
    void dfs(vector<vector<int>>& image , int r , int c , int newColor , int old){
        image[r][c] = newColor;
        for(int i = 0 ; i < 4 ; i++){
            int x = r + deltaX[i];
            int y = c + deltaY[i];
            if(x >= image.size() || y >= image[0].size() || x < 0 || y < 0 || image[x][y] != old || image[x][y] == newColor) continue;
            dfs(image , x , y , newColor , old);
        }
    }
public:
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int newColor) {
        dfs(image , sr , sc , newColor , image[sr][sc]);
        return image;
    }
};


// bfs 
class Solution {
private:
    vector<int> deltaX = { 1 , -1, 0 , 0};
    vector<int> deltaY = { 0 , 0 , 1 , -1};
public:
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int newColor) {
        list<vector<int>> que;
        que.push_back(vector<int>{sr , sc});
        int origin = image[sr][sc];
        while(!que.empty()){
            vector<int> cur = que.front();
            image[cur[0]][cur[1]] = newColor;
            que.pop_front();
            for(int i = 0 ; i < 4 ; i++){
                int x = cur[0] + deltaX[i];
                int y = cur[1] + deltaY[i];
                if(x >= image.size() || y >= image[0].size() || x < 0 || y < 0 || image[x][y] != origin || image[x][y] == newColor){
                    continue;
                } 
                que.push_back(vector<int>{x , y});
            }
        }
        return image;
    }
};