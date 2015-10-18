package com.alprojects.hiber;

import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class TestPersist {
	
	private SessionFactory sessionFactory;
	
	static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    
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
		bus1.setName("bus1");
		TheBus bus2 = new TheBus();
		bus1.setName("bus2");
		
		// route1
		TheRoute route1 = new TheRoute();
		route1.setBuses( new HashSet<TheBus>( Arrays.asList( bus1, bus2 ) ) );
	
		route1.setName("route1");
		
		// drivers
		
		TheDriver driver1 = new TheDriver();
		driver1.setName("driver1");
		// driver1.setBuses( new HashSet<TheBus>( Arrays.asList( bus1, bus2 ) ) );
		
		TheDriver driver2 = new TheDriver();
		driver2.setName("driver2");
		// driver2.setBuses( new HashSet<TheBus>( Arrays.asList( bus2 ) ) );


		// link buses & drivers
		
		Utils.LinkBusAndDriver(bus1, driver1);
		Utils.LinkBusAndDriver(bus2, driver1);
		
		Utils.LinkBusAndDriver(bus2, driver2);
		
		
		// persisting
		
		// SessionFactory sf = new Hibernate
		// SessionFactory = new Configuration().configure().buildSessionFactory(serviceRegistry);
		
		SessionFactory sf = this.getServiceFactory();

		Session session = sf.openSession();
		
		Transaction trans = null;
		
		try
		{
			trans = session.beginTransaction();
			session.save(route1);
			session.save(bus1);
			session.save(bus2);
			session.save(driver1);
			session.save(driver2);
			trans.commit();
		}
		catch (HibernateException e)
		{
			
		}
		finally
		{
			session.close();
		}
	}

	public void performTests()
	{
		persistTest();
	}
}
