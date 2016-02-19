/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
class AddBinary {
    private int flag;

    private int getBit(String s, int i) {
        if (i <= s.length()) {
            return Character.getNumericValue(s.charAt(s.length() - i));
        } else {
            return 0;
        }
    }

    private int addBit(int a, int b) {
        int result = (a ^ b) ^ flag;
        if (a + b + flag > 1) {
            flag = 1;
        }
        return result;
    }

    String add(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = 1;
        while (i <= a.length() || i <= b.length()) {
            result.append(addBit(getBit(a, i), getBit(b, i)));
            i++;
        }
        if (flag == 1) {
            result.append(flag);
        }
        return result.reverse().toString();
    }
}
