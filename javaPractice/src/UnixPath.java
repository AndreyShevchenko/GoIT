import java.util.Stack;

/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class UnixPath {
    private boolean isLastLetter;
    private Stack<Character> temp;

    private boolean isSymbolForInput(char symbol) {
        if (isLastLetter) {
            if (symbol == '/') {
                isLastLetter = false;
            }
        } else {
            if (symbol != '.' && symbol != '/') {
                isLastLetter = true;
            } else {
                return false;
            }
        }
        return true;
    }

    private void deleteUnnecessaryFolder(int i, char symbol, String input) {
        if (symbol == '.') {
            if (input.charAt(i - 1) == '.' && temp.size() > 1) {
                temp.pop();
                while (temp.peek() != '/') {
                    temp.pop();
                }
            }
        }
    }

    public String simplify(String input) {
        temp = new Stack();
        temp.push(input.charAt(0));
        isLastLetter = false;
        int i = 1;
        while (i < input.length()) {
            char symbol = input.charAt(i);
            if (isSymbolForInput(symbol)) {
                temp.push(symbol);
            }
            deleteUnnecessaryFolder(i, symbol, input);
            i++;
        }
        if (temp.peek().equals('/') && temp.size() > 1) {
            temp.pop();
        }
        StringBuilder result = new StringBuilder();
        while (!temp.empty()) {
            result.append(temp.pop());
        }
        return result.reverse().toString();
    }
}
