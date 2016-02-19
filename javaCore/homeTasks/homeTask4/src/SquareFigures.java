public class SquareFigures {

    private static void checkNegativeValue(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException();
        }
    }

    // площадь треугольника
    public static double getSquare(double sideA, double sideB, double sideC) {
        try {
            checkNegativeValue(sideA);
            checkNegativeValue(sideB);
            checkNegativeValue(sideC);
            double perimeter = (sideA + sideB + sideC) / 2;
            return Math.sqrt(perimeter * (perimeter - sideA) * (perimeter - sideB) * (perimeter - sideC));
        } catch (IllegalArgumentException ex) {
            System.out.println("Length of side must be positive");
        }
        return -1;
    }

    // площадь прямоугольника
    public static double getSquare(double sideA, double sideB) {
        try {
            checkNegativeValue(sideA);
            checkNegativeValue(sideB);
            return sideA * sideB;
        } catch (IllegalArgumentException ex) {
            System.out.println("Length of side must be positive");
        }
        return -1;
    }

    // площадь круга
    public static double getSquare(double radius){
        try {
            checkNegativeValue(radius);
            return Math.PI * Math.pow(radius, 2);
        } catch (IllegalArgumentException ex) {
            System.out.println("Length of radius must be positive");
        }
        return -1;
    }
}
