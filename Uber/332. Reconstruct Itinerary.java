// 332. Reconstruct Itinerary
// Share
// Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
// All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

// Note:

// If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
//  For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
// All airports are represented by three capital letters (IATA code).
// You may assume all tickets form at least one valid itinerary.
// Example 1:

// Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
// Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
// Example 2:

// Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
// Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
// Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
//              But it is larger in lexical order.


class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList();
        if(tickets == null || tickets.length == 0) return res;  
        Map<String , List<String>> map = new HashMap();
        Map<String , List<Boolean>> visitedMap = new HashMap();
        for(String[] ticket : tickets){
            map.computeIfAbsent(ticket[0] , x -> new ArrayList()).add(ticket[1]);
            visitedMap.computeIfAbsent(ticket[0] ,x -> new ArrayList()).add(false);
        }
    
        for(List<String> list : map.values()) Collections.sort(list);
        res.add("JFK");
        if(dfs("JFK" , map , res , visitedMap , tickets.length + 1)) return res;
        return res;
    }
    private boolean dfs(String start , Map<String , List<String>> map ,  List<String> res ,Map<String , List<Boolean>> visitedMap , int target){
        if(res.size() == target) return true;
        if(map.containsKey(start)){
            for(int i = 0 ; i < map.get(start).size() ; i++){
                if(visitedMap.get(start).get(i)) continue;
                String next = map.get(start).get(i);
                res.add(next);
                visitedMap.get(start).set( i , true);
                if(dfs(next , map , res , visitedMap , target)) return true;
                visitedMap.get(start).set( i , false);
                res.remove(res.size() - 1);
            }   
        }
        return false;
    }
}


// All the airports are vertices and tickets are directed edges. Then all these tickets form a directed graph.

// The graph must be Eulerian since we know that a Eulerian path exists.

// Thus, start from "JFK", we can apply the Hierholzer's algorithm to find a Eulerian path in the graph which is a valid reconstruction.

// Since the problem asks for lexical order smallest solution, we can put the neighbors in a min-heap. In this way, we always visit the smallest possible neighbor first in our trip.

public class Solution {

    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;

    public List<String> findItinerary(String[][] tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        for (String[] ticket : tickets) {
            flights.putIfAbsent(ticket[0], new PriorityQueue<>());
            flights.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK");
        return path;
    }

    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.addFirst(departure);
    }
}