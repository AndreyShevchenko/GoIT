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

    private void liftElementToCorrectPosition(int i, String liftMethod) {
        int leftNode = 2 * i;
        int rightNode = 2 * i + 1;
        int minValue = i;
        int maxValue = i;
        if (leftNode <= currentSize) {
            if (heap[leftNode - 1] < heap[i - 1]) {
                minValue = leftNode;
            } else {
                maxValue = leftNode;
            }
        }
        if (rightNode <= currentSize) {
            if (heap[rightNode - 1] < heap[minValue - 1]) {
                minValue = rightNode;
            } else {
                if (heap[rightNode - 1] > heap[maxValue - 1]) {
                    maxValue = rightNode;
                }
            }
        }
        switch (liftMethod) {
            case "max":
                if (maxValue != i) {
                    swapTwoElements(maxValue - 1, i - 1);
                    if (minValue == i) {
                        minValue = maxValue;
                    }
                }
                if (minValue != leftNode && leftNode <= currentSize) {
                    swapTwoElements(minValue - 1, leftNode - 1);
                }
                break;
            case "average":
                if (minValue != leftNode && leftNode <= currentSize) {
                    swapTwoElements(minValue - 1, leftNode - 1);
                    if (maxValue == leftNode) {
                        maxValue = minValue;
                    }
                }
                if (maxValue != rightNode && rightNode <= currentSize) {
                    swapTwoElements(maxValue - 1, rightNode - 1);
                }
                break;
        }
    }

    private boolean isNodeFromRightBranch(int nodeNumber) {
        double temp = Math.log(nodeNumber + 1) / Math.log(2);
        int tempRounding = (int) temp;
        return (tempRounding - temp == 0);
    }

    private void makeBiggerHeap() {
        int[] temp = new int[heap.length * 2];
        System.arraycopy(heap, 0, temp, 0, heap.length);
        heap = temp;
    }

    public void insert(int val) {
        String liftMethod;
        if (currentSize >= heap.length) {
            makeBiggerHeap();
        }
        heap[currentSize] = val;
        currentSize++;
        if (currentSize > 1) {
            for (int i = currentSize / 2; i >= 1; i /= 2) {
                if (i == 1 || isNodeFromRightBranch(i)) {
                    liftMethod = "average";
                } else {
                    liftMethod = "max";
                }
                liftElementToCorrectPosition(i, liftMethod);
            }
        }
    }

    public int poll() {
        int nodeNumber = 3;
        int result = heap[0];
        while (nodeNumber <= currentSize) {
            if (result < heap[nodeNumber - 1]) {
                result = heap[nodeNumber - 1];
            }
            nodeNumber = nodeNumber * 2 + 1;
        }
        return result;
    }

    public void printTree() {
        int i = 1;
        int index = (1 << i) - 1;
        int begin = 1;
        do {
            for (int j = begin; j <= index; j++) {
                System.out.print(heap[j - 1] + " ");
            }
            System.out.println();
            i++;
            begin = index + 1;
            index = (1 << i) - 1;
        } while (currentSize > index);
        for (int j = (1 << (i - 1)); j <= currentSize; j++) {
            System.out.print(heap[j - 1] + " ");
        }
    }
}
