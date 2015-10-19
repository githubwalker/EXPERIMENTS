package com.alprojects.hiber;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TheRoute {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="name")
	private String name;

	
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

	public void setBuses(Set<TheBus> buses) {
		this.buses = buses;
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

