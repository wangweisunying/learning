public class Solution {
    public String addStrings(String num1, String num2) {
        String res = "";
        int m = num1.length(), n = num2.length(), i = m - 1, j = n - 1, flag = 0;
        while(i >= 0 || j >= 0){
            int a, b; 
            if(i >= 0){
                a = num1.charAt(i--) - '0';
            }
            else{
                a = 0;
            }
            if(j >= 0){
                b = num2.charAt(j--) - '0';
            }
            else{
                b = 0;
            }
            int sum = a + b + flag;
            res =(char)(sum % 10 + '0') + res;
            flag = sum / 10;
        }
        return flag == 1 ? "1" + res: res; 
    }
}






public class Solution {
    public String addStrings(String nums1, String nums2) {
        int m = nums1.length();
        int n = nums2.length();
        
        int i = m - 1 , j = n - 1 ,flag = 0;
        String res = "";
        while(i >= 0 || j >= 0){
            int x = 0;
            if(i >= 0){
                x = nums1.charAt(i) - '0';
            }
            int y = 0 ;
            if(j >= 0){
                y = nums2.charAt(j) - '0';
            }
            int sum = x + y + flag;
            res = String.valueOf(sum % 10) + res;
            flag = sum /10; 
            i --;
            j --;
        }
        return flag = 1 ? "1"+ res : res; 
    }
}
