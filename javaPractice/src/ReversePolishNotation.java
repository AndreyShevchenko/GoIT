/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class ReversePolishNotation {

    private int[] findExpression(StringBuilder expression, int i) {
        int[] index = new int[3];
        index[2] = i + 3;
        int counter = 1;
        while (counter > -1) {
            if (i < 0) {
                index[0] = 0;
                counter = -1;
            } else {
                if (expression.charAt(i) == ' ') {
                    index[counter] = i + 1;
                    counter--;
                }
            }
            i--;
        }
        return index;
    }

    private int makeOperation(int a, int b, char operation) {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

    private String calculateExpression(StringBuilder expression, int[] index) {
        int a = Integer.valueOf(expression.substring(index[0], index[1] - 1));
        int b = Integer.valueOf(expression.substring(index[1], index[2] - 1));
        char operation = expression.charAt(index[2]);
        return Integer.toString(makeOperation(a, b, operation));
    }

    public int evaluate(String expression) {
        StringBuilder exp = new StringBuilder(expression);
        String temp = "";
        int[] index = new int[3];
        int result = 0;
        int i = 0;
        while (i < exp.length()) {
            if (exp.charAt(i) < 48 && exp.charAt(i) != ' ') {
                index = findExpression(exp, i - 3);
                temp = calculateExpression(exp, index);
                exp = exp.replace(index[0], index[2] + 1, temp);
                i = 0;
            } else i++;
        }
        return Integer.valueOf(exp.toString());
    }
}
