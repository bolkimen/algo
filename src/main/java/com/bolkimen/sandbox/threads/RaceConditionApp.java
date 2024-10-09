package com.bolkimen.sandbox.threads;

public class RaceConditionApp {
    static volatile int count = 0;

    private static void increment() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                new Thread(() -> {
                  while(true) {
                      ThreadUtils.sleep(123);
                  }
                }).start();
                for(int index=0; index<1000; index++) {
                    increment();
                    ThreadUtils.sleep(10000);
                }
                System.out.println("Finish 1th");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int index=0; index<1000; index++) {
                    increment();
                    ThreadUtils.sleep(10000);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);
    }
}
