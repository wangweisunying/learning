// 277. Find the Celebrity
// Medium

// 486

// 72

// Favorite

// Share
// Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that
//  all the other n - 1 people know him/her but he/she does not know any of them.

// Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

// You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

// Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party.
//  If there is no celebrity, return -1.


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

// 一次判断有2次结果a - > b  true b是candidate ， a不是candidate , a !->b  b一定不是candidate ，a 可能是candidate
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int can = 0;
        // can 若存在 ，can 不认识 can之后的人已经问过
        for(int i = 1; i< n; i++){
            if(knows(can , i)) can = i;
        } 
        //确定之前的人 can 也不认识
        for(int i = 0 ; i < can ; i++){
            if(knows(can , i)) return -1;
        }
        
        //确定can之后的人不认识can
        for(int i = can + 1 ;i < n ; i++){
            if(!knows(i , can)) return -1;
        }
        return can;
    }
}



