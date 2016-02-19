/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class FindMaxNumber {

    public int max(int[] input) {
        int maxValue = input[0];
        for (int i = 1; i < input.length; i++) {
            if (input[i] > maxValue) {
                maxValue = input[i];
            }
        }
        return maxValue;
    }
}