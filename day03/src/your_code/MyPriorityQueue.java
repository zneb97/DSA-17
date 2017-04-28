package your_code;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
    ArrayList<Integer> queue = new ArrayList<>();
    int size = 0;

    public void enqueue(int item) {
        queue.add(item);
        size++;
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else{
            int max = 0;
            int index = 0;
            for (int i = 0; i < size; i++) {
                if (queue.get(i) > max) {
                    max = queue.get(i);
                    index = i;
                }
            }
            queue.remove(index);
            size--;
            return max;
        }
    }

}