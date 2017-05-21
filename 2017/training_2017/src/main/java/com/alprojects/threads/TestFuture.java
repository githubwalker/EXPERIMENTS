package com.alprojects.threads;

/**
 * Created by andrew on 09.05.2017.
 */
public class TestFuture
{
    public void TestFuture() throws InterruptedException
    {
        Thread thr = new Thread( () -> System.out.println("done") );
        thr.start();
        thr.join();
    }
}
