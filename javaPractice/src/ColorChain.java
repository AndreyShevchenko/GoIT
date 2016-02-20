import java.util.HashMap;
import java.util.Map;

/**
 * Created by Андрей Шевченко on 20.02.2016.
 */
public class ColorChain {
    Map<Integer, Integer> values = new HashMap<>();
    {
        values.put(-2, 0);
        values.put(-1, 0);
        values.put(0, 0);
        values.put(1, 1);
        values.put(2, 2);
        values.put(3, 4);
    }

    public int count(int N) {
        int white;
        int yellow;
        int red;
        if (values.containsKey(N - 3)) {
            white = values.get(N - 3);
        } else {
            white = count(N - 3);
        }
        if (values.containsKey(N - 2)) {
            yellow = values.get(N - 2);
        } else {
            yellow = count(N - 2);
        }
        if (values.containsKey(N - 1)) {
            red = values.get(N - 1);
        } else {
            red = count(N - 1);
        }
        int result = white + yellow + red;
        if (!values.containsKey(N)) {
            values.put(N, result);
        }
        return values.get(N);
    }
}
