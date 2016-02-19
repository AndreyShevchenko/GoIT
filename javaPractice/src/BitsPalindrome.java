/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class BitsPalindrome {

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

    public boolean isPalindrome(int input) {
        int result;
        int i = 1;
        int maskStart = (1 << 31) + 1;
        if (input == 0 || input == -(Math.pow(2, 32) - 1)) return true;
        if (input > 0 && input < (Math.pow(2, 16) + Math.pow(2, 15))) return false;
        int mask = maskStart;
        do {
            result = input & mask;
            if (count(result) == 1) return false;
            else {
                mask = maskStart >>> i;
                mask += Math.pow(2, i);
                i++;
            }
        } while (i < 16);
        return true;
    }
}
