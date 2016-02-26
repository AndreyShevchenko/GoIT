public class FindElement {

    public enum Values {
        MIN,
        MAX
    }

    public static int getElement(int[] arrayOfInteger, String typeOfElement) {
        try {
            int element = arrayOfInteger[0];
            Values value = Values.valueOf(typeOfElement);
            for (int i = 1; i < arrayOfInteger.length; i++) {
                if ((value == Values.MIN && arrayOfInteger[i] < element) ||
                        (value == Values.MAX && arrayOfInteger[i] > element)) {
                    element = arrayOfInteger[i];
                }
            }
            System.out.println(value + " element of array = " + element);
            return element;
        } catch (IllegalArgumentException value) {
            System.out.println("[Error:] incorrect variable; correct values are MIN or MAX");
            return 0;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("[Error]: Array is empty");
            return 0;
        }
    }

}
