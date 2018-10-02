
// 607. Two Sum III - Data structure design
// Design and implement a TwoSum class. It should support the following operations: add and find.

// add - Add the number to an internal data structure.
// find - Find if there exists any pair of numbers which sum is equal to the value.

// Example
// add(1); add(3); add(5);
// find(4) // return true
// find(7) // return false

public class TwoSum {
    /**
     * @param number: An integer
     * @return: nothing
     */
    List<Integer> list = new ArrayList();
    public void add(int number) {
        list.add(number);
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        Collections.sort(list);
        int s = 0 , e = list.size() - 1;
        while(s < e){
            if(list.get(s) + list.get(e) == value){
                return true;
            }
            else if(list.get(s) + list.get(e) < value){
                s ++;
            }
            else{
                e --;
            }
        }
        return false;
    }
}