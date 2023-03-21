package ru.bor;

import static java.lang.Thread.sleep;

public class WaitNotifyPingPong {
    private final Object monitor = new Object();
    private volatile String current = "ping";

    public static void main(String[] args) {

        WaitNotifyPingPong waitNotifyPingPong = new WaitNotifyPingPong();
        Thread thread1 = new Thread(()->{
            waitNotifyPingPong.printPing();
        });

        Thread thread2 = new Thread(()->{
            waitNotifyPingPong.printPong();
        });

        thread1.start();
        thread2.start();
    }

    private void printPing() {
        synchronized (monitor) {
            try{
                for (int i = 0; i < 100; i++) {
                    while(current != "ping"){
                        monitor.wait();
                    }
                    System.out.println("ping");
                    sleep(500);
                    current = "pong";
                    monitor.notify();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    private void printPong() {
        synchronized (monitor) {
            try{
                for (int i = 0; i < 100; i++) {
                    while(current != "pong"){
                        monitor.wait();
                    }
                    System.out.println("pong");
                    sleep(500);
                    current = "ping";
                    monitor.notify();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}
