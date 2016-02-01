package com.alprojects.jms_tests;


public class Dispatcher {
	public static void main(String[] args) {

		try {

			for (String arg : args) {
				if ("-listener".equalsIgnoreCase(arg)) {
					Listener.main(args);
					return;
				} else if ("-publisher".equalsIgnoreCase(arg)) {
					Publisher.main(args);
					return;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("neither publisher nor listener was recognized");
	}
}
