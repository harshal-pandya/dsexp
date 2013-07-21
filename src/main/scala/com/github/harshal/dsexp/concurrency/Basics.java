package com.github.harshal.dsexp.concurrency;

import java.util.Random;

/**
 * @author harshal
 * @date: 7/5/13
 */
public class Basics {
    Thread[] THREAD_POOL;

    public int sharedData = 0;

    static class Mutex {}

    static Mutex mutex = new Mutex();
    Random r = new Random();
    protected Thread makeThread(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (mutex){
                    sharedData++;
                    try {
                        Thread.sleep(r.nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sharedData--;
                }
            }
        };
        return new Thread(runnable);
    }

    public Basics(int N){
        THREAD_POOL = new Thread[N];
        for(int i = 0; i<N; i++){
            THREAD_POOL[i] = makeThread();
            THREAD_POOL[i].start();
        }
        for(int i = 0; i<N; i++){
            try {
                THREAD_POOL[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sharedData);
    }
    public static void main(String[] args){
        new Basics(100);
    }
}
