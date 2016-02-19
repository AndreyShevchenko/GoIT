import java.math.BigDecimal;

/**
 * Created by JAvengers on 28.01.2016.
 * class that directly implements the logic of the calculator
 * @see BigDecimal
 * @see InputOutput
 * @see Operations
 * @see DivisionByZeroException
 */
class Calculator {
    Logic logic = new Logic();

    void simpleCalculate() {
        BigDecimal result;
        BigDecimal operand;
        String typeOperation;
        StringBuilder expression = new StringBuilder();
        boolean isWrongExit = false;
        do {
            do {
                result = InputOutput.inputOperand(expression);
                typeOperation = InputOutput.inputOperation(expression);
                if (typeOperation.equals("=")) {
                    System.out.println("Result = " + result);
                    expression.delete(0, expression.length());
                }
            } while (typeOperation.equals("="));
            do {
                operand = InputOutput.inputOperand(expression);
                try {
                    result = logic.makeSimpleOperation(typeOperation, result, operand);
                } catch (ArithmeticException ex) {
                    typeOperation = "=";
                    isWrongExit = true;
                }
                if (!typeOperation.equals("=")) {
                    typeOperation = InputOutput.inputOperation(expression);
                }
            } while (!typeOperation.equals("="));
            if (!isWrongExit) {
                InputOutput.printResult(result, expression);
                expression.delete(0, expression.length());
            }
        } while (InputOutput.moreCalculation());
    }

    void expressionCalculate() {
        do {
            Verification expression = new Verification(InputOutput.inputExpression());
            try {
                logic = new Logic(expression.finalVerification());
                logic.handlingParentheses();
            } catch (ArithmeticException ex) {
                System.out.println("[Error]: Operation can not be performed");
            }

        } while (InputOutput.moreCalculation());
    }

}
