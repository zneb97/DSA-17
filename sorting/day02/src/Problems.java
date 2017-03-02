import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        //Or use min finding as part of count sort
        CountingSort countsort = new CountingSort();
        for(int i = 0; i < A.length; i++){
            A[i] += 100;
        }
        countsort.countingSort(A);
        for(int j = 0; j < A.length; j++){
            A[j] -= 100;
        }
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        int base = 26;
        LinkedList<String>[] counts = new LinkedList[base];
        //Initialize buckets
        for(int i =0; i < base; i++){
            counts[i] = new LinkedList();
        }
        for(String word : A){
            int digit = getNthCharacter(word, n);
            counts[digit].add(word);
        }
        int j = 0;
        for(LinkedList<String> list : counts ){
            while(list.size()>0){
                A[j] = list.getFirst();
                j++;
                list.removeFirst();
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for (int i = 0; i < stringLength; i++) {
            countingSortByCharacter(S,i);
        }
    }

}
