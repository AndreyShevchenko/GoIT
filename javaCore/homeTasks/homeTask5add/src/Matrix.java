/**
 * Created by Андрей Шевченко on 05.02.2016.
 */
public class Matrix {

    public boolean isMatrix(double[][] matrix) {
        int i = -1;
        do {
            i++;
        } while (i < matrix.length && matrix.length == matrix[i].length);
        return (i == matrix.length);
    }

    public int getSize(double[][] matrix) {
        return matrix.length;
    }

    public boolean isEqual(double[][] matrixA, double[][] matrixB) {
        return matrixA.length == matrixB.length;
    }

    public boolean isPossibleMakeOperations(double[][] matrixA, double[][] matrixB) {
        return (isMatrix(matrixA) && isMatrix(matrixB) && isEqual(matrixA, matrixB));
    }
}
