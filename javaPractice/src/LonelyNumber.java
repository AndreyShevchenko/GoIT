import java.util.HashSet;
import java.util.Set;

/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class LonelyNumber {

    public int find(int[] input) {
        Set<Integer> all = new HashSet<>();
        Set<Integer> wrong = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            if (all.contains(input[i])) {
                wrong.add(input[i]);
            } else {
                all.add(input[i]);
            }
        }
        all.removeAll(wrong);
        return all.iterator().next();
    }
}
