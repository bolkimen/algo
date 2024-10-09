package com.bolkimen.sandbox.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchApp {
    private static final CountDownLatch START = new CountDownLatch(5);

    public static void main(String[] args) {
        int countThreads = 6;
        ExecutorService service = Executors.newFixedThreadPool(countThreads);
        System.out.println("Before: " + START.getCount());
        for (int i = 0; i<countThreads; i++) {
            service.submit(() -> {
                try {
                    START.countDown();
                    System.out.println("count: " + START.getCount());
                    //Thread.sleep(5000);
                    START.await();
                    System.out.println("finished - OK");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }
}
