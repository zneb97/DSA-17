public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Recursively corrects the position of element indexed i: check children, and swap with larger child if necessary.
    public void heapify(int i) {
        int temp = 0;
        if((leftChild(i)<size)&&(rightChild(i)<size)){
                if(heap[leftChild(i)] >= heap[rightChild(i)]) {
                    if(heap[leftChild(i)] > heap[i]){ //Left bigger
                        temp = heap[i];
                        heap[i] = heap[leftChild(i)];
                        heap[leftChild(i)] = temp;
                        heapify(leftChild(i));
                    }
                }
                else {
                    if (heap[rightChild(i)] > heap[i]) { //Right bigger
                        temp = heap[i];
                        heap[i] = heap[rightChild(i)];
                        heap[rightChild(i)] = temp;
                        heapify(rightChild(i));
                    }
                }

        }
        if(leftChild(i)< size) {
            if (heap[i] < heap[leftChild(i)]) {
                temp = heap[i];
                heap[i] = heap[leftChild(i)];
                heap[leftChild(i)] = temp;
                heapify(leftChild(i));
            }
        }


    }


    // Given the array, build a heap by correcting every non-leaf's position.
    public void buildHeapFrom(int[] array) {
        this.heap = array;
        this.size = array.length;
        for(int i = size/2; i >=0; i--){
            heapify(i);
        }
    }

    /**
     * Best-case runtime: O(n log n)
     * Worst-case runtime: O(n log n)
     * Average-case runtime: O(n log n)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        int temp = 0;
        buildHeapFrom(array);
        for (int i=size-1; i>0; i--) {
            temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;
            size--; //Cut off root
            heapify(0);
        }
        return heap;
    }
}
