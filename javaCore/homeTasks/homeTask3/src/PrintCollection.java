import java.util.Collection;
import java.util.Iterator;

public class PrintCollection {

    public static void print(Collection someCollection) {
        Iterator iterator = someCollection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
