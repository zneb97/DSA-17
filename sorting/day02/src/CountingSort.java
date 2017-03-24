public class CountingSort {

    static void countingSort(int[] A) {
        //Get max
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < A.length; i++) {
            if(A[i] > max){
                max = A[i];
            }
        }

        int maxInt = (int)max;
        //Create and populate counts array
        int[] counts = new int[maxInt+1];
        for (int j = 0; j < A.length; j++) {
            counts[A[j]]++;
        }

        //Set original array
        int index = 0;
        for (int k = 0; k < maxInt+1; k++) {
            while(counts[k] > 0){
                A[index] = k;
                index++;
                counts[k]--;
            }
        }


    }

}
