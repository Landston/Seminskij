package com.senla;

import com.senla.economy.Data;

public class MyThreadImpl implements Runnable {

    private Main main;
    private String name;

    public MyThreadImpl(Main main) {
        this.main = main;
    }

    @Override
    public void run() {

        while (Thread.currentThread().isAlive()){
            try {
                if ("Blocked".equals(main.getCondition())) {
                  synchronized (this.main){

                  }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
