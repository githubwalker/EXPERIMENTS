package com.alprojects.threads;

import java.util.ArrayList;
import java.util.Random;

public class ThreadTests {
	
	public static class BounceManager
	{
		Object _mutex = new Object();
		Integer _nPrevValue = null;
		
		public void writeNumber2console( String threadName, int nNumber ) throws InterruptedException
		{
			synchronized(_mutex)
			{
				while (_nPrevValue != null && _nPrevValue == nNumber)
					_mutex.wait();
				
				System.out.println( threadName + ": " + new Integer(nNumber).toString() );
				_nPrevValue = nNumber;
				
				_mutex.notify();
			}			
		}
	}
	
	public static class BounceThrd extends Thread
	{
		private BounceManager _bm;
		private String _thrdName;
		private int _sleepTime;
		
		BounceThrd( String thrdName, BounceManager bounceManager, int sleepTime )
		{
			_thrdName = thrdName;
			_bm = bounceManager;
			_sleepTime = sleepTime;
		}
		
		public void run() {
			while ( !isInterrupted() )
			{
				try {
					int nNumber = new Random().nextInt(2);
					nNumber = nNumber == 0 ? 0 : 1; // normalize
						_bm.writeNumber2console(_thrdName, nNumber);
						nNumber = 1 - nNumber;
						sleep(_sleepTime);
				} 
				catch (InterruptedException e) {
					System.out.println( "Thread " + _thrdName + " exiting ..." );
					Thread.currentThread().interrupt();
				}
			}
			
			if ( isInterrupted() )
			{
				System.out.println( "Thread " + _thrdName + " was interrupted" );
			}
			
			System.out.println( "Thread " + _thrdName + " was interrupted - leaving" );
		}
	}
	

	public static void testThreads() {
    	ArrayList<Thread> thrds = new ArrayList<Thread>();
    	
    	BounceManager bm = new BounceManager();
		
    	thrds.add( new BounceThrd( "thrd1", bm, 500 ) );
    	thrds.add( new BounceThrd( "thrd2", bm, 8888 ) );
    	thrds.add( new BounceThrd( "thrd3", bm, 1234 ) );
    	thrds.add( new BounceThrd( "thrd4", bm, 3333 ) );
    	thrds.add( new BounceThrd( "thrd5", bm, 10111 ) );

    	
    	System.out.println( "Starting " + Integer.toString(thrds.size()) + " threads"  );
    	
    	for ( Thread th : thrds )
    		th.start();
    	
    	final int seconds2wait = 20; // 20 sec
    	
    	System.out.println( "Waiting " + Integer.toString(seconds2wait) + " Seconds" );
    	
    	try {
			Thread.sleep( 20 * 1000 );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	System.out.println( "Cancelling all " + Integer.toString(thrds.size()) + " threads" );
    	
    	for ( Thread th : thrds )
    		th.interrupt();
    	
    	System.out.println( "Interrupt signal was sent to all threads. Waiting for threads completion" );

    	for ( Thread th : thrds )
    	{
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	System.out.println( "Completed. Leaving" );
	}
}

