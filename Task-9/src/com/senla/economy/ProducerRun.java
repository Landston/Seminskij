package com.senla.economy;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

public class ProducerRun extends Thread {

    Data data;


    public ProducerRun(Data data) {
        this.data = data;
    }

    @Override
    public void run() {

        Random random = new Random();
        while (true) {


            if (data.getData().size() < 10) {
                try {
                    Integer add = random.nextInt(1000);

                    data.getData().add(add);

                    System.out.println(data.getData());

                    synchronized (data) {
                        data.notifyAll();
                    }
                    System.out.println(add + " Added to DATA");
                }
                catch (ConcurrentModificationException e){
                    synchronized (data){

                            System.out.println(this.getState());
                        data.notifyAll();

                    }
                }
            } else {
                try {
                    synchronized (data) {

                     data.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

    }

}

