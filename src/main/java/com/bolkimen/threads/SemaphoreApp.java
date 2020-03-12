package com.bolkimen.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreApp {
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i<20; i++) {
            service.submit(() -> {
                try {
                    SEMAPHORE.acquire();
                    System.out.println("available Semaphore permits now: " + SEMAPHORE.availablePermits());
                    Thread.sleep(5000);
                    SEMAPHORE.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }
}
