import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class SquareSum {
    private int[] values;
    private int numberOfThreads;
    private volatile long result;

    public SquareSum(int[] values, int numberOfThreads) {
        this.values = values;
        this.numberOfThreads = numberOfThreads;
        this.result = 0;
    }

    private int getSquare(int number) {
        return (int) Math.pow(number, 2);
    }

    public long getSimpleSquareSum() {
        result = 0;
        long time = System.nanoTime();
        for (int number : values) {
            result += Math.pow(number, 2);
        }
        System.out.println(System.nanoTime() - time);
        return result;
    }

    public long getSquareSum() {
        result = 0;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        Phaser phaser = new Phaser(numberOfThreads) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                if (phase == values.length / numberOfThreads - 1) {
                    for (int i = 0; i < (registeredParties - values.length % numberOfThreads); i++) {
                        new Thread(this::arriveAndDeregister).start();
                    }
                }
                System.out.println(getRegisteredParties());
                return false;
            }
        };
        int numberOfPhase = values.length / numberOfThreads;
//        long time = System.nanoTime();
        for (int number : values) {
            executor.submit(() -> {
                result += getSquare(number);
                String name = Thread.currentThread().getName();
                System.out.println(name + " start waiting");
//                System.out.println(result);
                phaser.arriveAndAwaitAdvance();
                /*if (phaser.getPhase() == values.length / numberOfThreads) {
                    phaser.arrive();
                } else {
                    phaser.arriveAndAwaitAdvance();
                }*/
                System.out.println(name + " finish waiting");
            });
            if (phaser.getPhase() == values.length / numberOfThreads &&
                    phaser.getArrivedParties() == values.length % numberOfThreads) {
                phaser.forceTermination();
            }
        }
//        System.out.println(System.nanoTime() - time);
        executor.shutdown();
        return result;
    }

    public long getSquareSum(int[] values, int numberOfThreads) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        Phaser phaser = new Phaser(numberOfThreads);
        for (int i = 0; i < values.length; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {


                    try {
                        phaser.awaitAdvanceInterruptibly(15, 5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        executor.shutdown();
        return 5;
    }
}
