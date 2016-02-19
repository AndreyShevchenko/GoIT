public class DistancePoints {

    static void checkValue(double number) {
        if ((number > 0 && number < Math.abs(Double.MIN_VALUE)) || number > Double.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
    }

    public static double getDistance(double x1, double y1, double x2, double y2){
        try {
            checkValue(x1);
            checkValue(y1);
            checkValue(x2);
            checkValue(y2);
            return SquareFigures.checkNumberEqualsFinite(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
        } catch (IllegalArgumentException ex) {
            System.out.println("It's wrong value was entered");
        }
        return Double.NaN;
    }
}
