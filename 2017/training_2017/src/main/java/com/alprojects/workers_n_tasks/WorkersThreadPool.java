package com.alprojects.workers_n_tasks;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by andrew on 17.06.2017.
 */

// https://www.ibm.com/developerworks/ru/library/j-5things4/index.html
public class WorkersThreadPool
{
    private final BlockingQueue<ITask> _q = new LinkedBlockingQueue<ITask>();
    private final AtomicBoolean _stopFlag = new AtomicBoolean(false);
    private final ExecutorService workerThreadsPool;

    public WorkersThreadPool()
    {
        this(0);
    }

    public WorkersThreadPool( int workersSize )
    {
        int nWorkers = Math.max(workersSize, Runtime.getRuntime().availableProcessors() * 2);
        workerThreadsPool = Executors.newFixedThreadPool(nWorkers);

        for(int i = 0; i < nWorkers; i ++)
        {
            workerThreadsPool.execute( () ->
            {
                for(;;)
                {
                    try
                    {
                        _q.take().doSomething();
                        if (_q.isEmpty() && _stopFlag.get())
                        {
                            addEmptyTaskForNextThread2beAble2stop();
                            return;
                        }
                    } catch (InterruptedException ignored)
                    {
                        return;
                    } catch (Exception ex)
                    {
                        // Something wrong happened
                    }
                }
            } );
        }

        workerThreadsPool.shutdown();
    }

    private void addEmptyTaskForNextThread2beAble2stop()
    {
        _q.add( () -> {} );
    }

    public void addTask( ITask task )
    {
        if (!_stopFlag.get())
            _q.add(task);
    }

    public void stopSignal()
    {
        _stopFlag.set(true);
        addEmptyTaskForNextThread2beAble2stop();
    }

    public void awaitTermination() throws InterruptedException
    {
        while (!workerThreadsPool.isTerminated())
            workerThreadsPool.awaitTermination( 1, TimeUnit.MINUTES );
    }

    public void synchronyousStop() throws InterruptedException
    {
        stopSignal();
        awaitTermination();
    }
}
