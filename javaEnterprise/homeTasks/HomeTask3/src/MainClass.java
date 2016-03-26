import java.util.Calendar;

import static java.lang.Thread.sleep;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

public class MainClass {
    private static final int threadsQuantity = 10;
    private static final int permitsQuantity = 10;
    private static final int permitsForOneThread = 2;
    private static SemaphoreRealization sr = new SemaphoreRealization(permitsQuantity);

    public static void main(String[] args) {
        for (int i = 0; i < threadsQuantity; i++) {
            if (i % 3 == 0 && i > 0) {
                try {
                    sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            getTime(i, " was created at ");
            newThread(i);
        }
    }

    private static void getTime(int i, String text) {
        Calendar current = Calendar.getInstance();
        System.out.println("Thread " + i + text + current.get(HOUR) + ":" + current.get(MINUTE) + ":" + current.get(SECOND));
    }

    private static void newThread(int i) {
        Thread thread = new Thread(() -> {
            sr.acquire(permitsForOneThread);
            getTime(i, " start at ");
            try {
                sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getTime(i, " finish at ");
            sr.release(permitsForOneThread);
        });
        thread.start();
    }

}
