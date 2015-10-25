package com.alprojects.hiber;

import java.util.Arrays;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

// https://github.com/zzantozz/testbed/tree/master/hibernate-with-xml-mappings
// http://www.journaldev.com/3793/hibernate-tutorial-with-example-projects
// http://www.journaldev.com/2882/hibernate-tutorial-for-beginners-using-xml-annotations-and-property-configurations

public class TestPersist {
	
	private SessionFactory sessionFactory;
	
	static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure( "hibernate.cfg.xml" );
	    
	    // ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
	    //        configuration.getProperties()). buildServiceRegistry();
	    ServiceRegistry serviceRegistry = 
	    		new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();	    
	    
	    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
	
	synchronized public SessionFactory getServiceFactory()
	{
		if (sessionFactory == null)
			sessionFactory = createSessionFactory();
		
		return sessionFactory;
	}
	
	private void persistTest()
	{
		// buses
		TheBus bus1 = new TheBus();
		bus1.setNumber("bus1");
		TheBus bus2 = new TheBus();
		bus2.setNumber("bus2");
		
		// route1
		TheRoute route1 = new TheRoute();
		route1.setBuses( new HashSet<TheBus>( Arrays.asList( bus1, bus2 ) ) );
	
		route1.setName("route1");
		
		// drivers
		
		TheDriver driver1 = new TheDriver();
		driver1.setName("driver1");
		driver1.setSurname("driver1_surname");
		driver1.setAge(35);
		// driver1.setBuses( new HashSet<TheBus>( Arrays.asList( bus1, bus2 ) ) );
		
		TheDriver driver2 = new TheDriver();
		driver2.setName("driver2");
		driver2.setSurname("driver2_surname");
		driver2.setAge(41);
		
		Utils.LinkBusAndDriver(bus1, driver1);
		Utils.LinkBusAndDriver(bus2, driver1);
		
		Utils.LinkBusAndDriver(bus2, driver2);
		
		SessionFactory sf = this.getServiceFactory();

		Session session = sf.openSession();
		
		Transaction trans = null;
		
		try
		{
			trans = session.beginTransaction();
			
			session.persist(route1);
			session.persist(bus1);
			session.persist(bus2);
			session.persist(driver1);
			session.persist(driver2);
			
			trans.commit();
		}
		finally
		{
			session.close();
			sf.close();
		}
	}
	
	private void loadTest()
	{
		BusPark bp = new BusPark();
		bp.load();
		bp.closeFactory();
		bp.printPark();
		return;
	}

	public void performTests()
	{
		loadTest();
		// persistTest();
	}
}
