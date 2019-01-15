// 273. Integer to English Words
// DescriptionHintsSubmissionsDiscussSolution
// Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

// Example 1:

// Input: 123
// Output: "One Hundred Twenty Three"
// Example 2:

// Input: 12345
// Output: "Twelve Thousand Three Hundred Forty Five"
// Example 3:

// Input: 1,234,567
// Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
// Example 4:

// Input: 1234567891
// Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

public String numberToWords(int num) {
    if (num == 0) return "Zero";

    int i = 0;
    String words = "";
    
    while (num > 0) {
        if (num % 1000 != 0)
    	    words = helper(num % 1000) +THOUSANDS[i] + " " + words;
    	num /= 1000;
    	i++;
    }
    
    return words.trim();
}

private String helper(int num) {
    if (num == 0)
        return "";
    else if (num < 20)
        return LESS_THAN_20[num] + " ";
    else if (num < 100)
        return TENS[num / 10] + " " + helper(num % 10);
    else
        return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
}



class Solution {
    Map<Integer, String> map;
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        map = new HashMap();
        map.put(0 , "Zero");
        map.put(1 , "One");
        map.put(2 , "Two");
        map.put(3 , "Three");
        map.put(4 , "Four");
        map.put(5 , "Five");
        map.put(6 , "Six");
        map.put(7 , "Seven");
        map.put(8 , "Eight");
        map.put(9 , "Nine");
        map.put(10 , "Ten");
        map.put(11 , "Eleven");
        map.put(12 , "Twelve");
        map.put(13 , "Thirteen");
        map.put(14 , "Fourteen");
        map.put(15 , "Fifteen");
        map.put(16 , "Sixteen");
        map.put(17 , "Seventeen");
        map.put(18 , "Eighteen");
        map.put(19 , "Nineteen");
        map.put(20 , "Twenty");
        map.put(30 , "Thirty");
        map.put(40 , "Forty");
        map.put(50 , "Fifty");
        map.put(60 , "Sixty");
        map.put(70 , "Seventy");
        map.put(80 , "Eighty");
        map.put(90 , "Ninety");
        StringBuilder sb = new StringBuilder();
        int remain1 = num / (int)(1e9);
        num %= (int)(1e9);
        if(remain1 != 0){
            sb.append(helper(remain1)).append("Billion").append(" ");
        }
        
        remain1 = num / (int)(1e6);
        num %= (int)(1e6);
        if(remain1 != 0){
            sb.append(helper(remain1)).append("Million").append(" ");
        }
        remain1 = num / (int)(1e3);
        num %= (int)(1e3);
        if(remain1 != 0){
            sb.append(helper(remain1)).append("Thousand").append(" ");
        }
        if(num != 0){
            sb.append(helper(num));
        }
        return sb.toString().trim();
    }
    private String helper(int num){
        StringBuilder sb = new StringBuilder();
        int hu = num / 100;
        int rest = num % 100;
        if(hu != 0){
            sb.append(map.get(hu)).append(" ").append("Hundred").append(" ");
        }
        
        if(rest != 0){
            if(rest <= 20){
                sb.append(map.get(rest)).append(" ");
            }
            else{
                sb.append(map.get((rest / 10) * 10)).append(" ");
                if(rest % 10!= 0){
                    sb.append(map.get(rest % 10)).append(" ");
                } 
            }
        }
        return sb.toString();
    }
}


public String numberToWords(int num) {
        if(num==0) {
            return "Zero";
        }
        return helper(num);
    }
    public String helper(int num) {
        String[] words = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        StringBuilder res = new StringBuilder();
        
        if(num>=1000000000) {
            res.append(helper(num/1000000000)).append(" Billion ").append(helper(num%1000000000));
        } else if(num>=1000000) {
            res.append(helper(num/1000000)).append(" Million ").append(helper(num%1000000));
        } else if(num>=1000) {
            res.append(helper(num/1000)).append(" Thousand ").append(helper(num%1000));
        } else if(num>=100) {
            res.append(helper(num/100)).append(" Hundred ").append(helper(num%100));
        } else if(num>=20) {
            res.append(words[(num-20)/10+20]).append(" ").append(words[num%10]);
        } else {
            res.append(words[num]);
        }
        
        return res.toString().trim();
    }

