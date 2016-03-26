public class SemaphoreRealization implements Semaphore {
    private volatile int permits;
    private volatile int counterOfThreads;

    public SemaphoreRealization(int permits) {
        this.permits = permits;
        counterOfThreads = 0;
    }

    private void threadWait() {
        counterOfThreads++;
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acquire() {
        synchronized (this) {
            if (permits > 0) {
                permits--;
            } else {
                threadWait();
            }
        }
    }

    @Override
    public void acquire(int permits) {
        synchronized (this) {
            if (this.permits >= permits) {
                this.permits -= permits;
            } else {
                threadWait();
            }
        }
    }

    @Override
    public void release() {
        synchronized (this) {
            if (counterOfThreads > 0) {
                counterOfThreads--;
            } else {
                permits++;
            }
            this.notify();
        }
    }

    @Override
    public void release(int permits) {
        synchronized (this) {
            if (counterOfThreads > 0) {
                counterOfThreads--;
            } else {
                this.permits += permits;
            }
            this.notify();
        }
    }

    @Override
    public int getAvailablePermits() {
        return permits;
    }
}
