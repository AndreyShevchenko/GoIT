import java.io.*;
import java.util.*;

public class TestCollectionSpeed {
    private int numberOfMeasurements;
    private Random random = new Random();
    private long time;

    public TestCollectionSpeed(int numberOfMeasurements) {
        this.numberOfMeasurements = numberOfMeasurements;
    }

    private void initCollections(List[] lists, Set[] sets) {
        lists[0] = new ArrayList<>();
        lists[1] = new LinkedList<>();
        sets[0] = new HashSet<>();
        sets[1] = new TreeSet<>();
    }

    private long populateCollection(Collection<Integer> someCollection, int size) {
        someCollection.clear();
        time = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            someCollection.add(random.nextInt() % size);
        }
        return System.currentTimeMillis() - time;
    }

    private long getAverageTimeForListAdd(List<Integer> someCollection, int size) {
        int index = Math.abs(random.nextInt() % size);
        time = System.nanoTime();
        someCollection.add(index, index);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForSetAdd(Set<Integer> someCollection, int size) {
        int value = random.nextInt() % size;
        time = System.nanoTime();
        someCollection.add(value);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForListGet(List<Integer> someCollection, int size) {
        int index = Math.abs(random.nextInt() % size);
        time = System.nanoTime();
        someCollection.get(index);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForListRemove(List<Integer> someCollection, int size) {
        int index = Math.abs(random.nextInt() % size);
        time = System.nanoTime();
        someCollection.remove(index);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForSetRemove(Set<Integer> someCollection, int size) {
        int value = random.nextInt() % size;
        time = System.nanoTime();
        someCollection.remove(value);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForContains(Collection<Integer> someCollection, int size) {
        int value = random.nextInt() % size;
        time = System.nanoTime();
        someCollection.contains(value);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForListIteratorAdd(List<Integer> someCollection, int size) {
        ListIterator<Integer> iterator = someCollection.listIterator();
        int number = Math.abs(random.nextInt() % size);
        time = System.nanoTime();
        iterator.add(number);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForListIteratorRemove(List<Integer> someCollection) {
        ListIterator<Integer> iterator = someCollection.listIterator();
        iterator.next();
        time = System.nanoTime();
        iterator.remove();
        return System.nanoTime() - time;
    }

    private String getRightLengthString(String record) {
        StringBuilder temp = new StringBuilder(record);
        while (temp.length() < 15) {
            temp.append(' ');
        }
        return temp.toString();
    }

    private String getRightStringFromDouble(Double number, String second) {
        StringBuilder temp = new StringBuilder(number.toString());
        temp.delete(temp.indexOf(".") + 2, temp.length());
        temp.append(second);
        return getRightLengthString(temp.toString());
    }

    private void printFields(Integer size, String filename) {
        String[] fieldNames = {"size = ", "add", "get", "remove", "contains", "populate", "iterator.add", "iterator.remove"};
        try (FileWriter file = new FileWriter(filename, true)) {
            fieldNames[0] = fieldNames[0].concat(size.toString());
            for (String fieldName : fieldNames) {
                String temp = getRightLengthString(fieldName);
                System.out.print(temp);
                file.write(temp);
            }
            System.out.println();
            file.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printResults(double[][] results, String filename) {
        final String[] collectionNames = {"ArrayList", "LinkedList", "HashSet", "TreeSet"};
        try (FileWriter file = new FileWriter(filename, true)) {
            for (int i = 0; i < 4; i++) {
                String temp = getRightLengthString(collectionNames[i]);
                System.out.print(temp);
                file.write(temp);
                for (int j = 0; j < 7; j++) {
                    results[i][j] /= numberOfMeasurements;
                    if (j == 4) {
                        temp = getRightStringFromDouble(results[i][j], " ms");
                        System.out.print(temp);
                        file.write(temp);
                    } else {
                        temp = getRightStringFromDouble(results[i][j], " ns");
                        System.out.print(temp);
                        file.write(temp);
                    }
                    results[i][j] = 0;
                }
                System.out.println();
                file.write("\n");
            }
            System.out.println();
            file.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getResults(String filename) {
        final Integer[] sizes = {10_000, 100_000, 1_000_000};
        double[][] results = new double[4][7];
        List<Integer>[] lists = new List[2];
        Set<Integer>[] sets = new Set[2];
        initCollections(lists, sets);
        for (int i = 0; i < 3; i++) {
            printFields(sizes[i], filename);
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < numberOfMeasurements; k++) {
                    results[j][4] += populateCollection(lists[j], sizes[i]);
                    results[j][0] += getAverageTimeForListAdd(lists[j], sizes[i]);
                    results[j][1] += getAverageTimeForListGet(lists[j], sizes[i]);
                    results[j][2] += getAverageTimeForListRemove(lists[j], sizes[i]);
                    results[j][3] += getAverageTimeForContains(lists[j], sizes[i]);
                    results[j][5] += getAverageTimeForListIteratorAdd(lists[j], sizes[i]);
                    results[j][6] += getAverageTimeForListIteratorRemove(lists[j]);
                    results[j + 2][4] += populateCollection(sets[j], sizes[i]);
                    results[j + 2][0] += getAverageTimeForSetAdd(sets[j], sizes[i]);
                    results[j + 2][2] += getAverageTimeForSetRemove(sets[j], sizes[i]);
                    results[j + 2][3] += getAverageTimeForContains(sets[j], sizes[i]);
                }
            }
            printResults(results, filename);
        }
    }

}
