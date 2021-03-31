package com.senla;

import com.senla.economy.Data;
import com.senla.secondtask.NameRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    private Main main;
    private String condition;


    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        Counter counter = new Counter();
        Semaphore sem = new Semaphore(2);
        Data data = new Data();
        ExecutorService service = Executors.newFixedThreadPool(2);

///////////////////////////////////////// TASK - 1

        main.condition = "Blocked";
        Thread mainThread = new Thread(new MyThreadImpl(main), "Main Thread");
        Thread sideThread = new Thread(new MyThreadImpl(main), "Thread - 2");

        mainThread.start();
        sideThread.start();

        System.out.println(mainThread.getState());
        System.out.println(sideThread.getState());

///////////////////////////////////////// TASK - 2
        //      service.execute(new NameRunnable("First", sem));
        // service.execute(new NameRunnable("Second", sem));


       /* service.execute(new countRun(counter,sem, "thread-1"));
        service.execute(new countRun(counter,sem, "thread-2"));
        service.execute(new countRun(counter,sem, "thread-3"));
        service.execute(new countRun(counter,sem, "thread-4"));
*/
        //////////////////////////////// TASK -4
      /*  SystemThread systemThread = new SystemThread(1);

        systemThread.setDaemon(true);

        systemThread.start();

        Thread thread = new Thread(new countRun(counter,sem, "thread-1"));
        thread.start();


        */

        ///////////////////////////////////////// 3 TASK

      /*

        List<Integer> itas = new ArrayList<>();

        itas.add(1);
        itas.add(2);
        itas.add(3);
        itas.add(4);
        data.setData(itas);

        ProducerRun producer = new ProducerRun(data);
        ConsumerRun consumer = new ConsumerRun(data);

        producer.setDaemon(true);
        producer.start();
        consumer.start();

*/


    }

    public String getCondition() {
        return condition;
    }

    public void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public synchronized void blocked() {

    }
}

