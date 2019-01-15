/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design;

import java.util.Iterator;
import java.util.StringTokenizer;

/**
 *
 * @author Wei Wang
 */
public class MapReduce {
    public static void main(String[] args){
        StringTokenizer tokenizer = new StringTokenizer("a b c");
        System.out.println(tokenizer.countTokens());
        while(tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());
        }
    }
    
   
 abstract class OutputCollector<K, V> {
      public abstract void collect(K key, V value);
         // Adds a key/value pair to the output buffer
  }
    public static class Map {
        public void map(String key, String value, OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            StringTokenizer tokenizer = new StringTokenizer(value);
            while(tokenizer.hasMoreTokens()){
                output.collect(tokenizer.nextToken() , 1);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            int sum = 0;
            while(values.hasNext()){
                sum += values.next();
            }
            output.collect(key , sum);
        }
    }


}
