package com.bolkimen.sandbox.trees.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchApp {
    private static final CountDownLatch START = new CountDownLatch(10);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i<10; i++) {
            service.submit(() -> {
                try {
                    START.countDown();
                    System.out.println("count: " + START.getCount());
                    Thread.sleep(5000);
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
