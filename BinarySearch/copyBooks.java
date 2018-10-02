// Copy Books
// Given n books and the ith book has A[i] pages. You are given k people to copy the n books.

// n books list in a row and each person can claim a continous range of the n books. 
// For example one copier can copy the books from ith to jth continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).

// They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign books so that the slowest copier can finish at earliest time?

// Example
// Given array A = [3,2,4], k = 2.

// Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. )

// dp
public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        
    }
}





//值域二分法 起点终点  ， 然后通过条件来判断
public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        if(pages == null || pages.length == 0){
            return 0;
        }
        int s = Integer.MAX_VALUE;
        int e = 0;
        for(int x : pages){
            s = Math.min(s , x);
            e += x;
        }
        
        while( s + 1 < e){
            int mid = (s + e) / 2;
            if(can(pages , k , mid)){
                e = mid;
            } 
            else{
                s = mid;
            }
        }
        
        if(can(pages , k , s)){
            return s;
        }
        return e;
    }
    private boolean can(int[] pages, int k , int mid){
   
        int ct = 0;
        int res = -1;
        for(int i = 0 ; i < pages.length ; i++){
            if(res < pages[i]){
                if(mid < pages[i]){
                    return false;
                }
                if(mid >= pages[i]){ 
                    ct++;
                    res = mid - pages[i];
         
                }
            }
            else{
                res = res - pages[i];  
            }
        }
        return ct <= k;
    }
}

