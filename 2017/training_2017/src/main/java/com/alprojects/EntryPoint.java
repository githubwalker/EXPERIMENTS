package com.alprojects;

import com.alprojects.TestIterable.MySingle;
import com.alprojects.TestIterable.PlayWithIterable;
import com.alprojects.code.j8features.MyInterface;
import com.alprojects.code.j8features.TestFooBarSuper;
import com.alprojects.code.j8features.TestStreamApi;
import com.alprojects.producers_consumers.Message;
import com.alprojects.producers_consumers.ProducersConsumers;
import com.alprojects.testcloneable.TestUser;
import com.alprojects.threads.TestDownload;
import com.alprojects.threads.TestExecutor;
import com.alprojects.threads.TestFuture;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by andrew on 07.05.2017.
 */
public class EntryPoint
{
    private static void TestConcurrent()
    {
        ConcurrentLinkedQueue<Integer> clq = new ConcurrentLinkedQueue<>();
        clq.add(1);
        clq.add(2);
        clq.add(3);

        for(Integer i : clq)
            System.out.println(i);

        // clq.getClass().getClassLoader()

        return;
    }

    public static void TestProducersConsumers() throws Exception
    {
        AtomicInteger cnt = new AtomicInteger(0);
        final long nItems = 1_000_000;
        long beforeMillis = 0;
        // ProducersConsumers pc = null;
        try(ProducersConsumers pc = new ProducersConsumers())
        {
            pc.AddConsumer(msg ->
            {
                // cnt.incrementAndGet();
                // long threadId = Thread.currentThread().getId();
                // System.out.println(String.format("Consumer1 got message %s, (thrid = %d)", msg.getMessage(), threadId));
            });

            pc.AddConsumer(msg ->
            {
                // cnt.incrementAndGet();
                // long threadId = Thread.currentThread().getId();
                // System.out.println(String.format("Consumer2 got message %s (thrid = %d)", msg.getMessage(), threadId));
            });

            pc.AddConsumer(msg ->
            {
                // cnt.incrementAndGet();
                // long threadId = Thread.currentThread().getId();
                // System.out.println(String.format("Consumer3 got message %s, (thrid = %d)", msg.getMessage(), threadId));
            });

            pc.AddConsumer(msg ->
            {
                // cnt.incrementAndGet();
                // long threadId = Thread.currentThread().getId();
                // System.out.println(String.format("Consumer4 got message %s (thrid = %d)", msg.getMessage(), threadId));
            });

            beforeMillis = System.currentTimeMillis();

            for (long i = 0; i < nItems; i++)
                pc.PublishMessage(new Message(String.format("test %d", i)));
        }

        // pc.close();
        // System.out.println( String.format("threads check number is : %d; exit count is : %d", pc.getThreadCheckNumber(), pc.getExitCount())  );

        long afterMillis = System.currentTimeMillis();

        System.out.println( String.format("Time taken to process %d messages : %f, time taken for 1 msg to be delivered : %f millis",
                nItems,
                (double)(afterMillis - beforeMillis) / 1000.0,
                (double)(afterMillis - beforeMillis) / nItems
                ));

        System.out.println( String.format("Number of deliverys : %d", cnt.get()) );
        // Thread.sleep(10000);

        return;
    }

    private static void showClassPath()
    {
        String cp = System.getProperty("java.class.path");
        System.out.println( String.format("cp is %s", cp) );
        return;
    }

    public static void TestShutdownHook()
    {
        Runtime r = Runtime.getRuntime();

        r.addShutdownHook( new Thread( () ->
        {
            System.out.println( "JVM is about to shutdown" );
        } ) );
    }

    public static int foo()
    {
        try
        {
            return 0;
        }
        finally
        {
            return 42;
        }
    }

    public static void foo2()
    {
        try
        {
            throw new NullPointerException("npe2");
        }
        catch (NullPointerException e)
        {
            throw e;
            // throw new NullPointerException("npe2");
        }
        finally
        {
            throw new NullPointerException("npe3");
            // return;
        }
    }

    public static void clTest() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        // ClassLoader cl = ClassLoader.getSystemClassLoader();
        ClassLoader cl = EntryPoint.class.getClassLoader();
        Class<?> clazz = cl.loadClass( "com.alprojects.TestIterable.PlayWithIterable" );
        Constructor<?> ctr = clazz.getConstructors()[0];
        Object newInstance = ctr.newInstance(null);
        return;
    }

    public static void testClone() throws CloneNotSupportedException
    {
        TestUser usr = new TestUser( "UserName", "UserPwd", "UserCity" );
        TestUser usr1 = usr.clone();
    }

    public static Number acceptNumbers( Number[] nums )
    {
        return Arrays.stream(nums).reduce(0,(x,y)->x.intValue()+y.intValue());
    }

    public static void testCov()
    {
        Integer[] nums = {1,2,3,4,5};
        Number nm = acceptNumbers(nums);
    }

    public static void main(String[] args) throws Exception
    {
        testCov();
        testClone();
        clTest();
        foo2();
        String str = "";
        str.hashCode();

        /*
        int rv = foo();
        TestShutdownHook();
        Thread.sleep(5000);
        new PlayWithIterable().test();
        */

        // showClassPath();
        return;

        // TestProducersConsumers();
        // return;
        /*
        TestExecutor te = new TestExecutor();
        te.TestCompletableFuture3();

        te.TestHandleError();

        tsapi.TestStreamFileList();

        System.out.println("==========");
        System.out.println("seems done");
        */
    }
}
