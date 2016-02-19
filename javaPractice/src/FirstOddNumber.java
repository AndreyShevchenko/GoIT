/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class FirstOddNumber {

    public int find(int[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] % 2 == 1)
                return i;
        }
        return -1;
    }
}
