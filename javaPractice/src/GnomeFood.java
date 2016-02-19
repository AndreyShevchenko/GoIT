import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by Андрей Шевченко on 19.02.2016.
 */
public class GnomeFood {

    public int[] find(int[] gnames, int[] portions) {
        TreeMap<Integer, Integer> mapGnomes = new TreeMap<>();
        TreeMap<Integer, Integer> mapPortions = new TreeMap<>();
        int[] result = new int[gnames.length];
        for (int i = 0; i < gnames.length; i++) {
            mapGnomes.put(gnames[i], i);
            mapPortions.put(portions[i], i);
        }
        Iterator iterGnomes = mapGnomes.values().iterator();
        Iterator iterPortions = mapPortions.values().iterator();
        while (iterGnomes.hasNext()) {
            int i = (Integer) iterGnomes.next();
            int j = (Integer) iterPortions.next();
            result[i] = j;
        }
        return result;
    }
}
