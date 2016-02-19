public class SquareFigures {

    private static void checkValue(double number) {
        if (number <= 0 || number > Double.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
    }

    static double checkNumberEqualsFinite(double number) {
        if (Double.isFinite(number)) {
            return number;
        } else {
            return Double.NaN;
        }
    }

    // площадь треугольника
    public static double getSquare(double sideA, double sideB, double sideC) {
        try {
            checkValue(sideA);
            checkValue(sideB);
            checkValue(sideC);
            double perimeter = checkNumberEqualsFinite((sideA + sideB + sideC) / 2);
            return checkNumberEqualsFinite(Math.sqrt(perimeter * (perimeter - sideA) * (perimeter - sideB) * (perimeter - sideC)));
        } catch (IllegalArgumentException ex) {
            System.out.println("It's wrong value was entered");
        }
        return Double.NaN;
    }

    // площадь прямоугольника
    public static double getSquare(double sideA, double sideB) {
        try {
            checkValue(sideA);
            checkValue(sideB);
            return checkNumberEqualsFinite(sideA * sideB);
        } catch (IllegalArgumentException ex) {
            System.out.println("It's wrong value was entered");
        }
        return Double.NaN;
    }

    // площадь круга
    public static double getSquare(double radius){
        try {
            checkValue(radius);
            return checkNumberEqualsFinite(Math.PI * Math.pow(radius, 2));
        } catch (IllegalArgumentException ex) {
            System.out.println("It's wrong value was entered");
        }
        return Double.NaN;
    }
}
