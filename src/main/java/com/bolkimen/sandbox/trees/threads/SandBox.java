package com.bolkimen.sandbox.trees.threads;

import java.util.List;
import java.util.concurrent.*;

class Producer<T extends Number> implements Runnable {
    private BlockingQueue<T> queue;
    private T value;
    public Producer(BlockingQueue<T> queue, T value) {
        this.queue = queue;
        this.value = value;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.put(this.value);
                System.out.println("Put the number");

                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<? extends Number> queue;
    public Consumer(BlockingQueue<? extends Number> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Number num = queue.take();
                System.out.println("Take number: " + num);

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

public class SandBox {

    public <T extends Number> void fun(List<T> arr1, List<T> arr2) {
        arr1.addAll(arr2);
    }

    public static void main(String[] args) {
        ExecutorService thrPool = Executors.newFixedThreadPool(2);
        BlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
                //new SynchronousQueue<>();
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue, 10);
        Future consumerResult = thrPool.submit(consumer);
        Future producerResult = thrPool.submit(producer);

        while (consumerResult.state() == Future.State.RUNNING || producerResult.state() == Future.State.RUNNING) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("All done");
    }
}
