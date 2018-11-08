// str1
// “ABABZ”,
// str2
// "ABZ",
// return 2;


public static int substringCombinations(String source, String target) {
        Set<Character> sourceChars = new HashSet<>();
         
        for (char c : source.toCharArray()) {
            sourceChars.add(c);
        }
         
        for (char c : target.toCharArray()) {
            // target contains a character that is not in the source string
            if (!sourceChars.contains(c)) {
                return -1;
            }
        }
         
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= Math.min(source.length(), target.length()); i++) {
            if (source.contains(target.substring(0, i))) {
                queue.offer(i);
            } else {
                break;
            }
        }
         
        int result = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
             
            while (size-- > 0) {
                int start = queue.poll();
                if (start == target.length()) {
                    return result;
                }
                for (int i = start + 1; i <= Math.min(target.length(), start + source.length()); i++) {
                    if (source.contains(target.substring(start, i))) {
                        queue.offer(i);
                    } else {
                        break;
                    }
                }
            }
             
            result++;
        }
        return result;
    }