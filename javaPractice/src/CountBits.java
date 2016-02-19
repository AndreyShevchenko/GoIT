/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class CountBits {

    public int count(int num) {
        int mask = 1;
        int temp;
        int result = 0;
        do {
            temp = num | mask;
            if (temp == num) {
                result += 1;
            }
            num = num >>> 1;
        } while (num != 0);
        return result;
    }
}