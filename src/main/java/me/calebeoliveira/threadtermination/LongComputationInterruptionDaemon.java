package me.calebeoliveira.threadtermination;

import java.math.BigInteger;

public class LongComputationInterruptionDaemon {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new LongComputationClass(new BigInteger("2000000000"), new BigInteger("100000000000000")));

        thread.setDaemon(true);
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }

    private static class LongComputationClass implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        public LongComputationClass(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }

            return result;
        }
    }
}
