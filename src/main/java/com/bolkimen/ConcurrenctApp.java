package com.bolkimen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Lock{
    private boolean isLocked      = false;
    private Thread  lockingThread = null;

    public synchronized void lock() throws InterruptedException{
        while(isLocked){
            wait();
        }
        isLocked      = true;
        lockingThread = Thread.currentThread();
    }

    public synchronized void unlock(){
        if(this.lockingThread != Thread.currentThread()){
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLocked      = false;
        lockingThread = null;
        notify();
    }
}
public class ConcurrenctApp
{

    public static void main( String[] args ) throws Exception
    {
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        outputScraper.add("asd");
        /*
        try {
            executor.awaitTermination( 20l, TimeUnit.NANOSECONDS );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("finish");
    }
}
