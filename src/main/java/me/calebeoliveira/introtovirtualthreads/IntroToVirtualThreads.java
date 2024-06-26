package me.calebeoliveira.introtovirtualthreads;

import java.util.ArrayList;
import java.util.List;

public class IntroToVirtualThreads {
    private static final int NUMBER_OF_VIRTUAL_THREADS = 100;
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new BlockingTask();

//        Thread platformThread = Thread.ofPlatform().unstarted(runnable);
        List<Thread> virtualThreads = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_VIRTUAL_THREADS; i++) {
            Thread virtualThread = Thread.ofVirtual().unstarted(runnable);
            virtualThreads.add(virtualThread);
        }

        for(Thread virtualThread: virtualThreads) {
            virtualThread.start();
        }

        for(Thread virtualThread: virtualThreads) {
            virtualThread.join();
        }
    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Inside Thread: " + Thread.currentThread() + " before blocking call");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Inside thread: " + Thread.currentThread() + " after blocking call");
        }
    }
}
