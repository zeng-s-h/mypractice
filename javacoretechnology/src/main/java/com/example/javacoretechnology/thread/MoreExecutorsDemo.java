package com.example.javacoretechnology.thread;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;

/**
 * @author 小白i
 * @date 2020/9/6
 */
public class MoreExecutorsDemo {

    public static void main(String[] args) {
        Executor executor = MoreExecutors.directExecutor();
        //executor.execute();
    }

}
