public class Temperature {

    public static double getCelsius(double referenceTemperature){
        return (referenceTemperature - 32) * 5 / 9;
    }

    public static double getFahrenheit(double referenceTemperature){
        return referenceTemperature * 9 / 5 + 32;
    }

}
