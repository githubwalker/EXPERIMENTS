package com.alprojects.hiber;

public class Utils {
	public static boolean LinkBusAndDriver( TheBus bus, TheDriver driver )
	{
		bus.getDrivers().add(driver);
		driver.getBuses().add(bus);
		return true;
	}
}
