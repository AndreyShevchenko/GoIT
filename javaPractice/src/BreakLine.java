/**
 * Created by Андрей Шевченко on 22.02.2016.
 */
public class BreakLine {
    private int wordBegin;

    private boolean isSymbolIsLetter(char symbol) {
        return ((symbol - 'a' >= 0 && symbol - 'a' <= 25) || (symbol - 'A' >= 0 && symbol - 'A' <= 25));
    }

    private boolean isWordLongerWidth(int i, int width, String input) {
        wordBegin = i;
        int wordLength = 1;
        boolean isNeedMoveForward = true;
        boolean isNeedMoveBackward = true;
        if (isSymbolIsLetter(input.charAt(i))) {
            int j = 1;
            while (isNeedMoveForward || isNeedMoveBackward) {
                if (i + j < input.length()) {
                    if (isSymbolIsLetter(input.charAt(i + j)) && isNeedMoveForward) {
                        wordLength++;
                    } else {
                        isNeedMoveForward = false;
                    }
                } else {
                    isNeedMoveForward = false;
                }
                if (isSymbolIsLetter(input.charAt(i - j)) && isNeedMoveBackward) {
                    wordLength++;
                    wordBegin = i - j;
                } else {
                    isNeedMoveBackward = false;
                }
                j++;
            }
            if (wordLength > width) return true;
        }
        return false;
    }

    public String format(String input, int width) {
        if (input.length() <= width) return input;
        StringBuilder output = new StringBuilder();
        int lastRightI = 0;
        int i = width;
        while (i < input.length()) {
            lastRightI = i;
            if (!isSymbolIsLetter(input.charAt(i)) || isWordLongerWidth(i, width, input)) {
                output.append(input.substring(i - width, i - 1));
                i += width;
            } else {
                output.append(input.substring(i - width, wordBegin - 1));
                i = wordBegin + width;
            }
            output.append("\n");
        }
        output.append(input.substring(lastRightI, input.length()));
        return output.toString();
    }
}
