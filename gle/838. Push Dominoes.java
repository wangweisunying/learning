// 838. Push Dominoes
// DescriptionHintsSubmissionsDiscussSolution
// There are N dominoes in a line, and we place each domino vertically upright.

// In the beginning, we simultaneously push some of the dominoes either to the left or to the right.



// After each second, each domino that is falling to the left pushes the adjacent domino on the left.

// Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

// When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

// For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

// Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', 
// if the i-th domino has not been pushed.

// Return a string representing the final state. 

// Example 1:

// Input: ".L.R...LR..L.."
// Output: "LL.RR.LLRRLL.."
// Example 2:

// Input: "RR.L"
// Output: "RR.L"
// Explanation: The first domino expends no additional force on the second domino.
// Note:

// 0 <= N <= 10^5
// String dominoes contains only 'L', 'R' and '.'

// two pointers 
class Solution {
    public String pushDominoes(String s) {
        List<Integer> list = new ArrayList();
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) != '.') list.add(i);
        }
        if(list.size() == 0) return s;
        char[] arr = s.toCharArray();
        if(list.get(0) != 0 && arr[list.get(0)] == 'L'){
            for(int i = 0 ; i < list.get(0) ; i++){
                arr[i] = 'L';
            }
        }
        for(int i = 0 ; i < list.size() - 1 ; i++){
            int i1 = list.get(i) , i2 = list.get(i + 1);
            if(i1 + 1 == i2) continue;
            if(arr[i1] == arr[i2]){
                for(int j = i1 + 1 ; j < i2 ; j++){
                    arr[j] = arr[i1];
                }
            }
            else if(arr[i1] == 'R' && arr[i2] == 'L'){
                while(i1 < i2){
                    arr[i1++] = 'R';
                    arr[i2--] = 'L';
                }
            }
            else{
                continue;
            }
        }
        if( (list.get(list.size() - 1) != arr.length - 1)  && arr[list.get(list.size() - 1)] == 'R'){
            for(int i = list.get(list.size() - 1) + 1 ; i < arr.length ; i++){
                arr[i] = 'R';
            }
        }
        return new String(arr);
    }
}