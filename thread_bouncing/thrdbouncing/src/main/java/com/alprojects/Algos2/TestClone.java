package com.alprojects.Algos2;

public class TestClone  {
	public static class Class1 implements Cloneable
	{
		public transient String calculated = null; 
		public Integer i1;
		public String str;
		
		public Class1( int i1, String s1 )
		{
			this.i1 = i1;
			this.str = s1;
			this.calculated = this.str + Integer.toString(this.i1);   
		}
		
		@Override
		public Class1 clone() throws CloneNotSupportedException {
             return (Class1)super.clone();
		}		
	}
	
	public static void testClone() throws CloneNotSupportedException
	{
		Class1 cl1 = new Class1( 1, "2" );
		Class1 cl2 = cl1.clone();
		
		return;
	}
	
}
