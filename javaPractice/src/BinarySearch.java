public class BinarySearch {
    public int find(int[] array, int target) {
        if (array.length == 0 || target < array[0]) return -1;
        if (target == array[0]) return 0;
        if (target > array[array.length - 1]) return (array.length + 1) * -1;
        int indexBegin = 0;
        int indexEnd = array.length;
        int indexMiddle = (indexEnd - indexBegin) / 2;
        do {
            if (target == array[indexMiddle]) return indexMiddle;
            else if (target < array[indexMiddle]) {
                indexEnd = indexMiddle;
                indexMiddle -= (indexEnd - indexBegin) / 2;
            } else {
                indexBegin = indexMiddle;
                indexMiddle += (indexEnd - indexBegin) / 2;
            }
        } while (indexEnd - indexBegin > 1);
        return (indexEnd + 1) * -1;
    }
}
