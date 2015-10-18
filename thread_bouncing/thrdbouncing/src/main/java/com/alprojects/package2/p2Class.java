package com.alprojects.package2;

// import com.alprojects.thrdbouncing.AnotherClass;

// import com.alprojects.thrdbouncing;



public class p2Class {
	
	public String ak;
	
	public static void staticMethod()
	{
		System.out.println( "p2Class.staticMethod" );
	}
	
	
	public p2Class( String val )
	{
		ak = val;
	}
	
	public final int fnl()
	{
		return 1;
	}
	
	protected int doSomething()
	{
		return 1;
	}

}
