package com.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by wuxinjian on 2017/6/4.
 */
public class MyMutexTest {

    MyMutex mutex = new MyMutex();
    int count =0;

    class MutexTestRunnable implements Runnable{

        @Override
        public void run() {
            while(true){
                if(mutex.tryLock()){
                    count++;
                    System.out.println(Thread.currentThread().getName()+":"+count);
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mutex.unlock();
                }
            }
        }
    }


    @Test
    public void mutexTest() throws InterruptedException {
        new Thread(new MutexTestRunnable()).start();
        new Thread(new MutexTestRunnable()).start();
        TimeUnit.SECONDS.sleep(1);
        assertTrue(count>=99&&count<=101);
    }

}