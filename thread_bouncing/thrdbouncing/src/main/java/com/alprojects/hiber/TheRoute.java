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

@Entity
@Table(name="the_route")
public class TheRoute {
	@Id
	@Column(name="route_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="number")
	private Integer number = 0;

	@OneToMany(mappedBy="theRoute",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
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
	
	/*
	public TheBus getBus() {
		return bus;
	}

	public void setBus(TheBus bus) {
		this.bus = bus;
	}
	*/
}

