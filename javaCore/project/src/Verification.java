import java.util.HashSet;
import java.util.Set;

/**
 * Created by JAvengers on 01.02.2016.
 */
public class Verification {
    private final char[] allowedCharacters = {'0','1','2','3','4','5','6','7','8','9','E','+','-','*','/','.',',','(',')'};

    private StringBuilder expression;
    private final Set<Character> allowedChar = new HashSet<>();
    static final Set<Character> setOfDigits = new HashSet<>();
    static final Set<Character> setOfOperations = new HashSet<>();

    public Verification(String expression) {
        this.expression = new StringBuilder(expression);
    }

    private void initSets() {
        for (int i = 0; i < allowedCharacters.length; i++) {
            this.allowedChar.add(allowedCharacters[i]);
            if (i < 11) {
                setOfDigits.add(allowedCharacters[i]);
            }
            if (i > 10 && i < 15) {
                setOfOperations.add(allowedCharacters[i]);
            }
        }
    }

    private void getRightExpression() {
        boolean flag = false;
        int counterSigns = 0;
        char symbol;
        for (int i = 0; i < expression.length(); i++) {
            symbol = expression.charAt(i);
            if (symbol == ',') {
                expression.replace(i, i + 1, ".");
            }
            if (!allowedChar.contains(symbol)) {
                expression.deleteCharAt(i);
                i--;
            }
            if ((setOfOperations.contains(symbol) || symbol == '(' || symbol == ')') && flag) {
                expression.insert(i, ')');
                i++;
                flag = false;
            }
            if (setOfOperations.contains(symbol)) {
                counterSigns++;
                if (setOfOperations.contains(expression.charAt(i - 1)) && symbol == '-') {
                    expression.insert(i, '(');
                    i++;
                    flag = true;
                }
            }
        }
        if (flag) {
            expression.insert(expression.length(), ')');
        }
        if (expression.length() <= 2 || counterSigns == 0) {
            throw new StringIndexOutOfBoundsException();
        }
    }

    private void checkBeginExpression() throws IncorrectLocationException {
        final char symbol = expression.charAt(0);
        if (setOfOperations.contains(symbol) || symbol == '.' || symbol == ')' || symbol == 'E') {
            if (symbol != '-') {
                throw new IncorrectLocationException("Expression must start with symbols ('(' or '-') or digit");
            }
        }
    }

    private void checkEndExpression() throws IncorrectLocationException {
        final char symbol = expression.charAt(expression.length() - 1);
        if (setOfOperations.contains(symbol) || symbol == '.' || symbol == '(' || symbol == 'E') {
            throw new IncorrectLocationException("Expression must finish with symbol ')' or digit");
        }
    }

    private void checkNumberParentheses() throws IncorrectLocationException {
        int openParentheses = 0;
        int closeParentheses = 0;
        char symbol;
        for (int i = 0; i < expression.length(); i++) {
            symbol = expression.charAt(i);
            if (symbol == '(') {
                openParentheses++;
            }
            if (symbol == ')') {
                closeParentheses++;
            }
            if (closeParentheses > openParentheses) {
                throw new IncorrectLocationException("It's must be the open parentheses first");
            }
        }
        if (openParentheses != closeParentheses) {
            throw new IncorrectLocationException("Expression must have the same number of parentheses");
        }
    }

    private void checkContentParentheses() throws IncorrectLocationException {
        boolean check = true;
        char symbol;
        for (int i = 0; i < expression.length(); i++) {
            symbol = expression.charAt(i);
            if (symbol == '(') {
                check = false;
            }
            if (setOfOperations.contains(symbol)) {
                check = true;
            }
            if (symbol == ')' && !check) {
                throw new IncorrectLocationException("It's must be an expression for calculating or negative number " +
                        "between two parentheses");
            }
        }
    }

    private void checkComma() throws IncorrectLocationException {
        for (int i = 0; i < expression.length() - 1; i++) {
            if (expression.charAt(i) == '.' && !setOfDigits.contains(expression.charAt(i + 1))) {
                    throw new IncorrectLocationException("It's must be a digit after comma");
                }
        }
    }

    private void checkOperation() throws IncorrectLocationException {
        char symbol;
        for (int i = 0; i < expression.length() - 1; i++) {
            symbol = expression.charAt(i + 1);
            if (setOfOperations.contains(expression.charAt(i)) || expression.charAt(i) == '(') {
                if ((setOfOperations.contains(symbol) && symbol != '-') || symbol == ')' || symbol == '.' || symbol == 'E') {
                    throw new IncorrectLocationException("It's must be a digit or open parentheses after operation " +
                            "or open parentheses");
                }
            }
        }
    }

    private void checkCloseParentheses() throws IncorrectLocationException {
        char symbol;
        for (int i = 0; i < expression.length() - 1; i++) {
            symbol = expression.charAt(i + 1);
            if (expression.charAt(i) == ')') {
                if (symbol == '.' || symbol == 'E') {
                    throw new IncorrectLocationException("A comma or symbol 'E' can not be after close parentheses");
                }
                if (symbol == '(' || setOfDigits.contains(symbol)) {
                    expression.insert(i + 1, '*');
                }
            }
        }
    }

    private void checkE() throws IncorrectLocationException {
        char symbol;
        for (int i = 0; i < expression.length() - 1; i++) {
            symbol = expression.charAt(i + 1);
            if (expression.charAt(i) == 'E') {
                if (!(setOfDigits.contains(symbol) || symbol == '-')) {
                    throw new IncorrectLocationException("It's must be only digit or symbol '-' after character 'E'");
                }
            }
        }
    }

    private void checkDigit() {
        for (int i = 0; i < expression.length() - 1; i++) {
            if (setOfDigits.contains(expression.charAt(i))) {
                if (expression.charAt(i + 1) == '(') {
                    expression.insert(i + 1, '*');
                }
            }
        }
    }

    public StringBuilder finalVerification() {
        initSets();
        try {
            getRightExpression();
            checkBeginExpression();
            checkEndExpression();
            checkNumberParentheses();
            checkContentParentheses();
            checkComma();
            checkOperation();
            checkCloseParentheses();
            checkE();
            checkDigit();
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println(("[Error]: Expression defies calculation"));
            throw new ArithmeticException();
        } catch (IncorrectLocationException ex) {
            System.out.println("[Error]: Incorrect location of the characters in the expression");
            throw new ArithmeticException();
        }
        return expression;
    }

}
