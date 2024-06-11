package me.calebeoliveira.threadcreation;

public class ThreadInheritance {

    public static void main(String[] args) {
        Thread thread = new NewThread();

        thread.start();
    }


    private static class NewThread extends Thread {
        @Override
        public void run() {
            // Code that will run in a new thread
            System.out.println("We are now in thread " + this.getName());
        }
    }
}
