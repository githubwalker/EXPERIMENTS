package com.alprojects.producers_consumers;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by andrew on 13.05.2017.
 */
public class ProducersConsumers implements Closeable
{
    private enum RunMode
    {
        NormalRun,
        HardTermination,
        Stopping
    };

    private class Worker implements Callable<Void>
    {
        private BlockingQueue<Message> _bq = new LinkedBlockingQueue<>();
        private ConcurrentLinkedQueue<IConsumer> _consumers = new ConcurrentLinkedQueue<>();


        public int getRestItems()
        {
            return _bq.size();
        }

        public void PublishMessage(Message msg) throws Exception
        {
            if (_runMode != RunMode.NormalRun)
                throw new Exception("we are terminating");
            _bq.add(msg);
        }

        public void AddConsumer( IConsumer consumer )
        {
            _consumers.add(consumer);
        }

        private void devastate_queue() throws InterruptedException
        {
            Message msg = null;
            while( (msg = _bq.poll(50, TimeUnit.MILLISECONDS)) != null)
            {
                for (IConsumer cons : _consumers)
                    cons.consume(msg);
            }
        }

        @Override
        public Void call() throws Exception
        {
            try
            {
                for(;;)
                {
                    devastate_queue();

                    if (_runMode == RunMode.Stopping)
                    {
                        if (!_bq.isEmpty())
                            devastate_queue();
                        break;
                    }

                    if (_runMode == RunMode.HardTermination)
                        break;
                }
            } catch (InterruptedException e)
            {
                // e.printStackTrace();
                return null;
            }

            return null;
        }
    }

    private final int nWorkers = 10;
    private AtomicInteger current_buffer_index = new AtomicInteger(0);
    private BlockingQueue<Message> _bq = new LinkedBlockingQueue<>();
    // private ConcurrentLinkedQueue<IConsumer> _consumers = new ConcurrentLinkedQueue<>();
    private volatile RunMode _runMode = RunMode.NormalRun;
    private List<Worker> _workers = new ArrayList<>();
    private List<Future<Void>> _worker_futures = new ArrayList<>();
    ExecutorService _executor = Executors.newFixedThreadPool(nWorkers);

    public ProducersConsumers()
    {
        for(int i = 0; i < nWorkers; i++)
        {
            Worker wr = new Worker();
            _workers.add(wr);
            _worker_futures.add(_executor.submit( wr ));
        }
    }

    private void stop(boolean bWaitforComletion) throws ExecutionException, InterruptedException
    {
        _runMode = bWaitforComletion ? RunMode.Stopping : RunMode.HardTermination;


        for(Future<Void> ft : _worker_futures)
           ft.get();
        _executor.shutdown();

        for(Worker wk : _workers)
            System.out.println(String.format("Worker: %d items left", wk.getRestItems()));
    }

    private void stop() throws ExecutionException, InterruptedException
    {
        stop(true);
    }

    public void AddConsumer( IConsumer consumer )
    {
        for( Worker wk : _workers )
            wk.AddConsumer(consumer);
    }

    public void PublishMessage(Message msg) throws Exception
    {
        _workers.get(current_buffer_index.incrementAndGet() % nWorkers).PublishMessage(msg);
    }

    @Override
    public void close()
    {
        try
        {
            stop();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

