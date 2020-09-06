package com.example.javacoretechnology.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 小白i
 * @date 2020/8/30
 */
public class Join {

    public volatile static int i = 0;

    public static class addThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 10000000; i++) ;
        }
    }

    public static void main(String[] args) throws Exception {
        addThread addThread = new addThread();
        addThread.start();
        //join方法保证输出的i结果为10000000，当不join，i的值是一个很小的数值
        addThread.join();
        System.out.println(i);
        new ConcurrentHashMap<>();
    }

}
