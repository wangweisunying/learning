// RestoreIPAddresses
// Given a string containing only digits, restore it by returning all possible valid IP address combinations.

// Example
// Given "25525511135", return

// [
//   "255.255.11.135",
//   "255.255.111.35"
// ]
// Order does not matter.
// 先了解IP的格式，它的形式应该为：(0~255).(0~255).(0~255).(0~255)
public class Solution {
    /**
     * @param s: the IP string
     * @return: All possible valid IP addresses
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList();
        if (s.length() == 0) {
            return res;
        }

        dfs(res, s, "", 0 , 4);
        return res;
    }

    private void dfs(List<String> res, String s, String cur, int startIndex, int dotCt) {
        if (dotCt < 0) {
            return;
        }
        System.out.println(cur);
        System.out.println(startIndex);
        if (cur.length() - 4 == s.length() && dotCt == 0) {
            res.add(cur.substring(0 , cur.length() - 1));
        }
        for (int i = startIndex; i < s.length(); i++) {
            int ct = 0;
            for (int j = i + 1; j <= s.length(); j++) {
                if (!isValid(s.substring(i, j))) {
                    ct++;
                    continue;
                }
                dfs(res, s, cur + s.substring(i, j) + ".", j, dotCt - 1);
            }
            if (ct == 4) {
                return;
            }
        }
    }

    private boolean isValid(String ss) {
        if (ss.startsWith("0") && ss.length() > 1) {
            return false;
        }
        Long p = Long.parseLong(ss);
//        System.out.println(p);
        if (p >= 0 && p <= 255) {
            return true;
        }
        return false;
    }
}