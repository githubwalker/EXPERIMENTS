package com.alprojects.reflection;

import java.lang.reflect.Field;
import java.util.Hashtable;

public class TestReflection {

	public static void testReflection() 
	{
		ClassWithPrivate obj = new ClassWithPrivate("test");
		Class<? extends ClassWithPrivate> cls = obj.getClass();
		try {
			Field fld = cls.getDeclaredField("str");
			fld.setAccessible(true);
			Object val = fld.get(obj);
			System.out.println( val );
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int a = 10;
		int b = 1;
		
		throw new Error();
	}
}
