package com.alprojects.hiber;

import java.util.HashSet;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

// loading:
// https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/objectstate.html#objectstate-loading

// child with toys
// http://www.javacodegeeks.com/2012/08/hibernate-lazyeager-loading-example.html

// Spring DAO
// http://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/orm.html#orm-hibernate-straight

// https://www.youtube.com/watch?v=TejT8H81aVI
// https://howtoprogramwithjava.com/category/hibernate-2/

// http://blog.leodev.ru/java-dynamic-proxy/

// best practices to pull out lazy collection
// http://stackoverflow.com/questions/19928568/hibernate-best-practice-to-pull-all-lazy-collections

// DAO
// http://www.studytrails.com/frameworks/spring/spring-hibernate-jpa.jsp

// lection
// http://www.youtube.com/watch?v=r8SlsJjmm8o

public class BusPark 
// extends HibernateDaoSupport 
{
	// private Session session = null;
	// private SessionFactory sessionFactory = null;
	private Set<TheRoute> routes = new HashSet<TheRoute>();
	// private HibernateTemplate hibernateTemplate; 
	
	/*
	static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure( "hibernate.cfg.xml" );
	    
	    ServiceRegistry serviceRegistry = 
	    		new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();	    
	    
	    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
	
	synchronized public SessionFactory getServiceFactory() {
		if (sessionFactory == null)
			sessionFactory = createSessionFactory();

		return sessionFactory;
	}
	
	public Session getSession() {
		if ( session == null )
			session = this.getServiceFactory().openSession();
		return session;
	}
	*/
	

	public Set<TheRoute> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<TheRoute> routes) {
		this.routes = routes;
	}
	
	public void printPark() {
		for (TheRoute rt : routes) {
			System.out.println("Route " + rt.getName());
			for (TheBus bs : rt.getBuses()) {
				System.out.print("Bus numbers:" + bs.getNumber());
				System.out.print("; drivers: [ ");
				for (TheDriver dr : bs.getDrivers()) {
					System.out.print("Name: " + dr.getName() + "; Surname: "
							+ dr.getSurname() + "; Age: " + dr.getAge());
				}
				System.out.print("]; ");
				System.out.println();
			}
		}
	}
	
	public void persist() {
		// Session session = this.getServiceFactory().openSession();
		// Session session = getSession();
		Session session = SessionFactoryHolder.getFactory().openSession();

		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			
			for (TheRoute rt : routes) {
				// session.persist(rt);
				// Utils.persistOrMerge(session, rt);
				rt = Utils.mrg(session, rt);
				/*
				for (TheBus bs : rt.getBuses()) {
					// session.persist(bs);
					// Utils.persistOrMerge( session, bs );
					Utils.mrg(session, bs);
					for (TheDriver dr : bs.getDrivers()) {
						// session.persist(dr);
						// Utils.persistOrMerge( session, dr );
						Utils.mrg(session, dr);
					}
				}
				*/
			}

			trans.commit();
		} finally {
			session.flush();
			session.close();
		}
	}

	public void load() {
		// Session session = this.getServiceFactory().openSession();
		// Session session = this.getSession();
		Session session = SessionFactoryHolder.getFactory().openSession();

		try {

			Iterator it = session.createQuery("from TheRoute").iterate();
			
			this.routes = new HashSet<TheRoute>();

			while (it.hasNext()) {
				TheRoute rt = (TheRoute) it.next();
				this.routes.add(rt);
				Set<TheBus> buses = rt.getBuses();
				for (TheBus bus : buses) {
					Set<TheDriver> drivers = bus.getDrivers();
					for (TheDriver dr : drivers) {
						dr.getName();
					}
				}
			}
		} finally {
			// session.clear();
			// session.close();
			// closeFactory();
			session.close();
		}
	}

	/*
	public void closeFactory() {
		if (this.session != null) {
			session.close();
			session = null;
		}
		
		if (this.sessionFactory != null) {
			this.sessionFactory.close();
			this.sessionFactory = null;
		}
	}
	*/

	/*
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
	    this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	*/	
	
	public void loadWholePark() {
		// HibernateTemplate ht = this.getHibernateTemplate();
		// Session session = this.getServiceFactory().openSession();
		// Session session = this.getSession();
		Session session = SessionFactoryHolder.getFactory().openSession();

		try {
			Iterator it = session.createQuery("from TheRoute").iterate();
			session.byId(TheRoute.class);
			// List<TheRoute> rts = ht.<TheRoute>loadAll(TheRoute.class);
			
			session.beginTransaction();

			this.routes = new HashSet<TheRoute>();

			while (it.hasNext()) {
				// this.routes.add(rt);
				TheRoute rt = (TheRoute) it.next();
				this.routes.add(rt);
			}
		} finally {
			session.flush();
			session.close();
		}
	}

	public boolean deleteRoute(TheRoute route) {
		// Session session = this.getSession();
		Session session = SessionFactoryHolder.getFactory().openSession();
		
		
		Transaction trans = null;
		boolean bOk = false; 

		try
		{
			trans = session.beginTransaction();
			session.delete(route);
			trans.commit();
			bOk = this.routes.remove(route);
		} 
		finally 
		{
			session.close();
		}
		
		return bOk;
	}
}

