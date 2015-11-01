package com.alprojects.thrdbouncing;

import java.io.FileNotFoundException;

import org.dom4j.DocumentException;






// import com.alprojects.guicetest.CopyModule;
// import com.alprojects.guicetest.ICopier;
//import com.alprojects.package2.p2Class;
import com.alprojects.Algos.Algo;
import com.alprojects.collections.testCollections;
import com.alprojects.guicetest.GuiceTest;
import com.alprojects.hiber.SessionFactoryHolder;
import com.alprojects.hiber.TestPersist;
import com.alprojects.inheritance.AccessClass;
import com.alprojects.reflection.TestReflection;
import com.alprojects.threads.ThreadTests;
import com.alprojects.xml_tests.pomxml_parser;


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
	
	// algorighms
	// http://www.youtube.com/watch?v=f5OD9CKrZEw
	
    public static void main( String[] args ) throws InterruptedException {

    	try
    	{
    		TestPersist.performTests();
	    	// testCollections.MytestCollections();
    	}
    	finally
    	{
	    	SessionFactoryHolder.closeFactory();
    	}
    	
    	return;
    }
}

