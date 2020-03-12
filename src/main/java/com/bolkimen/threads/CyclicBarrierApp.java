package com.bolkimen.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierApp {

    private static final CyclicBarrier BARRIER = new CyclicBarrier(5, () -> {
        try {
            Thread.sleep(500);
            System.out.println("job was done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    public static void main(String[] args) {
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

}
