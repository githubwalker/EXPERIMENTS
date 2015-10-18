package com.alprojects.guicetest;

public class SimpleWriter implements IWriter {

	public void write(String data) {
		System.out.println( data );
	}

}
