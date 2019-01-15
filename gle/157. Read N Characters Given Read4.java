// 157. Read N Characters Given Read4
// DescriptionHintsSubmissionsDiscussSolution
// The API: int read4(char *buf) reads 4 characters at a time from a file.

// The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

// By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

// Example 1:

// Input: buf = "abc", n = 4
// Output: "abc"
// Explanation: The actual number of characters read is 3, which is "abc".
// Example 2:

// Input: buf = "abcde", n = 5 
// Output: "abcde"
// Note:
// The read function will only be called once for each test case.

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        int index = 0;
        int count = 4;
        while(index < n && count == 4){
            count = read4(buffer);
            for(int i = 0 ; i < count && index < n ; i++){
                buf[index++] = buffer[i];
            }
        }
        return index;
    }
}








public int read(char[] buf, int n) {
  boolean eof = false;      // end of file flag
  int total = 0;            // total bytes have read
  char[] tmp = new char[4]; // temp buffer
  
  while (!eof && total < n) {
    int count = read4(tmp);
    
    // check if it's the end of the file
    eof = count < 4;
    
    // get the actual count
    count = Math.min(count, n - total);
    
    // copy from temp buffer to buf
    for (int i = 0; i < count; i++) 
      buf[total++] = tmp[i];
  }
  
  return total;
}