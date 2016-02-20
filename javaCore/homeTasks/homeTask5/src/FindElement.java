public class FindElement {

    public enum Values {
        MIN,
        MAX
    }

    public static int getElement(int[] arrayOfInteger, String typeOfElement){
        int element = arrayOfInteger[0];
        try {
            Values value = Values.valueOf(typeOfElement);
            for (int i = 1; i < arrayOfInteger.length; i++) {
                if ((value == Values.MIN && arrayOfInteger[i] < element) ||
                        (value == Values.MAX && arrayOfInteger[i] > element)) {
                    element = arrayOfInteger[i];
                }
            }
            System.out.println(value + " element of array = " + element);
        }
        catch (IllegalArgumentException value){
            System.out.println("[Error: incorrect variable]; correct values are MIN or MAX");
            return ((int) Double.NaN);

        }
        return element;
    }

}
