/**
 * Created by Андрей Шевченко on 08.02.2016.
 */
public class BinaryHeap {
    private int[] heap;
    private int currentSize;

    public BinaryHeap(int size) {
        heap = new int[size];
        currentSize = 0;
    }

    private void swapTwoElements(int i, int j) {
        heap[i] -= heap[j];
        heap[j] += heap[i];
        heap[i] = heap[j] - heap[i];
    }

    private int liftMaxElementToTop(int i) {
        int leftNode = 2 * i;
        int rightNode = 2 * i + 1;
        int maxValue = i;
        if (leftNode <= currentSize) {
            if (heap[i - 1] < heap[leftNode - 1]) {
                maxValue = leftNode;
            }
        }
        if (rightNode <= currentSize) {
            if (heap[maxValue - 1] < heap[rightNode - 1]) {
                maxValue = rightNode;
            }
        }
        if (maxValue != i) {
            swapTwoElements(maxValue - 1, i - 1);
        }
        return maxValue;
    }

    private void makeBiggerHeap() {
        int[] temp = new int[heap.length * 2];
        System.arraycopy(heap, 0, temp, 0, heap.length);
        heap = temp;
    }

    public void insert(int val) {
        if (currentSize >= heap.length) {
            makeBiggerHeap();
        }
        heap[currentSize] = val;
        currentSize++;
        if (currentSize > 1) {
            for (int i = currentSize / 2; i >= 1; i /= 2) {
                liftMaxElementToTop(i);
            }
        }
    }

    public int poll() {
        int maxNode;
        int root = heap[0];
        heap[0] = heap[currentSize - 1];
        currentSize--;
        if (currentSize > 1) {
            int i = 1;
            while (i < currentSize) {
                maxNode = liftMaxElementToTop(i);
                if (i == maxNode) {
                    i = currentSize;
                } else {
                    i = maxNode;
                }
            }
        }
        return root;
    }

}
