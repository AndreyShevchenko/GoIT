/**
 * Created by Андрей Шевченко on 22.02.2016.
 */
public class WordNumber {

    private boolean isSymbolIsLetter(char symbol) {
        return ((symbol - 'a' >= 0 && symbol - 'a' <= 25) || (symbol - 'A' >= 0 && symbol - 'A' <= 25));
    }

    public int count(String input) {
        int counter = 0;
        boolean isLastSymbolIsSign = true;
        for (char symbol : input.toCharArray()) {
            if (isLastSymbolIsSign) {
                if (isSymbolIsLetter(symbol)) {
                    isLastSymbolIsSign = false;
                    counter++;
                }
            } else {
                if (!isSymbolIsLetter(symbol)) {
                    isLastSymbolIsSign = true;
                }
            }
        }
        return counter;
    }
}
