package com.alprojects.workers_n_tasks;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by andrew on 17.06.2017.
 */
public class LoadTestWorkersThreadPool
{
    public static void doTest() throws InterruptedException
    {
        WorkersThreadPool wpt = new WorkersThreadPool();
        final int nBlocks = 80;
        BlockingQueue<int[]> blocks2sort = new LinkedBlockingQueue<>();
        ConcurrentLinkedQueue<int[]> sorted_blocks = new ConcurrentLinkedQueue<>();

        CountDownLatch doneSignal = new CountDownLatch(nBlocks);

        for(int iRandTask = 0 ; iRandTask < nBlocks; iRandTask ++)
        {
            final int[] taskNumber = new int[1];
            taskNumber[0] = iRandTask;

            wpt.addTask( () ->
            {
                wpt.addTask( () ->
                {
                    System.out.println( String.format("[SORT][START] %d ", taskNumber[0]) );
                    int[] block = blocks2sort.take();
                    Arrays.sort(block);
                    sorted_blocks.add(block);
                    System.out.println( String.format("[SORT][STOP] %d", taskNumber[0]) );
                    doneSignal.countDown();
                } );

                System.out.println( String.format("[RND][START] %d ", taskNumber[0]) );
                int[] ints = new int[1000000];
                Random rnd = new Random();

                for(int i = 0; i < ints.length; i ++)
                    ints[i] = rnd.nextInt();
                System.out.println( String.format("[RND][STOP] %d", taskNumber[0]) );
                blocks2sort.add(ints);
            } );
        }

        doneSignal.await();
        wpt.synchronyousStop();
    }
}
