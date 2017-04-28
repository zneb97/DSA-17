import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size()/2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size()/2-1))/2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        PriorityQueue<Integer> high = minPQ();
        PriorityQueue<Integer> low = maxPQ();
;
        for (int i = 0; i < inputStream.length; i++) {
            //First value
            if(i == 0){
                low.offer(inputStream[i]);
                runningMedian[i] = inputStream[i];
            }

            else {
                //Set Values to correct queues
                if ((high.peek() == null) || (high.peek() > inputStream[i])) {
                    low.offer(inputStream[i]);
                }
                else {
                    high.offer(inputStream[i]);
                }

                //Readjust sizes of queues
                if (low.size() > high.size() + 1){
                    high.offer(low.poll());
                }

                else if (low.size() < high.size()) {
                    low.offer(high.poll());
                }

                //Put in
                if (low.size() > high.size())
                    runningMedian[i] = low.peek();
                else
                    runningMedian[i] = (low.peek() + high.peek()) / 2.0;
            }
        }

        return runningMedian;
    }

}
