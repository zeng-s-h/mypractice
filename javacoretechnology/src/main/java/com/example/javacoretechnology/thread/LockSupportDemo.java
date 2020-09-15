package com.example.javacoretechnology.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 小白i
 * @date 2020/9/6
 */
public class LockSupportDemo {

    public static Object u = new Object();

    static ChangeObjectThread t1 = new ChangeObjectThread("t1");

    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            //类加锁
            synchronized (u) {
                System.out.println("in " + getName());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //阻塞线程
                LockSupport.park();
                System.out.println("执行结束");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // LockSupport.unpark(t2);

        //unpark方法只能再线程start后才会起作用
        t1.start();
        LockSupport.unpark(t1);
        Thread.sleep(1000);
        //t2.start();
        //释放线程park阻塞状态
//        LockSupport.unpark(t1);
//        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }

}
