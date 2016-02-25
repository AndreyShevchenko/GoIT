public class Doubles2 {
    private int iterator = 0;

    public Double tryParse(String s) {
        int sign;
        int signExp = 1;
        StringBuilder integ = new StringBuilder();
        StringBuilder decim = new StringBuilder();
        StringBuilder exp = new StringBuilder();
        s = s.concat("\n");
        sign = getSign(s);
        while (iterator < s.length()) {
            if (isPoint(s.charAt(iterator))) {
                iterator++;
                if (integ.length() == 0 && !isDigit(s.charAt(iterator))) return null;
                getNumber(s, decim);
            }
            if (isExp(s.charAt(iterator))) {
                iterator++;
                signExp = getSign(s);
                getNumber(s, exp);
            }
            getNumber(s, integ);
            if (s.charAt(iterator) == '\n') {
                iterator = s.length();
            }
        }
        return (getInteger(sign, integ) + getDecimal(sign, decim)) * Math.pow(10, getInteger(signExp, exp));
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

    private StringBuilder getNumber(String s, StringBuilder str) {
        while (isDigit(s.charAt(iterator))) {
            str.append(s.charAt(iterator));
            iterator++;
        }
        return str;
    }

    private double getInteger(int sign, StringBuilder s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result = result * 10 + Character.digit(s.charAt(i), 10);
        }
        return result * sign;
    }

    private double getDecimal(int sign, StringBuilder s) {
        double result = 0;
        int degree = -1;
        for (int i = 0; i < s.length(); i++) {
            result += Math.pow(10, degree) * Character.digit(s.charAt(i), 10);
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
