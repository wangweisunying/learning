// 727. Minimum Window Subsequence
// DescriptionHintsSubmissionsDiscussSolution
// Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

// If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

// Example 1:

// Input: 
// S = "abcdebdde", T = "bde"
// Output: "bcde"
// Explanation: 
// "bcde" is the answer because it occurs before "bdde" which has the same length.
// "deb" is not a smaller window because the elements of T in the window must occur in order.
 

// Note:

// All the strings in the input will only contain lowercase letters.
// The length of S will be in the range [1, 20000].
// The length of T will be in the range [1, 100].




// "abcdebdde"
// "bde"
// Your answer
// "deb"
// Expected answer
// "bcde"


// dp[i][j] stores the starting index of the substring where T has length i and S has length j.

// So dp[i][j would be:
// if T[i - 1] == S[j - 1], this means we could borrow the start index from dp[i - 1][j - 1] to make the current substring valid;
// else, we only need to borrow the start index from dp[i][j - 1] which could either exist or not.

// Finally, go through the last row to find the substring with min length and appears first.

class Solution {
    public String minWindow(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int sindex = 0, tindex = 0, slen = s.length, tlen = t.length, start = -1, len = slen;
        while(sindex < slen) {
            if(s[sindex] == t[tindex]) {
                if(++tindex == tlen) {
                    //check feasibility from left to right of T
                    int end = sindex+1;
                    //check optimization from right to left of T
                    while(--tindex >= 0) {
                        while(s[sindex--] != t[tindex]);
                    }
                    ++sindex;
                    ++tindex;
                    //record the current smallest candidate
                    if(end - sindex < len) {
                        len = end - sindex;
                        start = sindex;
                    }
                }
            }
            ++sindex;
        }
        return start == -1? "" : S.substring(start, start + len);
    }
}



// The basic idea is simple and based on two pointer:
// (1) check feasibility,
// (2) check optimization.

class Solution {
    public String minWindow(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int sindex = 0, tindex = 0, slen = s.length, tlen = t.length, start = -1, len = slen;
        while(sindex < slen) {
            if(s[sindex] == t[tindex]) {
                if(++tindex == tlen) {
                    //check feasibility from left to right of T
                    int end = sindex+1;
                    //check optimization from right to left of T
                    while(--tindex >= 0) {
                        while(s[sindex--] != t[tindex]);
                    }
                    ++sindex;
                    ++tindex;
                    //record the current smallest candidate
                    if(end - sindex < len) {
                        len = end - sindex;
                        start = sindex;
                    }
                }
            }
            ++sindex;
        }
        return start == -1? "" : S.substring(start, start + len);
    }
}


// State:
// dp[i][j] = x indicates that T[0..j] is subsequence of S[x..i]
// Final(Aim) State:
// min(i - dp[i][T.length() - 1] + 1) for 0 <= i <= S.length() - 1
// State Transfer:
// If S[i] equals to T[j], dp[i][j] = max(dp[x][j - 1]) for 0 <= x <= i - 1

// The code below implements the idea above,

public String minWindow(String S, String T) {
        int[][] dp = new int[S.length()][T.length()];
        for (int[] tmp : dp) {
            Arrays.fill(tmp, -1);
        }
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == T.charAt(0)) {
                dp[i][0] = i;
            }
        }
        for (int j = 1; j < T.length(); j++) {
            int tmp = -1;
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == T.charAt(j)) {
                    dp[i][j] = tmp;
                }
                tmp = Math.max(tmp, dp[i][j - 1]);
            }
        }
        /* The same effect as the loop above, however, will cause TLE
        for (int i = 0; i < S.length(); i++) {
            for (int j = 1; j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j)) {
                    for (int x = 0; x <= i - 1; x++) {
                        dp[i][j] = Math.max(dp[i][j], dp[x][j - 1]);
                    }
                }
            }
        }
        */
        int minLength = Integer.MAX_VALUE, minStart = -1;
        for (int i = 0; i < S.length(); i++) {
            if (i - dp[i][T.length() - 1] + 1 < minLength) {
                if (dp[i][T.length() - 1] != -1) {
                    minLength = i - dp[i][T.length() - 1] + 1;
                    minStart = dp[i][T.length() - 1];
                }
            }
        }
        return minStart == -1 ? "" : S.substring(minStart, minStart + minLength);
    }