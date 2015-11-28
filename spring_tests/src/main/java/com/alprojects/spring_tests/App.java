package com.alprojects.spring_tests;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alprojects.aop.SomeFunctionality;
import com.alprojects.bean2.MyCalculator;

/**
 * Hello world!
 *
 */

// https://www.youtube.com/watch?v=rYXUEtKj8l8
	
public class App {
	public static void test2()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-Module2.xml");
		MyCalculator mycalc = (MyCalculator)ac.getBean("calculator");
		mycalc.performCalcs(1, 2);
		
		return;
	}
	
	public static void testAop()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-Module3.xml");
		SomeFunctionality sf = (SomeFunctionality)ac.getBean("some_functionality_proxy");
		
		sf.doSomething();
		
		sf.printSomething("Hello World!!!");
		
		return;
	}
	
	public static void main(String[] args) {
		// test2();
		
		// Logger.getLogger(SomeFunctionality.class.getName());
		testAop();
		
		return;
		
		// System.out.println("Hello World!");
		
		/*
		ApplicationContext ac = new ClassPathXmlApplicationContext("Spring-Module1.xml");
		
		HelloWorldBean hwb = (HelloWorldBean)ac.getBean("helloBean");
		hwb.printHello();
		*/
	}
}


