/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class JoinCharacters {

    public int join(char[] input) {
        int result = 0;
        for (char anInput : input) {
            result = result * 10 + (anInput - '0');
        }
        return result;
    }
}
