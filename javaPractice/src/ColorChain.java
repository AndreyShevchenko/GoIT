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
        int result = 0;
        for (int i = 1; i < 4; i++) {
            if (values.containsKey(N - i)) {
                result += values.get(N - i);
            } else {
                result += count(N - i);
            }
        }
        if (!values.containsKey(N)) {
            values.put(N, result);
        }
        return values.get(N);
    }
}
