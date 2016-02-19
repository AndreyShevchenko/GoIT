/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class LongestStabilityPeriod {

    public int count(int[] gdp) {
        int period = 1;
        int counter = 1;
        int firstMonth = 0;
        int secondMonth = 0;
        int firstValue = gdp[0];
        int secondValue = gdp[0];
        boolean isSecondValue = false;
        for (int i = 1; i < gdp.length; i++) {
            if (isSecondValue) {
                if (gdp[i] == firstValue || gdp[i] == secondValue) {
                    counter++;
                } else {
                    firstMonth = secondMonth;
                    firstValue = secondValue;
                    isSecondValue = false;
                    i = secondMonth;
                    counter = 1;
                }
            } else {
                switch (Math.abs(gdp[i] - firstValue)) {
                    case 1:
                        secondMonth = i;
                        secondValue = gdp[i];
                        isSecondValue = true;
                        counter++;
                        break;
                    case 0:
                        counter++;
                        break;
                    default:
                        firstMonth = i;
                        firstValue = gdp[i];
                        counter = 1;
                        break;
                }
            }
            if (period < counter) {
                period = counter;
                if (period > (gdp.length - firstMonth)) return period;
            }
        }
        return period;
    }
}
