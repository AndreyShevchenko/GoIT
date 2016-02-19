import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by JAvengers on 28.01.2016.
 * class contains methods for working with the user and implements keyboard input various data and
 * displaying the supplemental information and results of the program
 * @see BigDecimal
 * @see Scanner
 * @see java.io.BufferedReader
 * @see java.io.FileReader
 */
public class InputOutput {
    private static int precision = 3;

    public static void main(String[] args){
        InputOutput io = new InputOutput();
        io.startCalculator();
    }

    private void inputPrecision() {
        boolean isRightInput = false;
        StringBuilder someString = new StringBuilder();
        System.out.print("Input of calculation accuracy. ");
        do {
            precision = inputOperand(someString).intValue();
            if (precision >= 0 && precision <= 20) {
                isRightInput = true;
            }
            else {
                System.out.println("[Error]: invalid calculation precision, please re-enter");
            }
        } while (!isRightInput);
    }

    static int getPrecision() {
        return precision;
    } // TODO

    /**
     * method reads the file and displays the information on the rules of work with the program
     * @param filename is the name of file with help information
     */
    private void showStartMessage(String filename) {
        String temp;
        try(BufferedReader in = new BufferedReader(new FileReader(filename))) {
            // reading information line by line and printing it on the display
            while ((temp = in.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("[Error]: File with name \"" + filename + "\" does not exist. Help information not available.");
        } catch (IOException ex) {
            System.out.println("[Error]: Help information was damaged");
        }
    }

    /**
     * method is printing a message about the official end of the program
     */
    private void showFinalMessage() {
        System.out.println("Thank you for using calculator!");
    }

    /**
     * method makes a request to the user, whether he wants to start calculation, or exit the program
     * @return true - for start calculation, false - for exit the program
     */
    private void startCalculator() {
        String choice; // variable for storing the entered data
        Calculator calc = new Calculator();
        showStartMessage("terms.txt");
        do {
            System.out.println("Please select action: (\"s\" - start simplyCalc, \"i\" - start expressionCalc," +
                    " \"p\" - set precision, \"e\" - exit)? (s/i/p/e) = > ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.next();
            if (choice.equals("s") || choice.equals("i") || choice.equals("p") || choice.equals("e")) {
                switch (choice) {
                    case "s":
                        calc.simpleCalculate();
                        break;
                    case "i":
                        calc.expressionCalculate();
                        break;
                    case "p":
                        inputPrecision();
                        break;
                }
            }
            else {
                System.out.println("[Error]: Wrong input, please re-enter");
            }
        } while (!choice.equals("e"));
        showFinalMessage();
    }

    /**
     * method makes a request to the user, whether he wants to start calculation, or exit the program
     */
    static boolean moreCalculation() {
        String choice; // variable for storing the entered data
        boolean isRightInput = false; // variable, which is responsible for verifying the accuracy of the entered data
        System.out.println("Do you want to start new calculation? (y/n) = > ");
        // unit for reading entered data
        do {
            Scanner scanner = new Scanner(System.in);
            choice = scanner.next();
            if (choice.equals("y") || choice.equals("n")) {
                isRightInput = true;
            }
            else {
                System.out.println("[Error]: Wrong input, please re-enter");
            }
        } while (!isRightInput);
        return choice.equals("y");
    }

    /**
     * method for entering number by user
     * @return number entered by user
     */
    static BigDecimal inputOperand(StringBuilder expression) {
        boolean isRightInput = false; // variable, which is responsible for verifying the accuracy of the entered data
        BigDecimal operand = new BigDecimal(0);
        // unit for reading entered data
        do {
            try {
                System.out.println("Enter new number => ");
                Scanner scanner = new Scanner(System.in);
                operand = scanner.nextBigDecimal();
                isRightInput = true;
            } catch (InputMismatchException ex) {
                System.out.println("[Error]: The data entered is not a number, please re-enter");
            }
        } while (!isRightInput);
        recordExpression(expression, operand);
        return operand;
    }

    /**
     * method for entering arithmetical operation by user
     * @return type of arithmetical operation entered by user
     */
    static String inputOperation(StringBuilder expression) {
        boolean isRightInput = false; // variable, which is responsible for verifying the accuracy of the entered data
        String[] possibleOperations = {"+", "-", "*", "/", "="}; // array for permissible arithmetical operations
        String typeOperation; // variable to store the entered data
        // unit for reading entered data
        do {
            System.out.println("Specify operation => ");
            Scanner scanner = new Scanner(System.in);
            typeOperation = scanner.next();
            // check the correctness of the entered data
            for (String possibleOperation : possibleOperations) {
                if (typeOperation.equals(possibleOperation)) {
                    isRightInput = true;
                }
            }
            if (!isRightInput) {
                System.out.println("[Error]: The wrong type of operation, please re-enter");
            }
        } while (!isRightInput);
        recordExpression(expression, typeOperation);
        return typeOperation;
    }

    static String inputExpression() {
        System.out.println("Enter the calculation expression =>");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    static StringBuilder recordExpression(StringBuilder expression, Object add) {
        String exp = add.toString();
        if (exp.compareTo("*") == 0 || exp.compareTo("/") == 0) {
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                    expression.insert(0, "(");
                    expression.append(")");
                    i = expression.length();
                }
            }
            expression.append(exp);
        } else {
            if (exp.charAt(0) == '-' && exp.length() > 1) {
                expression.append("(");
                expression.append(exp);
                expression.append(")");
            } else {
                if (!(exp.compareTo("=") == 0)) {
                    expression.append(exp);
                }
            }
        }
        return expression;
    }

    /**
     * method is printing to display the result of performed operations
     * @param result of performed operations
     */
    static void printResult(BigDecimal result, StringBuilder expression) {
        System.out.println("Result: " + expression + " = " + result.setScale(getPrecision(), BigDecimal.ROUND_HALF_UP));
    }

}
