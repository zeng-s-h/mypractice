package com.example.javacoretechnology.thread;

/**
 * @author 小白i
 * @date 2020/8/31
 */
public class Daemon {

    public static class DaemonThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("i am alive");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();

        Thread.sleep(2000);
    }

}
