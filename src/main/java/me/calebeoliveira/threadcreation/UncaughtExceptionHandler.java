package me.calebeoliveira.threadcreation;

public class UncaughtExceptionHandler {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Code that will run in a new thread

                throw new RuntimeException("Intencional Exception");
            }
        });
        thread.setName("Misbehaving Thread");

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName()
                + " the error is " + e.getMessage());
            }
        });

        thread.start();

    }
}