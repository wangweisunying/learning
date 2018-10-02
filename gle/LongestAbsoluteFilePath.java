// LongestAbsoluteFilePath
// Suppose we abstract our file system by a string in the following manner:

// The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

// dir
//     subdir1
//     subdir2
//         file.ext
// The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

// The string

// "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
// represents:

// dir
//     subdir1
//         file1.ext
//         subsubdir1
//     subdir2
//         subsubdir2
//             file2.ext
// The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2
//  containing a file file2.ext.

// We are interested in finding the longest (number of characters) 
// absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", 
// and its length is 32 (not including the double quotes).

// Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

// Example
// Give input = "dir\n\tsubdir1\n\tsubdir2\file.ext" return 20


public class Solution {
    /**
     * @param input: an abstract file system
     * @return: return the length of the longest absolute path to file
     */
    class Unit{
        String str;
        int index;
        int len;
        Unit(String str , int index ,int len){
            this.str = str;
            this.index = index;
            this.len = len;
        }
    }
    public int lengthLongestPath(String input) {
        if(!input.contains("\n")){
            return 0;
        }
        String[] arr = input.split("\n");
    
        boolean[] visited = new boolean[arr.length];
        Queue<Unit> que = new LinkedList();
        int res = 0;
        String y = "";
        for(int i = 0 ; i < arr.length ; i ++){
            if(!arr[i].startsWith("\t")){
                que.offer(new Unit( y + arr[i] + "/" , i , arr[i].length() + 1));
                visited[i] = true;
            }
        }
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0 ; i < size ; i ++){
                Unit unit = que.poll();
                y = unit.str;
                if(y.contains(".")){
                    res = Math.max(res , unit.len);
                }
                System.out.println(res);
                for(int j = unit.index + 1 ; j < arr.length ; j ++){
                    if(visited[j]){
                        break;
                    }
                    arr[j] = arr[j].substring(1);
                    if(!arr[j].startsWith("\t")){
                        visited[j] = true;
                        que.offer(new Unit(y + arr[j] + "/" , j , arr[j].length() + unit.len + 1));
                    }
                }
            }
        }
        return res - 1;
    }
}


public class Solution {
    /*
     * @param input an abstract file system
     * @return return the length of the longest absolute path to file
     */
    public int lengthLongestPath(String input) {
        // Write your code here
        if (input.length() == 0) {
            return 0;
        }
        int ans = 0;
        int[] sum = new int[input.length() + 1];

        for (String line : input.split("\n")) {
            int level = line.lastIndexOf('\t') + 2;
            int len = line.length() - (level - 1);
            if (line.contains(".")) {
                ans = Math.max(ans, sum[level - 1] + len);
            } else {
                sum[level] = sum[level - 1] + len + 1;
            }
        }
        return ans;
    }
}