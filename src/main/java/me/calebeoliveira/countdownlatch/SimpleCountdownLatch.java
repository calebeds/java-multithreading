package me.calebeoliveira.countdownlatch;

public class SimpleCountdownLatch {
    private int count;

    public SimpleCountdownLatch(int count) {
        this.count = count;
        if(count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (count > 0) {
                this.wait();
            }
        }
    }

    public void countdown() {
        synchronized (this) {
            if(count > 0) {
                count--;

                if(count == 0) {
                    this.notifyAll();
                }
            }
        }
    }

    public int getCount() {
        return this.count;
    }
}
