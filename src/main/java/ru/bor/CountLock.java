package ru.bor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class CountLock {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() ->{
            try {
                lock.lock();
                System.out.println("in thread " + Thread.currentThread().getName());
                for (int i = 0; i < 40; i++) {
                    count++;
                    sleep(5);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread t2 = new  Thread(() ->{
            try {
                lock.lock();
                System.out.println("in thread " + Thread.currentThread().getName());
                for (int i = 0; i < 60; i++) {
                    count++;
                    sleep(7);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();

            }
        });

        Thread t3 = new  Thread(() ->{
            try {
                lock.lock();
                System.out.println("in thread " + Thread.currentThread().getName());
                for (int i = 0; i < 50; i++) {
                    count--;
                    sleep(2);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();

            }
        });

        t1.start();//0
        t2.start();//1
        t3.start();//2

        new Thread(() ->{
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("count is: " + count);
        }).start();


    }
}
