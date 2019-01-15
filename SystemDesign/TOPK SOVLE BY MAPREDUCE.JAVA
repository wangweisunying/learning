// MapReduce for Top N items
// In this post we'll see how to count the top-n items of a dataset; we'll again use the flatland book we used in a previous post: in that example we used the WordCount program to count the occurrences of every single word forming the book; now we want to find which are the top-n words used in the book. 

// Let's start with the mapper:
// public static class TopNMapper extends Mapper {

//         private final static IntWritable one = new IntWritable(1);
//         private Text word = new Text();

//         @Override
//         public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
//             String cleanLine = value.toString().toLowerCase().replaceAll("[_|$#<>\\^=\\[\\]\\*/\\\\,;,.\\-:()?!\"']", " ");
//             StringTokenizer itr = new StringTokenizer(cleanLine);
//             while (itr.hasMoreTokens()) {
//                 word.set(itr.nextToken().trim());
//                 context.write(word, one);
//             }
//       }
// }
// The mapper is really straightforward : the TopNMapper class defines an IntWritable set to 1 and a Text object; its map() method, like in the previous post, splits every line of the book into an array of single words and send to the reducers every word with the value of 1. 

// The reducer is more interesting:
// public static class TopNReducer extends Reducer {

//         private Map countMap = new HashMap<>();

//         @Override
//         public void reduce(Text key, Iterable values, Context context) throws IOException, InterruptedException {

//             // computes the number of occurrences of a single word
//             int sum = 0;
//             for (IntWritable val : values) {
//                 sum += val.get();
//             }

//             // puts the number of occurrences of this word into the map.
//             countMap.put(key, new IntWritable(sum));
//         }

//         @Override
//         protected void cleanup(Context context) throws IOException, InterruptedException {

//             Map sortedMap = sortByValues(countMap);

//             int counter = 0;
//             for (Text key: sortedMap.keySet()) {
//                 if (counter ++ == 20) {
//                     break;
//                 }
//                 context.write(key, sortedMap.get(key));
//             }
//       }
// }
// We override two methods: reduce() and cleanup(). Let's examine the reduce() method. 
// As we've seen in the mapper's code, the keys the reducer receive are every single word contained in the book; at the beginning of the method, we compute the sum of all the values received from the mappers for this key, which is the number of occurrences of this word inside the book; then we put the word and the number of occurrences into a HashMap. Note that we're not directly putting into the map the Text object that contains the word because that instance is reused many times by Hadoop for performance issues; instead, we put a new Text object based on the received one. 

// To output the top-n values, we have to compute the number of occurrences of every word, sort the words by the number of occurrences and then extract the first n. In the reduce() method we don't write any value to the output, because we can sort the words only after that we collect them all; the cleanup() method is called by Hadoop after the reducer has received all its data, so we override this method to be sure that our HashMap is filled up with all the words. 
// Let's look at the method: first we sort the HashMap by values (using code from this post); then we loop over the keyset and output the first 20 items. 

// The complete code is available on my github. 

// The output of the reducer gives us the 20 most used words in Flatland:
// the 2286
// of 1634
// and 1098
// to 1088
// a 936
// i 735
// in 713
// that 499
// is 429
// you 419
// my 334
// it 330
// as 322
// by 317
// not 317
// or 299
// but 279
// with 273
// for 267
// be 252
// Predictably, the most used words in the book are articles, conjunctions, adjectives, prepositions and personal pronouns. 

// This MapReduce program is not very efficient: the mappers will transfer to the reducers a lot of data; every single word of the book will be emitted to reducers together with the number "1", causing a very high network load; the phase in which mappers send data to the reducers is called "Shuffle and sort" and is explained in more detail in the free chapter of the "Hadoop, the definitive guide" by Tom White. 

// In the next posts we'll see how to improve the performances of the Shuffle and sort phase. 