package com.alprojects.hiber;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class TheDriver {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="name")
	private String name;
	
	private Set<TheBus> buses = new HashSet<TheBus>();
	
	
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
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="the_driver")
	public Set<TheBus> getBuses() {
		return buses;
	}
	public void setBuses(Set<TheBus> buses) {
		this.buses = buses;
	}
}
