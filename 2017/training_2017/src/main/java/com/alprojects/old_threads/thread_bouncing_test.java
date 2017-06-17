package com.alprojects.old_threads;

/**
 * Created by andrew on 31.05.2017.
 */
public class thread_bouncing_test
{
    private static class ThrdManager
    {
        private final Object _sync = new Object();
        int latestClientId = 0;

        public void DisplayPingPongMsg( int clientId, String msg ) throws InterruptedException
        {
            synchronized (_sync)
            {
                while (clientId == latestClientId)
                    _sync.wait();

                System.out.println( String.format("cliend id: %d, msg: %s", clientId, msg) );
                latestClientId = clientId;
                _sync.notifyAll();
            }
        }
    }

    public static void ThreadBouncingTest() throws InterruptedException
    {
        ThrdManager mana = new ThrdManager();

        Thread thr1 = new Thread(() ->
        {
            try
            {
                for(;;)
                {
                    mana.DisplayPingPongMsg( 1, "[Thread1]: ping" );
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ignored)
            {

                System.out.println("[Thread1]: leaving ");
                System.out.println( String.format("[Thread1/catch]: is interrupted == %s", Thread.currentThread().isInterrupted()) );
            }

            System.out.println( String.format("[Thread1/leaving]: is interrupted == %s", Thread.currentThread().isInterrupted()) );
        });

        Thread thr2 = new Thread(() ->
        {
            try
            {
                for(;;)
                {
                    mana.DisplayPingPongMsg( 2, "Thread2: pong" );
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ignored)
            {
                System.out.println("[Thread2]: leaving ");
                System.out.println( String.format("[Thread2/catch]: is interrupted == %s", Thread.currentThread().isInterrupted()) );
            }

            System.out.println( String.format("[Thread2/leaving]: is interrupted == %s", Thread.currentThread().isInterrupted()) );
        });

        thr1.start();
        thr2.start();

        Thread.sleep(10000);

        thr1.interrupt();
        thr2.interrupt();

        thr1.join();
        thr2.join();
    }
}
