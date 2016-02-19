import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class FirstUniqueCharacter {

    public Character find(String s) {
        Set<Character> all = new LinkedHashSet<>();
        Set<Character> wrong = new HashSet<>();
        if (s.length() == 0) {
            return null;
        } else {
            if (s.length() == 1) {
                return s.charAt(0);
            } else {
                for (int i = 0; i < s.length(); i++) {
                    if (all.contains(s.charAt(i))) {
                        wrong.add(s.charAt(i));
                    } else {
                        all.add(s.charAt(i));
                    }
                }
                all.removeAll(wrong);
                if (all.size() == 0) {
                    return null;
                } else {
                    return all.iterator().next();
                }
            }
        }
    }
}
