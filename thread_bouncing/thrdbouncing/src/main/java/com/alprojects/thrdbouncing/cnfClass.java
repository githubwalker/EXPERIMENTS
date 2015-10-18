package com.alprojects.thrdbouncing;

public class cnfClass {
	
	static String m_str;
	static Ethrower m_eth;
	
	static
	{
		m_str = "123";
		System.out.println( "in static section" );
		m_eth = new Ethrower();
		m_eth.toThmthng();
		// throw new RuntimeException("123");
	}

	public cnfClass()
	{
		
	}
}

