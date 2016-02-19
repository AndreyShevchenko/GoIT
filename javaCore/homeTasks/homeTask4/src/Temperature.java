import javax.naming.NameNotFoundException;

public class Temperature {

    public static double getCelsius(double referenceTemperature){
        try {
            DistancePoints.checkValue(referenceTemperature);
            return SquareFigures.checkNumberEqualsFinite((referenceTemperature - 32) * 5 / 9);
        } catch (IllegalArgumentException ex) {
            System.out.println("It's wrong value was entered");
        }
        return Double.NaN;
    }

    public static double getFahrenheit(double referenceTemperature){
        try {
            DistancePoints.checkValue(referenceTemperature);
            return SquareFigures.checkNumberEqualsFinite(referenceTemperature * 9 / 5 + 32);
        } catch (IllegalArgumentException ex) {
            System.out.println("It's wrong value was entered");
        }
        return Double.NaN;
    }

}
