/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class AbcNumber {

    public int convert(String num) {
        int result = 0;
        for (int i = 0; i < num.length(); i++) {
            result = result * 10 + (num.charAt(i) - 'a');
        }
        return result;
    }
}
