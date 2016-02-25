public class Doubles2 {
    private int iterator = 0;

    public Double tryParse(String s) {
        int sign;
        double result = 0;
        s = s.concat("\n");
        sign = getSign(s);
        while (iterator < s.length()) {
            result += getInteger(s, sign);
            if (isPoint(s.charAt(iterator))) {
                iterator++;
                if (result == 0 && !isDigit(s.charAt(iterator))) return null;
                result += getDecimal(s, sign);
            }
            if (isExp(s.charAt(iterator))) {
                iterator++;
                sign = getSign(s);
                result *= Math.pow(10, getInteger(s, sign));
            }
            if (s.charAt(iterator) == '\n') {
                iterator = s.length();
            }
        }
        return result;
    }

    private int getSign(String s) {
        if (isSign(s.charAt(iterator))) {
            iterator++;
            if (s.charAt(iterator - 1) == '-') {
                return -1;
            }
        }
        return 1;
    }

    private double getInteger(String s, int sign) {
        int result = 0;
        while (isDigit(s.charAt(iterator))) {
            result = result * 10 + Character.digit(s.charAt(iterator), 10);
            iterator++;
        }
        return result * sign;
    }

    private double getDecimal(String s, int sign) {
        double result = 0;
        int degree = -1;
        while (isDigit(s.charAt(iterator))) {
            result += Math.pow(10, degree) * Character.digit(s.charAt(iterator), 10);
            iterator++;
            degree--;
        }
        return result * sign;
    }

    private boolean isDigit(char symbol) {
        return (symbol - '0' >= 0 && symbol - '0' <= 9);
    }

    private boolean isSign(char symbol) {
        return (symbol == '+' || symbol == '-');
    }

    private boolean isPoint(char symbol) {
        return symbol == '.';
    }

    private boolean isExp(char symbol) {
        return (symbol == 'E' || symbol == 'e');
    }

}
