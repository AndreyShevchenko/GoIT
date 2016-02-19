/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class MatrixSnakeTraversal {

    public int[] print(int[][] input) {
        int[] outputArray = new int[input.length * input[0].length];
        int elemOfOutputArray = 0;
        int line = 0;
        for (int column = 0; column < input[0].length; column++) {
            do {
                outputArray[elemOfOutputArray] = input[line][column];
                elemOfOutputArray++;
                if (column % 2 == 0) {
                    line++;
                } else {
                    line--;
                }
            } while (line < input.length && line > -1);
            if (line == input.length) {
                line--;
            } else {
                line++;
            }
        }
        return outputArray;
    }
}
