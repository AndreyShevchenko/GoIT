import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAvengers on 01.02.2016.
 */
public class Logic {
    private StringBuilder expression;
    private List<BigDecimal> numbers;
    private List<Character> operations;

    Logic() {}

    public Logic(StringBuilder expression) {
        this.expression = expression;
    }

    private void getNumbersAndOperations(StringBuilder someExpression) {
        int startPosition = 0;
        char symbol;
        numbers = new ArrayList<>();
        operations = new ArrayList<>();
        for (int i = 0; i < someExpression.length(); i++) {
            symbol = someExpression.charAt(i);
            if (Verification.setOfOperations.contains(symbol) && i != startPosition) {
                if (!((symbol == '-' || symbol == '+') && someExpression.charAt(i - 1) == 'E')) {
                    operations.add(symbol);
                    numbers.add(new BigDecimal(someExpression.substring(startPosition, i)));
                    startPosition = i + 1;
                }
            }
        }
        numbers.add(new BigDecimal(someExpression.substring(startPosition, someExpression.length())));
    }

    public BigDecimal handlingParentheses() {
        StringBuilder output = new StringBuilder(expression);
        StringBuilder temp;
        BigDecimal result;
        int openPar = 0;
        int i = 0;
        do {
            if (expression.charAt(i) == '(') {
                openPar = i;
            }
            if (expression.charAt(i) == ')') {
                temp = new StringBuilder(expression.substring(openPar + 1, i));
                getNumbersAndOperations(temp);
                expression.delete(openPar, i + 1);
                expression.insert(openPar, makeOperations().toString());
                i = -1;
            }
            i++;
        } while (i != expression.length());
        getNumbersAndOperations(expression);
        result = makeOperations().setScale(InputOutput.getPrecision(),  BigDecimal.ROUND_HALF_UP);
        InputOutput.printResult(result, output);
        return result;
    }

    private BigDecimal makeOperations() {
        boolean isFirstOperations = true;
        boolean isIgnore = false;
        int i = -1;
            do {
                i++;
                isIgnore = ((operations.get(i) == '+' || operations.get(i) == '-') && isFirstOperations);
                if (!isIgnore) {
                    numbers.add(i, makeSimpleOperation(operations.get(i).toString(), numbers.get(i), numbers.get(i + 1)));
                    operations.remove(i);
                    numbers.remove(i + 1);
                    numbers.remove(i + 1);
                    i--;
                }
                if (i == operations.size() - 1) {
                    isFirstOperations = false;
                    i = -1;
                }
            } while (i != operations.size() - 1);
        return numbers.get(0);
    }

    BigDecimal makeSimpleOperation(String typeOperation, BigDecimal operand1, BigDecimal operand2) {
        Operations makeOperation = new Operations();
        switch (typeOperation) {
            case "+":
                operand1 = makeOperation.add(operand1, operand2);
                break;
            case "-":
                operand1 = makeOperation.subtract(operand1, operand2);
                break;
            case "*":
                operand1 = makeOperation.multiply(operand1, operand2);
                break;
            case "/":
                try {
                    operand1 = makeOperation.divide(operand1, operand2);
                    // catch division by 0
                } catch (DivisionByZeroException ex) {
                    throw new ArithmeticException();
                }
                break;
        }
        return operand1;
    }
}
