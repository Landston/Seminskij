package com.senla;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadPoolExecutor;

public class SystemThread extends Thread {

    private final int n;

    public SystemThread(int n) {
        this.n = n*1000;
    }

    @Override
    public void run(){

        while (true){
            System.out.println(LocalDateTime.now().getHour()+ "-" + LocalDateTime.now().getMinute()+"-" + LocalDateTime.now().getSecond() );

            try {
                Thread.sleep(n);
            } catch (InterruptedException e) {
                System.out.println("Ну вот и всё..");
                e.printStackTrace();
            }
        }


    }

}
