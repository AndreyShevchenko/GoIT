public class Comb {

    public enum Values {
        INCREASE,
        DECREASE
    }

    public static void sort(int[] someArray, String typeOfSorting){
        int step = someArray.length;
        try {
            Values value = Values.valueOf(typeOfSorting);
            do {
                step /= 1.247;
                for (int i = 0; i + step < someArray.length; i++) {
                    if ((value == Values.INCREASE && someArray[i] > someArray[i + step]) ||
                            (value == Values.DECREASE && someArray[i] < someArray[i + step])) {
                        int temp = someArray[i];
                        someArray[i] = someArray[i + step];
                        someArray[i + step] = temp;
                    }
                }
            } while (step != 1);
            for (int i : someArray) {
                System.out.print(i + " ");
            }
        }
        catch (IllegalArgumentException value){
            System.out.println("[Error: incorrect variable]; correct values are INCREASE or DECREASE");
        }
    }
}
