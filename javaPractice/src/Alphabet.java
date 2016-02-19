import java.util.HashSet;
import java.util.Set;

/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class Alphabet {

    public boolean check(String input) {
        Set<Character> inputSet = new HashSet<>();
        input = input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            inputSet.add(input.charAt(i));
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (!inputSet.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
