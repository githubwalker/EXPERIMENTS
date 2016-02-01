package com.alprojects.thrdbouncing;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.dom4j.DocumentException;

import com.alprojects.hiber.SessionFactoryHolder;
import com.alprojects.hiber.TestPersist;
import com.alprojects.inheritance.AccessClass;
import com.alprojects.test_json.TestJsonSerialize;
import com.alprojects.xml_tests.pomxml_parser;
// import com.alprojects.guicetest.CopyModule;
// import com.alprojects.guicetest.ICopier;
//import com.alprojects.package2.p2Class;


/**
 * Hello world!
 *
 */
public class App 
{
	
	
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
	
	private static short testDoudle() {
		boolean b = true;
		short c = (b) ? (short)1 : (short)0;
		double a = 1.234567;
		short k = (short) a;
		return k;
	}
	
	static private void testConvert()
	{
		short a = 5;
		long b = -a;
		float diameters[] = { 1.2f, 3.5f, 4.5f };
		float f = 1.5f;
		long lll = 1_000_000_000;
		
		int myInts[][] = { {1,2,3}, {4,5}, {6,7,8,9}  };
		
		int k = myInts[2][3];
		
		String str = "123";
		// str.com
		
		System.gc();
		return;
	}
	
	private static class Person
	{
		byte[] thearray = new byte[1024];
		
		@Override
		protected void finalize() throws Throwable
		{
			System.out.println("GC was called");
			super.finalize();			
		}
	}
	
	private static void testPerson()
	{
		if (true)
		{
			Person ps = new Person();
		}
		
		System.gc();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	
	private static void changeRefTest_helper( Person ps1 )
	{
		// Integer.valueOf("345");
		ps1 = new Person();
		return;
	}
	
	private static void changeRefTest()
	{
		Person ps = new Person();
		changeRefTest_helper( ps );
		
		int k = 1;
		k <<= 1;
		
		k >>= 1;
		
		k = 2;
		k = ~k;
		return;
	}
	
	public void testOver(String s)
	{}
	
	public String testOver(String s,int k)
	{
		return s;
	}
	
	private static class testAccess extends AccessClass 
	{
		String str1;
		
		public testAccess()
		{
			str1 = super.str;
		}
	} 
	
	public void testAcess()
	{
		AccessClass ac = new AccessClass();
	}
	
	public static class finalTest
	{
		public final String str;
		
		public finalTest()
		{
			str = "456";
		}
	}
	
	public static void testException() {
		try {
			try {
				throw new IllegalStateException();
			} finally {
				throw new RuntimeException();
			}
		} catch (IllegalStateException ex) {
			System.out.println("1");
		} catch (RuntimeException e) {
			System.out.println("2");
		} catch (Exception ee) {
			System.out.println("3");
		}
	}
	
	public static void testError()
	{
		try
		{
			try
			{
				throw new Exception("!!!");
			}
			catch (Exception e)
			{
				System.out.println( "catch 1" );
				return;
			}
			finally 
			{
				System.out.println( "finally 1" );
			}
		}
		catch ( Exception e )
		{
			System.out.println( "catch 2" );
		}
		finally 
		{
			System.out.println( "finally 2" );
		}
		
		// System.out.println( "returning" );
	}
	
	static public void testLock()
	{
		// ReentrantLock rl = new ReentrantLock();
		// rl.lock();
		// rl.unlock();
		
		// ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
		// rrwl.readLock().lock();
		// rrwl.writeLock().lock();
	}
	
	// algorighms
	// http://www.youtube.com/watch?v=f5OD9CKrZEw
	
    public static void main( String[] args ) throws InterruptedException {

    	try
    	{
    		// TestPersist.performTests();
    		// sortFiles();
			// SortStringFile.Sort4bytesNumbers2( "C:\\__TEST__\\DSC_0895.JPG", "");
    		// TestClone.testClone();
    		// C:\__TEST__\DSC_0895.JPG
    		// TestSerialize.testSerialize();
    		// TestWildcards.testWildcards();
    		// TestExceptions.testExceptions();
    		// Algo.performTests();
    		// GuiceTest.performTests();
    		// Algo.performTests();
    		// new RuntimeException();
    		
    		// JdbcTests.performTests();
    		// Dispatcher.main(args);
    		// Algo.performTests();
    		// testException();
    		TestJsonSerialize.testJsonSerialize();
    		
    		// testError();
		}
    	finally
    	{
	    	SessionFactoryHolder.closeFactory();
    	}
    	// testCollections.MytestCollections();
    	
    	return;
    }
}

