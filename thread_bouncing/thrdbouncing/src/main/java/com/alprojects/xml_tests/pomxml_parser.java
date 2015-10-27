package com.alprojects.xml_tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

// http://tinman.cs.gsu.edu/~raj/8711/sp04/xpath/General_rus/examples.html

public class pomxml_parser {
	
	private void print_recursively( int level, Element el )
	{
		StringBuffer sb = new StringBuffer();
		for ( int i = 0; i < level; i ++ )
		{
			sb.append("--");
		}
		String strPrefix = sb.toString();
			
		System.out.println( strPrefix + el.getName() );

		List lstItems = el.elements();
		for ( Object item : lstItems )
		{
			Element child_el = (Element)item;
			print_recursively( level + 1, child_el );
		}
				
	}
	
	private void parseHelper( InputStream is ) throws DocumentException
	{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);
		
		Element rootel = doc.getRootElement();
		
		print_recursively( 0, rootel );
		
		// List lst = rootel.selectNodes( "//project/dependencies/dependency" );
		List lst = doc.selectNodes( "/hibernate-configuration/session-factory/property" );
		
		
		for ( Object obj : lst )
		{
			Element el = (Element)obj;
			if ( el != null )
			{
				String strName = el.getName();
				System.out.println( el.getName() + " " + el.getText()  );
			}
		}
		
	}

	public void parseResource( String resourceName ) throws DocumentException
	{
		resourceName = "META-INF/maven/com.alprojects/thrdbouncing/pom.xml";
		resourceName = "hibernate.cfg.xml";
		// resourceName = "META-INF\\maven\\com.alprojects\\thrdbouncing\\pom.xml";
		ClassLoader classLdr = Thread.currentThread().getContextClassLoader();
		InputStream is = classLdr.getResourceAsStream(resourceName);
		parseHelper(is);
	}
	
	public void parseFile( String filenameName ) throws DocumentException, FileNotFoundException
	{
		// filenameName = "C:\\PROJECTS\\JAVA\\thread_bouncing\\thrdbouncing\\pom.xml";
		filenameName = "C:\\PROJECTS\\JAVA\\thread_bouncing\\thrdbouncing\\src\\main\\resources\\hibernate.cfg.xml";
					   // "C:\\PROJECTS\\JAVA\\thread_bouncing\\thrdbouncing\\pom.xml"
		// filenameName = "C:\\PROJECTS\\111\\pom.xml";
		FileInputStream fis = new FileInputStream(filenameName);
		parseHelper( fis );
	}
}
