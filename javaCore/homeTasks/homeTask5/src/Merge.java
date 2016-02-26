public class Merge {
    public static int[] sort(int[] someArray) {
        int newSize = someArray.length / 2;
        int[] tempArray1 = new int[newSize];
        int[] tempArray2 = new int[someArray.length - newSize];
        for (int i = 0; i < someArray.length; i++) {
            if (i < newSize) {
                tempArray1[i] = someArray[i];
            } else {
                tempArray2[i - newSize] = someArray[i];
            }
        }
        if (tempArray1.length > 1) {
            sort(tempArray1);
        }
        if (tempArray2.length > 1) {
            sort(tempArray2);
        }
        int counter1 = 0;
        int counter2 = 0;
        for (int i = 0; i < someArray.length; i++) {
            if (counter1 < tempArray1.length && counter2 < tempArray2.length) {
                if (tempArray1[counter1] < tempArray2[counter2]) {
                    someArray[i] = tempArray1[counter1];
                    counter1++;
                } else {
                    someArray[i] = tempArray2[counter2];
                    counter2++;
                }
            } else {
                if (counter1 < tempArray1.length) {
                    someArray[i] = tempArray1[counter1];
                    counter1++;
                } else {
                    someArray[i] = tempArray2[counter2];
                    counter2++;
                }
            }
        }
        return someArray;
    }

    public static void printArray(int[] someArray) {
        System.out.println();
        for (int i : someArray) {
            System.out.print(i + " ");
        }
    }

}
