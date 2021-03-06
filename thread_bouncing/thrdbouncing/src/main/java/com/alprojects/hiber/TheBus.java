package com.alprojects.hiber;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// DOUBLE DIRECTION
// HIBERNATE_STUDY
// http://www.spring-source.ru/docs_simple.php?type=manual&theme=docs_simple&docs_simple=chap04_p03
//http://javatalks.ru/topics/30488
// http://stackoverflow.com/questions/14111607/manytomanymappedby-foo

// https://en.wikibooks.org/wiki/Java_Persistence/ManyToOne
// https://howtoprogramwithjava.com/hibernate-manytomany-unidirectional-bidirectional/
// http://habrahabr.ru/post/29694/
// eager fetch
// http://www.concretepage.com/hibernate/fetch_hibernate_annotation

@Entity
@Table(name="the_bus")
public class TheBus implements IPersistObject {
	@Id
	@Column(name="bus_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="number")
	private String number = "default";

	@ManyToMany(cascade={CascadeType.ALL},			
			fetch=FetchType.LAZY)
	@JoinTable(
			name="the_busdriver", 
			joinColumns=@JoinColumn(name="bus_id"), 
			inverseJoinColumns=@JoinColumn(name="driver_id"))
	private Set<TheDriver> drivers = new HashSet<TheDriver>();
	
	// @ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="route_id")
	private TheRoute theRoute; // bus belongs to this route

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public TheRoute getTheRoute() {
		return theRoute;
	}
	public void setTheRoute(TheRoute theRoute) {
		this.theRoute = theRoute;
	}
	
	public Set<TheDriver> getDrivers() {
		return drivers;
	}
	public void setDrivers(Set<TheDriver> drivers) {
		this.drivers = drivers;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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

