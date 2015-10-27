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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="the_driver")
public class TheDriver implements IPersistObject {
	@Id
	@Column(name="driver_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="surname")
	private String surname;
	@Column(name="age")
	private Integer age;
	
	@ManyToMany(mappedBy="drivers",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
/*	
	@JoinTable(
			name="the_busdriver", 
			joinColumns=@JoinColumn(name="driver_id"), 
			inverseJoinColumns=@JoinColumn(name="bus_id"))
*/			
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
	
	public Set<TheBus> getBuses() {
		return buses;
	}
	public void setBuses(Set<TheBus> buses) {
		this.buses = buses;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
