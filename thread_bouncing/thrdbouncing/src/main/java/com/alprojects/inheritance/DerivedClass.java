package com.alprojects.inheritance;

public class DerivedClass extends BaseClass {

	@Override
	public ReturnType2 method1( InputType it )
	{
		return new ReturnType2( "method2" + it.get()  );
	}
	
}
