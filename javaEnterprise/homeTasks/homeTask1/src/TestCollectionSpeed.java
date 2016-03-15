import java.io.*;
import java.util.*;

public class TestCollectionSpeed {
    private int numberOfMeasurements;
    private Random random = new Random();
    private int randomNumber;
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
            someCollection.add(Math.abs(random.nextInt() % size));
        }
        return System.currentTimeMillis() - time;
    }

    private long getAverageTimeForListAdd(List<Integer> someCollection) {
        time = System.nanoTime();
        someCollection.add(randomNumber, randomNumber);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForSetAdd(Set<Integer> someCollection) {
        time = System.nanoTime();
        someCollection.add(randomNumber);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForListGet(List<Integer> someCollection) {
        time = System.nanoTime();
        someCollection.get(randomNumber);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForRemove(Collection<Integer> someCollection) {
        time = System.nanoTime();
        someCollection.remove(randomNumber);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForContains(Collection<Integer> someCollection) {
        time = System.nanoTime();
        someCollection.contains(randomNumber);
        return System.nanoTime() - time;
    }

    private long getAverageTimeForListIteratorAdd(List<Integer> someCollection) {
        ListIterator<Integer> iterator = someCollection.listIterator();
        time = System.nanoTime();
        iterator.add(randomNumber);
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
        final String[] fieldNames = {"size = ", "add", "get", "remove", "contains", "populate", "iterator.add", "iterator.remove"};
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
        for (Integer size : sizes) {
            printFields(size, filename);
            for (int j = 0; j < lists.length; j++) {
                for (int k = 0; k < numberOfMeasurements; k++) {
                    randomNumber = Math.abs(random.nextInt() % size);
                    results[j][4] += populateCollection(lists[j], size);
                    results[j][0] += getAverageTimeForListAdd(lists[j]);
                    results[j][1] += getAverageTimeForListGet(lists[j]);
                    results[j][2] += getAverageTimeForRemove(lists[j]);
                    results[j][3] += getAverageTimeForContains(lists[j]);
                    results[j][5] += getAverageTimeForListIteratorAdd(lists[j]);
                    results[j][6] += getAverageTimeForListIteratorRemove(lists[j]);
                    results[j + 2][4] += populateCollection(sets[j], size);
                    results[j + 2][0] += getAverageTimeForSetAdd(sets[j]);
                    results[j + 2][2] += getAverageTimeForRemove(sets[j]);
                    results[j + 2][3] += getAverageTimeForContains(sets[j]);
                }
            }
            printResults(results, filename);
        }
    }

}
