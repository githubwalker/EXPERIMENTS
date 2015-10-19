package com.alprojects.hiber;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


// https://en.wikibooks.org/wiki/Java_Persistence/ManyToOne
// https://howtoprogramwithjava.com/hibernate-manytomany-unidirectional-bidirectional/

@Entity
public class TheBus {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="name")
	private String name;
	
	private Set<TheDriver> drivers = new HashSet<TheDriver>();
	
	@ManyToOne
	@JoinColumn(name="route_id", nullable=false, updatable=false)
	private String theRoute; // bus belongs to this route

	
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
	
	public String getTheRoute() {
		return theRoute;
	}
	public void setTheRoute(String theRoute) {
		this.theRoute = theRoute;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="the_busdriver", 
			joinColumns=@JoinColumn(name="bus_id"), 
			inverseJoinColumns=@JoinColumn(name="driver_id"))
	public Set<TheDriver> getDrivers() {
		return drivers;
	}
	public void setDrivers(Set<TheDriver> drivers) {
		this.drivers = drivers;
	}
}


/*
Bus:

id
route_id
name


Route:
id
name
 */


// spring mvc
// http://crunchify.com/simplest-spring-mvc-hello-world-example-tutorial-spring-model-view-controller-tips/

