/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class SumDigits {

    public int sum(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number = number / 10;
        }
        return Math.abs(sum);
    }
}