/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class AddNumberBase36 {
    private int flag;

    private int charToInt(char a) {
        if (a > 96) return a - 87;
        else return Character.getNumericValue(a);
    }

    private char intToChar(int a) {
        if (a > 9) return (char) ('a' + a - 10);
        else return (char) ('0' + a);
    }

    private int adding(char a, char b) {
        int result = charToInt(a) + charToInt(b) + flag;
        if (result > 35) {
            flag = 1;
            result -= 36;
        } else flag = 0;
        return result;
    }

    private String makeEqual(String a, String b) {
        String c = "";
        int dif = Math.abs(a.length() - b.length());
        for (int i = 0; i < dif; i++) {
            c = c.concat("0");
        }
        return c.concat(b);
    }

    public String add(String a, String b) {
        StringBuilder result = new StringBuilder();
        if (a.length() > b.length()) b = makeEqual(a, b);
        else a = makeEqual(b, a);
        for (int i = a.length() - 1; i >= 0; i--) {
            result.insert(0, intToChar(adding(a.charAt(i), b.charAt(i))));
        }
        if (flag == 1) result.insert(0, '1');
        return result.toString();
    }
}