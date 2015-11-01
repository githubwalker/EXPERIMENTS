package com.alprojects.collections;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class testCollections {
	
	public	 static class MyPrinter<T>
	{
		private T val;
		
		public MyPrinter( T val )
		{
			this.val = val;
		}
		
		private static <T> void  printHelper( T theval )
		{
			System.out.println( theval.toString() );
		}
		
		public void printValue()
		{
			printHelper(val);
			
			// System.out.println( val.toString() );
		}
	};
	
	static private class MyObj implements Comparable<MyObj>	{
		private Integer theint;
		
		public MyObj( int theint )
		{
			this.theint = theint; 
		}

		@Override
		public int compareTo(MyObj o) {
			// TODO Auto-generated method stub
			return theint.compareTo(o.theint);
		}
	}
	
	public static void testTreeMap()
	{
		Map<MyObj,String> mp = new TreeMap<MyObj,String>();
		mp.put( new MyObj(1), "1");
		mp.put( new MyObj(2), "2");
		mp.put( new MyObj(3), "3");
		
		// mp.entrySet().iterator()
		
		String str = mp.get( new MyObj(2) );
		
		Set<MyObj> st = new TreeSet<MyObj>();
		
		Hashtable< Integer, String> obj = new Hashtable<Integer, String>();
		
		// mp.entrySet();
		Set<MyObj> keys =  mp.keySet();
		
		for ( MyObj k : keys )
		{
			String found = mp.get(k);
			System.out.println(found);
		}
		
		return;
	}
	
	public void testSortedSet()
	{
		// SortedSet<String> ss = null;
		// TreeSet<String> ss = new TreeSet<String>();
	}

	public static void MytestCollections()
	{
		testTreeMap();
	}
}

