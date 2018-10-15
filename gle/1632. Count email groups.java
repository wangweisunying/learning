
// 1632. Count email groups
// Give you an array of n email addresses.
// Find the number of email groups and each group should have more than one email address(the address can be duplicated). 
// If two strings have the same value after being transformed, 
// they are in the same group.

// The rules of transforming are as follows:

// Ignore all the characters '.' before the character '@'.
// Ignore the substring which starts with the first '+'(included) and ends with the character '@'(exclude).
// Example
// emails: ["abc.bc+c+d@jiuzhang.com", "abcbc+d@jiuzhang.com", "abc.bc.cd@jiuzhang.com"]

// count: 1

// "abc.bc+c+d@jiuzhang.com" transforms to "abcbc@jiuzhang.com"
// "abcbc+d@jiuzhang.com" transforms to "abcbc@jiuzhang.com"
// "abc.bc.cd@jiuzhang.com" transforms to "abcbccd@jiuzhang.com"
// We can see that the first address and the second address are in the same group, and there is no address can transform to the same address as the third one. 
// Therefore, there is only one group which meets the requrements.
// emails: ["abc.b+c+d@jiuzhang.com", "abcbc+d@jiuzhang.com", "abc.bc.cd@jiuzhang.com"]

// count: 0

// There is no group meet the conditions.
// Notice
// a email group have at least two same email address(after transforming)
public class Solution {
    /**
     * @param emails: Original email
     * @return: Return the count of groups which has more than one email address in it.
     */
public class Solution {
    /**
     * @param emails: Original email
     * @return: Return the count of groups which has more than one email address in it.
     */
        public int countGroups(List<String> emails) {
        int res = 0;
        HashMap<String , Boolean> map = new HashMap();
        for(String email : emails){
            email = email.substring(0 , email.indexOf('@')).replaceAll("\\.", "") + email.substring(email.indexOf('@'));
            if( email.substring(0 , email.indexOf('@')).contains("+")){
                email = email.substring(0 , email.indexOf('@')).substring(0 , email.indexOf('+')) + email.substring(email.indexOf('@'));
            }
            
            if(map.containsKey(email) ){
                if(map.get(email)){
                    res++;
                    map.put(email , false);
                }    
            }
            else{
                map.put(email , true);
            }
        }
        System.out.println(map);
        return res;
    }
}
}