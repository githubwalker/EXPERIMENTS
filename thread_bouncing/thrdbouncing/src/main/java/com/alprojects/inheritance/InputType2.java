package com.alprojects.inheritance;

public class InputType2 extends InputType {
	protected String str2;
	
	InputType2( String str )
	{
		super(str);
		str2 = str + "_2";
		str2.equals(str);
	}
	
	@Override
	public String get() 
	{
		return super.get() + ( str2 == null ? "null" : str2 );
	}
}

