package com.senla.economy;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.List;

public class ConsumerRun extends Thread {

    Data data;

    public ConsumerRun(Data data) {
        this.data = data;
    }

    private List<Integer> resources = new ArrayList<>();

    @Override
    public void run() {

        while (true){

            if(data.getData().size()>0){
                Integer item =  data.getData().get(0);
                resources.add(item);
                data.getData().remove(item);

            }else {
                System.out.println("Nothing to get from data");
                System.out.println(resources);
                System.out.println(resources.size());

                try {
                    synchronized (data) {
                        data.notifyAll();

                        data.wait();
                    }


                }
                catch(InterruptedException e){
                        e.printStackTrace();
                    }

            }

            if(resources.size() == 100){
                this.stop();
                return;
            }
        }


    }
}
