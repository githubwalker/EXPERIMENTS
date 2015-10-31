package com.alprojects.inheritance;

public class InputType {
	protected String str;
	
	public InputType( String str )
	{
		this.str = str;
	}
	
	public String get() 
	{
		return str == null ? "null" : str;
	}
}

