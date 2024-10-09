package com.bolkimen.sandbox.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalApp {
    private static volatile ThreadLocal<Integer> tl = ThreadLocal.withInitial(() -> 10);
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future f1 = es.submit(() -> {
            System.out.println(tl.get());
            tl.set(12);
        });
        sleep(120);
        Future f2 = es.submit(() -> {
            System.out.println(tl.get());
            tl.set(16);
        });

        while (!f1.isDone() || !f2.isDone()) {
            sleep(121);
        }
        es.shutdownNow();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
