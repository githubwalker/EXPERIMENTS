package com.alprojects.TestCloneable;

public class MyCloneable implements Cloneable {
	private String thetag = null;

	public MyCloneable( String tag )
	{
		this.thetag = tag; 
	}
	
	public String getTag() 
	{
		return this.thetag;
	}
	
	public MyCloneable clone() throws CloneNotSupportedException{
		return new MyCloneable(thetag);
	}
	
}


