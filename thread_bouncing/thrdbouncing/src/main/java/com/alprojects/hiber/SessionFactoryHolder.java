package com.alprojects.hiber;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryHolder {
	
	static SessionFactory sf = null;
	
	private static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure( "hibernate.cfg.xml" );
	    
	    ServiceRegistry serviceRegistry = 
	    		new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();	    
	    
	    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
	
	
	synchronized static public SessionFactory getFactory() 
	{
		if (sf == null)
			sf = createSessionFactory();
		
		return sf;
	}
	
	synchronized static public void closeFactory()
	{
		if ( sf != null )
		{
			sf.close();
			sf = null;
		}
	}
}

