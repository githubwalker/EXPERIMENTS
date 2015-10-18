package com.alprojects.thrdbouncing;

public class Ethrower {

	public void toThmthng()
	{
		final String m_member = "123";
		
		class InnerClass
		{
			InnerClass()
			{
				
				System.out.println( "InnerClass.InnerClass" );
				System.out.println( m_member );
			}
		};
		
		InnerClass ic = new InnerClass();
		
		
		
		System.out.println( "public void toThmthng()" );
		// throw new RuntimeException();
	}
}
