package com.alprojects.StringTest;

import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.alprojects.SpringAnnotations.DIConfiguration;
import com.alprojects.SpringAnnotations.MyActivity;
import com.alprojects.TestCloneable.MyCloneable;
import com.alprojects.gentest.ListPrinter;
import com.alprojects.gentest.ThePair;

/**
 * Hello world!
 *
 */
public class App {
	public static void testAnnotations() {
		AnnotationConfigApplicationContext ac = null;

		try {
			ac = new AnnotationConfigApplicationContext(DIConfiguration.class);
			MyActivity bn = ac.getBean(MyActivity.class);
			bn.preformAction("Fucking animals");
		} finally {
			if (ac != null)
				ac.close();
		}
		return;
	}

	static void testGenerics() {
		ThePair<Integer,String> pr = new ThePair<>( 1, "Fuck" );
		
		Integer o1 = pr.getFirst(); 
		String o2 = pr.getSecond();
		
		System.out.println( "o1 : " + o1 );
		System.out.println( "o2 : " + o2 );
		
		return;
	}
	
	public static void printListTest()
	{
		List<Integer> al = new ArrayList<Integer>();
		al.add( 1 );
		al.add( 2 );
		al.add( 3 );
		al.add( 4 );
		al.add( 1112 );
		
		ListPrinter.printList(al);
		
		return;
	}
	
	public static void testClone()
	{
		// EmailService ems = new EmailService();
		
		MyCloneable mc = new MyCloneable("123");
		
		MyCloneable mc2 = null;
		try {
			mc2 = mc.clone();
			System.out.println( "mc2 tag is : " + mc2.getTag() );
			return;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testIntersection() {
		List<String> c1 = new ArrayList<String>() {
			{
				this.add("1");
				this.add("2");
				this.add("3");
				this.add("4");
				this.add("5");
			}
		};

		List<String> c2 = new ArrayList<String>() {
			{
				this.add("4");
				this.add("5");
				this.add("6");
			}
		};

		List<String> tmp = new ArrayList<String>(c1);

		tmp.retainAll(c2);
		List<String> tmp2 = new ArrayList<String>(c1);
		tmp2.addAll(c2);
		tmp2.removeAll(tmp);

		int i = 0;
		for (String str : tmp2)
			System.out.println("Item " + Integer.toString(i++) + ": " + str);
		
		Iterator<String> it = tmp2.iterator();

		while ( it.hasNext() )
			System.out.println( it.next() );
		
		String itemAt1 = c2.get(1);
		
		System.out.println( "Item at index 1 : " + itemAt1 );
		
		return;
	}
	
	public static void printMap( Map<Integer,String> mp )
	{
		Set<Map.Entry<Integer,String>> es = mp.entrySet();
		
		for ( Map.Entry<Integer,String> entryItem : es )
			System.out.println( entryItem.getKey().toString() + ": " + entryItem.getValue() );
		
		Iterator< Map.Entry<Integer,String> > it = mp.entrySet().iterator();
		
		while ( it.hasNext() )
		{
			Map.Entry<Integer,String> entry = it.next();
			System.out.println( entry.getKey() + ": " + entry.getValue() );
		}
	}
	
	public static void testMap()
	{
		TreeMap<Integer,String> tm = new TreeMap<Integer, String>()
		{{ 
			put(1,"1");
			put(2,"2");
			put(3,"3");
			put(4,"4");
		}};
		
		TreeMap<Integer,String> tm1 = (TreeMap<Integer,String>)tm.clone();
				
		printMap(tm);
		
		boolean bEq = tm1.equals(tm);
		
		System.out.println( " tm1 == tm is : " + Boolean.toString(bEq) );
	}
	
	public static int testAtomic()
	{
		AtomicInteger ai = new AtomicInteger( 10 );
		int nNext = ai.getAndIncrement();
		int nNext1 = ai.incrementAndGet();
		int result = ai.addAndGet(15);
		return result;
	}
	
	public static void testReflection()
	{
		Class< DIConfiguration > cls = DIConfiguration.class;
		String strName = cls.getName();
		System.out.println( "class name : " + strName );
		
		int mod = cls.getModifiers();
		if ( Modifier.isPrivate(mod) )
			System.out.println( "class is private" );
		
		if ( Modifier.isProtected(mod))
			System.out.println( "class is protected" );
		
		if ( Modifier.isPublic(mod) )
			System.out.println( "class is public" );
		
		Class<?> clsSuper = cls.getSuperclass();
		System.out.println( "Superclass name is :" + clsSuper.getName() );
		
		System.out.println( "class interfaces :" );
		
		for ( Class<?> iface : cls.getInterfaces() )
			System.out.println( "Iface is : " + iface.getName() );
		
		return;
	}
	
	public static void testMySql() 
	{
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/new_schema";
		
		Connection conn = null;
		Statement stmt = null;		

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,"andrew","170032");
			stmt = conn.createStatement();
			String sql = "select * from new_table";
			ResultSet rs = stmt.executeQuery(sql);
			
			while( rs.next() )
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				System.out.println( "id = " + Integer.toString(id) + "; name = " + name );
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if ( stmt != null )
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if ( conn != null )
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public static void main(String[] args) {
		testMySql();

/*		
		testReflection();
		
		System.out.println(  testAtomic() );
		
		testMap();
		
		testIntersection();
		
		testClone();
		
		printListTest();
		// testAnnotations();
		testGenerics();
*/		

		/*
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();
		TextEditor te = (TextEditor) context.getBean("textEditor"); 
		te.spellCheck();
		context.close();
		*/
	}
}


