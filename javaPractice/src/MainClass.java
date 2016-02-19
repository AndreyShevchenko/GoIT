import java.util.Random;

/**
 * Created by Андрей Шевченко on 06.02.2016.
 */
public class MainClass {
    public static void main(String[] args) {
        int size = 2;
        BinaryHeap a = new BinaryHeap(size);
        Random random = new Random();
        int[] x = {0, 1, 2};
        for (int i = 0; i < x.length; i++) {
            a.insert(x[i]);
        }
        a.printTree();
        System.out.println();
        System.out.println(a.poll());


    }
}
