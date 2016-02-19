import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Андрей Шевченко on 06.02.2016.
 */
public class MainClass {
    public static void main(String[] args) {
        int size = 2;
        Module36 a = new Module36(size);
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
