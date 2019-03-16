// 1. Two Sum
// Easy

// 9708

// 313

// Favorite

// Share
// Given an array of integers, return indices of the two numbers such that they add up to a specific target.

// You may assume that each input would have exactly one solution, and you may not use the same element twice.

// Example:

// Given nums = [2, 7, 11, 15], target = 9,

// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1].
 

# include <vector>
# include <unordered_map>
using namespace std;
class Solution {
    public:
    vector<int> twoSum(vector<int> &numbers, int target){
        unordered_map<int , int > hash;
        vector<int> res;
        for(int i = 0 ; i < numbers.size() ; ++i){
            int number =  target - numbers[i];
            if(hash.find(number) != hash.end()){
                res.push_back(hash[number] + 1);
                res.push_back(i + 1);
                return res;
            }
            else{
                hash[numbers[i]] = i;
            }
        }
        return res;
    }
};









class Solution {
    public:
    vector<int> twoSum(vector<int> &numbers, int target){
    //Key is the number and value is its index in the vector.
        unordered_map<int, int> hash;
        vector<int> result;
        for (int i = 0; i < numbers.size(); i++) {
            int numberToFind = target - numbers[i];

                //if numberToFind is found in map, return them
            if (hash.find(numberToFind) != hash.end()) {
                        //+1 because indices are NOT zero based
                result.push_back(hash[numberToFind] + 1);
                result.push_back(i + 1);			
                return result;
            }

                //number was not found. Put it in the map.
            hash[numbers[i]] = i;
        }
        return result;
    }
}