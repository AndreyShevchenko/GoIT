/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class MatrixTraversal {
    private int[][] input;
    private int[] output;
    private int elemOutput;
    private int lineMin;
    private int lineMax;
    private int columnMin;
    private int columnMax;

    public int[] print(int[][] input) {
        initData(input);
        while (oneCycle()) ;
        return output;
    }

    private void initData(int[][] input) {
        this.input = input;
        lineMax = input.length - 1;
        columnMax = input[0].length - 1;
        output = new int[input.length * input[0].length];
    }

    private boolean isEndOfProgram() {
        return elemOutput < output.length;
    }

    private void assignValue(int line, int column) {
        output[elemOutput] = input[line][column];
        elemOutput++;
    }

    private boolean moveForward(int minValue, int maxValue, boolean isMoveByLine) {
        for (int i = minValue; i < maxValue + 1; i++) {
            if (isMoveByLine) {
                assignValue(lineMin, i);
            } else {
                assignValue(i, columnMax);
            }
        }
        if (isMoveByLine) {
            lineMin++;
        } else {
            columnMax--;
        }
        return isEndOfProgram();
    }

    private boolean moveBackward(int minValue, int maxValue, boolean isMoveByLine) {
        for (int i = maxValue; i >= minValue; i--) {
            if (isMoveByLine) {
                assignValue(lineMax, i);
            } else {
                assignValue(i, columnMin);
            }
        }
        if (isMoveByLine) {
            lineMax--;
        } else {
            columnMin++;
        }
        return isEndOfProgram();
    }

    private boolean oneCycle() {
        boolean isExitFromMethod;
        if (isExitFromMethod = moveForward(columnMin, columnMax, true)) {
            if (isExitFromMethod = moveForward(lineMin, lineMax, false)) {
                if (isExitFromMethod = moveBackward(columnMin, columnMax, true)) {
                    isExitFromMethod = moveBackward(lineMin, lineMax, false);
                }
            }
        }
        return isExitFromMethod;
    }
}
