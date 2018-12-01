// 753. Cracking the Safe
// DescriptionHintsSubmissionsDiscussSolution
// There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.

// You can keep inputting the password, the password will automatically be matched against the last n digits entered.

// For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.

// Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.

// Example 1:
// Input: n = 1, k = 2
// Output: "01"
// Note: "10" will be accepted too.
// Example 2:
// Input: n = 2, k = 2
// Output: "00110"
// Note: "01100", "10011", "11001" will be accepted too.
// Note:
// n will be in the range [1, 4].
// k will be in the range [1, 10].
// k^n will be at most 4096.


//dfs 罗搜找出share 前一个密码可能出现的后（n -1）位的另一个密码 只加入最后一位 即可







class Solution {
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int total = (int)(Math.pow(k , n));
        for(int i = 0 ; i < n ; i++){
            sb.append('0');
        }
        //visited need to add cuz sb already append '00';
        if(dfs(sb, total , new HashSet<String>(Arrays.asList(sb.toString())), n , k)){
            return sb.toString();
        }
        return "";
    }
    private boolean dfs(StringBuilder sb , int total , Set<String> visited , int n , int k){
        if(visited.size() == total){
            return true;
        }

        //share part
        String pre = sb.substring(sb.length() - n + 1, sb.length());
        for(int i = 0 ; i < k ; i++){
            //next new part
            String next = pre + i;
            //fiter;
            if(visited.contains(next)) {
                continue;
            }
            visited.add(next);
            sb.append(i);
            if(dfs(sb , total , visited , n , k)){
                return true;
            }
            sb.setLength(sb.length() - 1);
            visited.remove(next);
        }
        return false;
    }
}



class Solution {
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int total = (int) (Math.pow(k, n));
        for (int i = 0; i < n; i++) sb.append('0');

        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());

        dfs(sb, total, visited, n, k);

        return sb.toString();
    }

    private boolean dfs(StringBuilder sb, int goal, Set<String> visited, int n, int k) {
        if (visited.size() == goal) return true;
        String prev = sb.substring(sb.length() - n + 1, sb.length());
        for (int i = 0; i < k; i++) {
            String next = prev + i;
            if (!visited.contains(next)) {
                visited.add(next);
                sb.append(i);
                if (dfs(sb, goal, visited, n, k)) return true;
                else {
                    visited.remove(next);
                    sb.delete(sb.length() - 1, sb.length());
                }
            }
        }
        return false;
    }

}






    // private void dfs(List<List<Integer>> res, int n, int k, List<Integer> list) {
    //     if (list.size() == n) {
    //         res.add(new ArrayList(list));
    //         return;
    //     }
    //     for (int i = 0; i < k; i++) {
    //         list.add(i);
    //         dfs(res, n, k, list);
    //         list.remove(list.size() - 1);
    //     }
    // }