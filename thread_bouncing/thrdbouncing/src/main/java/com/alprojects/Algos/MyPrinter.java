package com.alprojects.Algos;

import java.util.Comparator;

public class MyPrinter implements Comparable<MyPrinter> {
	private String a;
	private String b;
	
	public MyPrinter( String a, String b ) 
	{
		this.a = new String(a);
		this.b = new String(b);
	}

	@Override
	public int compareTo(MyPrinter o) {
		// TODO Auto-generated method stub
		
		int rv = a.compareTo(o.a);
		if ( rv != 0 )
			return rv;
		return b.compareTo(o.b);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		sb.append( a );
		sb.append("] [");
		sb.append( b );
		sb.append("]");
		
		return sb.toString();
	}

	public static class MyComparator implements Comparator<MyPrinter> {
		@Override
		public int compare(MyPrinter o1, MyPrinter o2) {
			// TODO Auto-generated method stub
			int rv = o1.b.compareTo(o2.b);
			if (rv != 0)
				return rv;
			return o1.a.compareTo(o2.a);
		}
	}
}

