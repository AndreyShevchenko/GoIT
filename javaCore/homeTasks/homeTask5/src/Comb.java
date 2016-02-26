public class Comb {

    public enum Values {
        INCREASE,
        DECREASE
    }

    private static void swapArray(int[] array, int i, int j) {
        array[i] -= array[j];
        array[j] += array[i];
        array[i] = array[j] - array[i];
    }

    public static int[] sort(int[] someArray, String typeOfSorting) {
        if (someArray.length == 0) return someArray;
        int step = someArray.length;
        boolean isHasChange = true;
        try {
            Values value = Values.valueOf(typeOfSorting);
            while (step > 1 || isHasChange) {
                isHasChange = false;
                if (step > 1) {
                    step /= 1.247330950103979;
                }
                for (int i = 0; i + step < someArray.length; i++) {
                    if ((value == Values.INCREASE && someArray[i] > someArray[i + step]) ||
                            (value == Values.DECREASE && someArray[i] < someArray[i + step])) {
                        swapArray(someArray, i, i + step);
                        isHasChange = true;
                    }
                }
            }
        } catch (IllegalArgumentException value) {
            System.out.println("[Error:] incorrect variable; correct values are INCREASE or DECREASE");
        }
        return someArray;
    }
}
