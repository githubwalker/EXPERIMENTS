package com.alprojects.thrdbouncing;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;




import org.dom4j.DocumentException;


// import com.alprojects.guicetest.CopyModule;
// import com.alprojects.guicetest.ICopier;
//import com.alprojects.package2.p2Class;
import com.alprojects.hiber.TestPersist;
import com.alprojects.xml_tests.pomxml_parser;
import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * Hello world!
 *
 */
public class App 
{
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
	
	/*
	private static void testGuice()
	{
		Injector ij = Guice.createInjector(new CopyModule());
		ICopier svc = ij.getInstance(ICopier.class);
		
		svc.copy();
		return;
	}
	*/
	
	private static void testHibernate()
	{
		TestPersist tp = new TestPersist();
		tp.performTests();
	}
	
	private static void testXML()
	{
		pomxml_parser xmlparser = new pomxml_parser();
		try {
			xmlparser.parseFile("");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.err.println("Something wrong happened during parsing");
			e.printStackTrace(System.err);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Something wrong happened during parsing");
			e.printStackTrace(System.err);
		}
	}
	
    public static void main( String[] args ) throws InterruptedException {
    
    	// testXML();
    	testHibernate();
    	return;
    	
    	/*
    	testGuice();
    	
    	System.out.println( "Before cnfClass cn = new cnfClass();" );
    	
    	cnfClass cn = new cnfClass();
    	
    	System.out.println( "After cnfClass cn = new cnfClass();" );
    	
    	// AnotherClass ak = new AnotherClass();
    	// AnotherClass.staticMethod();
    	p2Class.staticMethod();
    	
    	finStatClass fsc = new finStatClass();
    	
    	// com.google.inject.BindingAnnotation
    	
    	
    	BounceManager bm = new BounceManager();
    	
    	ArrayList<Thread> thrds = new ArrayList<Thread>();
    	thrds.add( new BounceThrd( "thrd1", bm, 500 ) );
    	thrds.add( new BounceThrd( "thrd2", bm, 8888 ) );
    	thrds.add( new BounceThrd( "thrd3", bm, 1234 ) );
    	thrds.add( new BounceThrd( "thrd4", bm, 3333 ) );
    	thrds.add( new BounceThrd( "thrd5", bm, 10111 ) );
    	
    	System.out.println( "Before Thread.start" );
    	
    	for ( Thread th : thrds )
    		th.start();
    		
    	System.out.println( "Before Thread.sleep(20 * 1000)" );

    	Thread.sleep(20 * 1000);
    	
    	System.out.println( "After Thread.sleep(20 * 1000)" );

    	System.out.println( "Before massive interrupt" );
    	
    	for ( Thread th : thrds )
    	{
    		th.interrupt();
    	}
    		
    	System.out.println( "Before massive join" );
    	
    	for ( Thread th : thrds )
    		th.join();
    		
    	System.out.println( "leaving" );
    	*/
    }
}

