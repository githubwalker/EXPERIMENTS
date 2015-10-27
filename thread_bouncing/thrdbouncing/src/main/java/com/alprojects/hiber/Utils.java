package com.alprojects.hiber;

import org.hibernate.Session;

public class Utils {
	public static boolean LinkBusAndDriver( TheBus bus, TheDriver driver )
	{
		bus.getDrivers().add(driver);
		driver.getBuses().add(bus);
		return true;
	}
	
	public static boolean savedObject( IPersistObject obj )
	{
		return obj.getId() != null;
	}
	
	public static void persistOrMerge( Session ses, IPersistObject obj )
	{
		if ( obj.getId() != null )
		{
			ses.merge(obj);
		}
		else
		{
			ses.persist(obj);
		}
	}
}


