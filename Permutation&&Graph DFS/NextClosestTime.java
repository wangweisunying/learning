// Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

// You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

// Example
// Given time = "19:34", return "19:39".

// Explanation: 
// The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
// Given time = "23:59", return "22:22".

// Explanation: 
// The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.


public class Solution {
    /**
     * @param time: the given time
     * @return: the next closest time
     */
    public String nextClosestTime(String time) {
        char[] chars = (time.substring(0, 2) + time.substring(3, 5)).toCharArray();
        ArrayList<String> res = new ArrayList();
        dfs(res, chars, "");
        return findClosest(res, time);

    }

    private String findClosest(ArrayList<String> res, String time) {
        String resTime = time ;
        int duration = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
        int min = Integer.MAX_VALUE;
        for (String tmp : res) {
            int tmpDuration = Integer.parseInt(tmp.substring(0, 2)) * 60 + Integer.parseInt(tmp.substring(3, 5));
            if (tmpDuration > duration) {
                if (tmpDuration - duration < min) {
                    min = tmpDuration - duration;
                    resTime = tmp;
                }
            }
            if (tmpDuration < duration) {
                if (tmpDuration + 1440 - duration < min) {
                    min = tmpDuration + 1440 - duration;
                    resTime = tmp;
                }   
            }
        }
        return resTime;

    }

    private void dfs(ArrayList<String> res, char[] chars, String cur) {
        if (cur.length() == 4) {
            if (isValid(cur)) {
                res.add(cur.substring(0, 2) + ":" + cur.substring(2, 4));
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            dfs(res, chars, cur + chars[i]);
        }
    }

    private boolean isValid(String cur) {
        int hr = Integer.parseInt(cur.substring(0, 2));
        int min = Integer.parseInt(cur.substring(2, 4));
        if (hr >= 24 || min >= 60) {
            return false;
        }
        return true;
    }
}