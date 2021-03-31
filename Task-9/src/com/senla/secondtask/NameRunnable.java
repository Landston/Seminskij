package com.senla.secondtask;

import java.util.concurrent.Semaphore;

public class NameRunnable implements Runnable {

    private String name;
    private Semaphore semaphore;



    public NameRunnable(String name, Semaphore semaphore ) {
        this.name = name;
        this.semaphore = semaphore;

    }

    @Override
    public void run() {

        while (Thread.currentThread().isAlive()) {
            try {

                System.out.println(name);
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
