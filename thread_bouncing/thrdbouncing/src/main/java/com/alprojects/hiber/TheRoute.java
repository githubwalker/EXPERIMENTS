package com.alprojects.hiber;

import java.util.Set;







import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
@Table(name="the_route")
public class TheRoute implements IPersistObject {
	@Id
	@Column(name="route_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="number")
	private Integer number = 0;

	@OneToMany(mappedBy="theRoute",cascade={CascadeType.ALL},fetch=FetchType.LAZY,orphanRemoval=true)
	private Set<TheBus> buses;
	
	// private TheBus bus;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TheBus> getBuses() {
		return buses;
	}
	
	private static void setRoute2Busses( TheRoute rt, Set<TheBus> buses )
	{
		if ( buses != null )
		{
			for ( TheBus bs : buses )
			{
				bs.setTheRoute(rt);
			}
		}
	}

	public void setBuses(Set<TheBus> buses) {
		setRoute2Busses( null, this.buses );
		setRoute2Busses( this, buses );
		this.buses = buses;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public TheBus getBusByID( Long Id )
	{
		if ( Id == null )
			return null;
		
		for ( TheBus bs : this.buses )
		{
			if ( Id.equals(bs.getId()) )
				return bs;
		}
		
		return null;
	}
	
	private boolean internalRemoveBus( TheBus bus )
	{
		if (this.buses.remove(bus))
		{
			bus.setTheRoute(null);
			return true;
		}
		
		return false;
	}
	
	// http://stackoverflow.com/questions/3609653/hibernate-error-org-hibernate-nonuniqueobjectexception-a-different-object-with
	public void deleteBus( TheBus bus )
	{
		Session session = SessionFactoryHolder.getFactory().openSession();
		
		try
		{
			Transaction tr = session.beginTransaction();
			if ( this.buses.contains(bus) )
			{
				TheRoute manRoute = (TheRoute)session.merge(bus.getTheRoute());
				
				TheBus manBus = manRoute.getBusByID( bus.getId() );
				
				if ( manBus != null )
				{
					// manRoute.getBuses().
					// session.delete(object);
					session.delete(manBus);
					manRoute.internalRemoveBus( manBus );
				}
				
				// session.delete(bus);
				// this.buses.remove(bus);
			}
			
			tr.commit();
			
			if ( this.buses.contains(bus) )
			{
				this.buses.remove(bus);
				bus.setTheRoute(null);
			}
		}
		finally 
		{
			session.close();
		}
	}
	
	/*
	public TheBus getBus() {
		return bus;
	}

	public void setBus(TheBus bus) {
		this.bus = bus;
	}
	*/
}

