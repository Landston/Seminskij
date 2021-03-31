package com.senla;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class countRun implements Runnable {

    private Counter counter;
    private Semaphore sem;
    private String name;


    public countRun(Counter counter, Semaphore sem, String name) {
        this.counter = counter;
        this.sem = sem;
        this.name = name;
    }

    @Override
    public  void run() {

        try{
            System.out.println(name +" Wait for permission");
            sem.acquire();


                counter.i = 1;

                System.out.println(Thread.currentThread().getState());
                for (int i = 1; i < 6; i++) {
                    System.out.println(this.name + ": " + i);
                    counter.i++;

                    synchronized (Thread.currentThread()){
                        Thread.currentThread().wait(10000);

                    }
                }

                System.out.println(name + "  " + counter.i);

            sem.release();
        } catch (InterruptedException e) {
            System.out.println(name + " освобождает разрешение");
            sem.release();
        }

    }
}
