package com.alprojects.reflection;

public class ClassWithPrivate {
	private String str;

	public ClassWithPrivate(String str) {
		this.str = str;
	}
	
	public String getName() 
	{
		return str;
	}
}
