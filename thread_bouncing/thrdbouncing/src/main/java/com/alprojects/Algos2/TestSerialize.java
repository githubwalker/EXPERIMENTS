package com.alprojects.Algos2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestSerialize {
	
	public static class Class1 implements Serializable
	{
		public String calculated = null; 
		public Integer i1;
		public String str;
		
		public Class1( int i1, String s1 )
		{
			this.i1 = i1;
			this.str = s1;
			this.calculated = "Transient" + this.str + Integer.toString(this.i1);   
		}
		
	}

	public static void testSerialize() throws IOException
	{
		Class1 obj1 = new Class1( 1, "2");
		// FileOutputStream fos = new FileOutputStream("C:\\__LEARN_JAVA__\\repeat\\work\\temp.out");
		// ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Integer i = new Integer(1);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream( baos ); 
		oos.writeObject(obj1);
		oos.close();
		
		System.out.println( baos.size() );
		byte[] bytes = baos.toByteArray();
		System.out.println( bytes.length );
		System.out.println( bytes.toString() );
		return;
	}
}
