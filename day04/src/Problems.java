import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Problems {

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        MyLinearMap counts = new MyLinearMap();
        for(int i = 0; i < arr.length; i++){
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
        MyLinearMap store = new MyLinearMap();
        int max = (int)Double.NEGATIVE_INFINITY;
        for(int i = 0; i < num.length; i++){
            if(num[i]> max){
                max= num[i];
            }
            store.put(num[i], i);
        }
        return result;

    }

    public static int sumLists(Node<Integer> h1, Node<Integer> h2) {
        int length1 = 0;
        int length2 = 0;
        int sum1 = 0;
        int sum2 = 0;
        while(h1.next != null){
            length1++;
            h1 = h1.next;
        }
        while(h1.next != null){
            length2++;
            h2 = h2.next;
        }
        for(int i = length1; i >= 0; i--){
            sum1 += h1.data * (10^i);
            h1 = h1.next;
        }
        for(int i = length2; i >= 0; i++){
            sum2 += h2.data * (10^i);
            h2 = h2.next;
        }
        return sum1+sum2;
    }

}
