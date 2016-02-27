public class MatrixCalculator {
    private double[][] resultMatrix;
    private Matrix matrix = new Matrix();

    private double[][] simpleMath(double[][] matrixA, double[][] matrixB, char type) {
        resultMatrix = new double[matrix.getSize(matrixA)][matrix.getSize(matrixA)];
        if (matrix.isPossibleMakeOperations(matrixA, matrixB)) {
            for (int i = 0; i < matrix.getSize(matrixA); i++) {
                for (int j = 0; j < matrix.getSize(matrixA); j++) {
                    if (type == '+') {
                        resultMatrix[i][j] = matrixA[i][j] + matrixB[i][j];
                    } else {
                        resultMatrix[i][j] = matrixA[i][j] - matrixB[i][j];
                    }
                }
            }
            printMatrix();
        } else {
            System.out.println("Matrix is not equal");
        }
        return resultMatrix;
    }

    public double[][] add(double[][] matrixA, double[][] matrixB) {
        return simpleMath(matrixA, matrixB, '+');
    }

    public double[][] subtract(double[][] matrixA, double[][] matrixB) {
        return simpleMath(matrixA, matrixB, '-');
    }

    public double[][] multiply(double[][] matrixA, double multiplier) {
        resultMatrix = new double[matrix.getSize(matrixA)][matrix.getSize(matrixA)];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                resultMatrix[i][j] = matrixA[i][j] * multiplier;
            }
        }
        printMatrix();
        return resultMatrix;
    }

    /*private double findMinor(double[][] someArray) {
        return (someArray[0][0] * someArray[1][1] - someArray[0][1] * someArray[1][0]);
    }*/


    public void printMatrix() {
        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j < resultMatrix[0].length; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }


}
