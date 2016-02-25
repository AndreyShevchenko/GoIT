import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Андрей Шевченко on 06.02.2016.
 */
public class MainClass {
    public static void main(String[] args) {
        /*long lBegin = System.currentTimeMillis();
        Doubles3 a = new Doubles3();
        System.out.println(a.tryParse("e10"));
        long lEnd = System.currentTimeMillis();
        System.out.println(lEnd - lBegin);*/
        int[] b = {-2147483648, -1, 2, 2147483647};
        BinarySearch a = new BinarySearch();
        System.out.println(a.find(b, -2147483648));
        Integer d = -2147483648;
        System.out.println(Integer.MIN_VALUE);

    }
}
