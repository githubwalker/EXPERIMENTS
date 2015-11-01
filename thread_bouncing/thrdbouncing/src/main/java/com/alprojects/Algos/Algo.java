package com.alprojects.Algos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import freemarker.core.ParseException;

// Trying to understand how java collections are working

public class Algo {

	public static String testKickoffRepeatingChars_noiterators(String str) {
		StringBuilder sb = new StringBuilder();
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

		for (int i = str.length() - 1; i >= 0; i--)
			hm.put(str.charAt(i), i);

		TreeMap<Integer, Character> tr = new TreeMap<Integer, Character>();

		for (HashMap.Entry<Character, Integer> hm_entry : hm.entrySet())
			tr.put(hm_entry.getValue(), hm_entry.getKey());

		for (Entry<Integer, Character> tr_entry : tr.entrySet())
			sb.append(tr_entry.getValue());

		return sb.toString();
	}

	public static String testKickoffRepeatingChars(String str) {
		StringBuilder sb = new StringBuilder();

		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

		for (int i = str.length() - 1; i >= 0; i--)
			hm.put(str.charAt(i), i);

		TreeMap<Integer, Character> tr = new TreeMap<Integer, Character>();

		Iterator<Entry<Character, Integer>> it_hm = hm.entrySet().iterator();

		while (it_hm.hasNext()) {
			Entry<Character, Integer> e = it_hm.next();
			tr.put(e.getValue(), e.getKey());
		}

		for (Iterator<Entry<Integer, Character>> it = tr.entrySet().iterator(); it
				.hasNext();) {
			Entry<Integer, Character> e = it.next();
			sb.append(e.getValue());
		}

		return sb.toString();
	}

	public static String testKickoffRepeatingChars_viaLinked(String str) {
		StringBuilder sb = new StringBuilder();

		LinkedHashSet<Character> lhs = new LinkedHashSet<Character>();
		for (int i = 0; i < str.length(); i++)
			lhs.add(str.charAt(i));

		for (Character ch : lhs)
			sb.append(ch);

		return sb.toString();
	}

	public static void testSet1() {
		StringBuilder sb = new StringBuilder();
		TreeSet<Character> tr = new TreeSet<Character>();
		tr.add('a');
		tr.add('b');
		tr.add('c');

		for (Character ch : tr) {
			sb.append(ch);
		}
	}

	public static String testSet2() {
		StringBuilder sb = new StringBuilder();
		TreeSet<Character> tr = new TreeSet<Character>();
		tr.add('a');
		tr.add('b');
		tr.add('c');

		for (Iterator<Character> it = tr.iterator(); it.hasNext();) {
			sb.append(it.next());
		}

		return sb.toString();
	}

	public static String testMap3(String str) {
		StringBuilder sb = new StringBuilder();

		TreeMap<Integer, Character> tr = new TreeMap<Integer, Character>();

		for (int i = 0; i < str.length(); i++)
			tr.put(i, str.charAt(i));

		for (Entry<Integer, Character> e : tr.entrySet())
			sb.append(e.getValue());

		return sb.toString();
	}

	public static String testHashMap4(String str) {
		StringBuilder sb = new StringBuilder();

		HashMap<Integer, Character> hm = new HashMap<Integer, Character>();

		for (int i = 0; i < str.length(); i++)
			hm.put(i, str.charAt(i));

		for (HashMap.Entry<Integer, Character> ent : hm.entrySet())
			sb.append(ent.getValue());

		return sb.toString();
	}

	public static String testTreeSet5(String str) {
		StringBuilder sb = new StringBuilder();
		TreeSet<Character> ts = new TreeSet<Character>();

		for (int i = 0; i < str.length(); i++)
			ts.add(str.charAt(i));

		for (Character e : ts)
			sb.append(e);

		for (Iterator<Character> it = ts.iterator(); it.hasNext();)
			sb.append(it.next());

		return sb.toString();
	}

	public static String testHashSet6(String str) {
		StringBuilder sb = new StringBuilder();

		HashSet<Character> hs = new HashSet<Character>();

		for (int i = 0; i < str.length(); i++)
			hs.add(str.charAt(i));

		for (Character ch : hs)
			sb.append(ch);

		for (Iterator<Character> it = hs.iterator(); it.hasNext();)
			sb.append(it.next());

		return sb.toString();
	}

	public static List<Integer> merge(ArrayList<Integer> a1,
			ArrayList<Integer> a2) {
		ArrayList<Integer> rv = new ArrayList<Integer>();

		int i = 0;
		int j = 0;

		while (i < a1.size() && j < a2.size()) {
			if (a1.get(i) < a2.get(j))
				rv.add(a1.get(i++));
			else
				rv.add(a2.get(j++));
		}

		while (i < a1.size())
			rv.add(a1.get(i));

		while (j < a2.size())
			rv.add(a2.get(j));

		return rv;
	}

	public static <T> List<T> myAsList(T... a) {
		System.out.println(a.length);
		ArrayList<T> rv = new ArrayList<T>();
		for (T el : a)
			rv.add(el);

		return rv;
	}

	public static void testArrays2() {
		List<Integer> lst = myAsList(1, 2, 3);

		for (Integer In : lst) {
			System.out.println(In);
		}
	}

	public static void testArrays() {
		Integer[] array = new Integer[10];
		System.out.println(array.length);

		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.addAll(Arrays.asList(array));
	}

	public static void testSet() {
		MyPrinter mp1 = new MyPrinter("123", "456");
		MyPrinter mp2 = new MyPrinter("345", "8910");
		MyPrinter mp3 = new MyPrinter("234", "1111");
		MyPrinter mp4 = new MyPrinter("234", "1112");

		TreeSet<MyPrinter> ts = new TreeSet<MyPrinter>();

		ts.add(mp4);
		ts.add(mp3);
		ts.add(mp2);
		ts.add(mp1);

		for (MyPrinter e : ts)
			System.out.println(e.toString());

		TreeSet<MyPrinter> ts2 = new TreeSet<MyPrinter>(
				new MyPrinter.MyComparator());

		ts2.add(mp4);
		ts2.add(mp3);
		ts2.add(mp2);
		ts2.add(mp1);

		for (MyPrinter e : ts2)
			System.out.println(e.toString());

		return;
	}
	
	public static void testCollections()
	{
		// ArrayList<Integer> al = new ArrayList<Integer>();
		Integer[] ints = new Integer[10];
		// ints.
		ArrayList<Integer> al = new ArrayList<Integer>( Arrays.asList(ints) );

		// Collections.
		// al.toArray();
	}
	
	public enum ABC
	{
		AAA,BBB,CCC
	}
	
	public enum Planet
	{
		MERCURY ( "Mercury", 3.303e+23, 2.4397e6 ),
		VENUS   ( "Venus", 4.869e+24, 6.0518e6),
		EARTH   ( "Earth", 5.976e+24, 6.37814e6);		
		    
		String name;
		double mass;
		double radius;
		
		Planet( String name, double mass, double radius )
		{
			this.name = name; 
			this.mass = mass;
			this.radius = radius;
		}
		
		String getName() { return name; }
		double getMass() { return mass; }
		double getRadius() { return radius; }
	};
	
	public static void ArrayTest() throws ParseException
	{
		Integer[] ints = {1,2,3,4,5};
		Object newInts = ints.clone();
		
		if ( newInts instanceof Integer[] )
		{
			Integer[] ints2 = (Integer[])newInts;
			System.out.println( "its instance of Integer[]" );
			System.out.println( "length = " + ints2.length );
		}
	}
	
	public static void ExceptionTest()
	{
		try
		{
			try
			{
				// throw new IllegalStateException();
				throw new Exception();
			}
			finally
			{
				
			}
		}
		catch (IllegalStateException ex)
		{
			System.out.println("1");
			throw ex;
		}
		catch (RuntimeException e)
		{
			System.out.println("2");
		}
		catch (Exception ee)
		{
			System.out.println("3");
		}
	}
	
	public static <T extends Comparable<T>> void mergeSortedArrays(
			List<T> a1,
			List<T> a2,
			List<T> a3
			) {

		Iterator<T> it1 = a1.iterator();
		Iterator<T> it2 = a2.iterator();
		
		T current1 = it1.hasNext() ? it1.next() : null;
		T current2 = it2.hasNext() ? it2.next() : null;
		
		while ( current1 != null && current2 != null )
		{
			if ( current1.compareTo(current2) < 0 )
			{
				a3.add(current1);
				current1 = it1.hasNext() ? it1.next() : null; 
			}
			else
			{
				a3.add(current2);
				current2 = it2.hasNext() ? it2.next() : null; 
			}
		}
		
		if ( current1 != null )
			a3.add(current1);
		
		while ( it1.hasNext() )
			a3.add(it1.next());
		
		if ( current2 != null )
			a3.add(current2);
		
		while ( it2.hasNext() )
			a3.add(it2.next());
	}
	
	private static void mergeArrayLists_test()
	{
		ArrayList<Integer> al1 = new ArrayList<Integer>( Arrays.asList( 1, 4, 5, 7 )  );
		ArrayList<Integer> al2 = new ArrayList<Integer>( Arrays.asList( 2, 3, 4, 6, 9, 11 ) );
		ArrayList<Integer> al3 = new ArrayList<Integer>();
		
		mergeSortedArrays( al1, al2, al3 );
		
		for ( Integer val : al3 )
		{
			System.out.print( val );
			System.out.print( ", " );
		}
		
		System.out.println();
	}

	public static void performTests() {
		
		// java.lang.Enum<Enum<E>>
		
		// ArrayTest();
		// ExceptionTest();

		/*
		try {
			ConcatFiles.concatFiles(
					"C:\\PROJECTS\\JAVA\\FILES\\MsgSys.log.txt", 
					"C:\\PROJECTS\\JAVA\\FILES\\WindowsUpdate (1).log", 
					"C:\\PROJECTS\\JAVA\\FILES\\out"
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		mergeArrayLists_test();
		
		return;
		
		

		// testSet();

		// testArrays2();

		/*
		 * // String kk = testKickoffRepeatingChars( "12346712389_" ); String
		 * srcString = "mutherfucker_1234_1235_mutheraaa"; String kk1 =
		 * testKickoffRepeatingChars_noiterators( srcString ); String kk2 =
		 * testKickoffRepeatingChars( srcString ); String kk3 =
		 * testKickoffRepeatingChars_viaLinked( srcString );
		 * System.out.println(kk1); System.out.println(kk2);
		 * System.out.println(kk3);
		 */

	}

}