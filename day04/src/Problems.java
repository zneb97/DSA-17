import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Problems {

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        MyLinearMap counts = new MyLinearMap();

        for(int i = 0; i < arr.length; i++){
            //If exists already, increment
            if(counts.containsKey(arr[i])){
                counts.put(arr[i], (int)counts.get(arr[i])+1);
            }
            else{
                counts.put(arr[i], 1);
            }
        }
        return counts;
    }

    public static List<Integer> removeKDigits(int[] num, int k) {
        List<Integer> result = new ArrayList<>();

        //Move data to workable list
        for(int i = 0; i < num.length; i++){
            result.add(num[i]);
        }

        //Go right until the next element is smaller than the current one. Remove current
        int i = 0;
        while((i < result.size()-1) && (k > 0)){
            if(result.get(i+1) <= result.get(i)){
                result.remove(i);

                //Reset i so as not to miss shifted values, decrement k
                i=-1;
                k--;
            }

            //If iterates through the entire array, cut the last
            if(i == result.size()-2){
                result.remove(result.size()-1);

                //Reset i so as not to miss shifted values, decrement k
                i=-1;
                k--;
            }
            i++;

        }
        return result;
    }

    public static int sumLists(Node<Integer> h1, Node<Integer> h2) {
        int length1 = -1;
        int length2 = -1;
        int sum1 = 0;
        int sum2 = 0;
        Node<Integer> saveHead1 = h1;
        Node<Integer> saveHead2 = h2;

        //Get lengths of each list
        while(h1 != null){
            length1++;
            h1 = h1.next;
        }
        while(h2 != null){
            length2++;
            h2 = h2.next;
        }

        //Sum each element of each list, multiplied by its corresponded 10^ place
        for(int i = length1; i >= 0; i--){
            sum1 += (saveHead1.data * (Math.pow(10,i)));
            saveHead1 = saveHead1.next;
        }
        for(int j = length2; j >= 0; j--){
            sum2 += (saveHead2.data * (Math.pow(10,j)));
            saveHead2 = saveHead2.next;
        }

        return (int)(sum1+sum2);
    }

}
