package me.calebeoliveira.racecondition;

public class RaceConditionLock {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.printf("We currently have %s items%n", inventoryCounter.getItems());
    }

    private static class DecrementingThread extends Thread {
        private final InventoryCounter inventoryCounter;
        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    private static class IncrementingThread extends Thread {
        private final InventoryCounter inventoryCounter;
        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }
    private static class InventoryCounter {
        private int items = 0;
        Object lock = new Object();

        public void increment() {
            // code
            synchronized (this.lock) {
                items++;
            }
            // more code
        }

        public void decrement() {
            synchronized (this.lock) {
                items--;
            }
            // some work to do

        }

        public int getItems() {
            synchronized (this.lock) {
                return items;
            }
        }
    }
}
