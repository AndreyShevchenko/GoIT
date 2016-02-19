/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class SetZero {

    public int set(int num, int i) {
        int mask = ~(1 << (i - 1));
        num = num & mask;
        return num;
    }
}
