package com.example.javacoretechnology.thread;

/**
 * @author 小白i
 * @date 2020/8/31
 */
public class PriorityDemo {

    public static class HighPriorityThread extends Thread {
        int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("high priority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriorityThread extends Thread {
        int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("low priority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        HighPriorityThread high = new HighPriorityThread();
        LowPriorityThread lowPriorityThread = new LowPriorityThread();
        high.setPriority(Thread.MAX_PRIORITY);
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);

        high.start();
        lowPriorityThread.start();
    }

}
