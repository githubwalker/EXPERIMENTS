package com.alprojects.Algos;

import java.util.ArrayList;
import java.util.List;

public class TestWildcards {

	// Collection<Integer>
	// Collection<>
	
	static class Vehicle
	{
		private double vehSpeed;
		private String vehName;
		
		public Vehicle( String name, double spd )
		{
			vehName = name;
			vehSpeed = spd;
		}
		
		public double getSpeed()
		{
			return vehSpeed;
		}
	}
	
	static class Truck extends Vehicle
	{
		final double maxCargoWeight;
		
		public Truck(String name, double spd) {
			super(name, spd);
			// TODO Auto-generated constructor stub
			maxCargoWeight = 10;
		}
		
		public double getMaxargoWeight()
		{
			return maxCargoWeight;
		}
	}
	
	public static void testWildcardsItself( List< ? super Vehicle > lst )
	{
		// col.get(0) = 10;
		if ( !lst.isEmpty() )
		{
			lst.set(0, new Truck( "truck", 11 ) );
		}
		
	}
	
	public static <T> void printSomething( T something )
	{
		System.out.println( something.toString() );
	}

	static public void testWildcards()
	{
		ArrayList<Vehicle> al = new ArrayList<Vehicle>();
		ArrayList<Truck> tr = new ArrayList<Truck>();
		
		al.add( new Truck( "truck1", 10 ) );
		
		testWildcardsItself( al );
		
		printSomething( new Integer(1) );
		
		// tr.add(new Vehicle( "Truck", 60 ));
	}
}

