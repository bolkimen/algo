package com.bolkimen.sandbox.threads;

import java.util.concurrent.*;

public class CyclicBarrierApp {

    private static final CyclicBarrier BARRIER = new CyclicBarrier(5, () -> {
        try {
            Thread.sleep(500);
            System.out.println("job was done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    private void main1() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i<20; i++) {
            service.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " thread before avait");
                    BARRIER.await();
                    System.out.println(Thread.currentThread().getName() + " thread after avait");
                } catch (BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        Future res = es.submit(() -> {
            sleep(1000);
            System.out.println("Hello");
        });

        while (!res.isDone()) {
            sleep(1000);
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
