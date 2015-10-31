package com.alprojects.inheritance;

public class BaseClass {
	String str;
	
	public ReturnType method1( InputType it )
	{
		return new ReturnType( it.get() + ": method1" );
	}
}
