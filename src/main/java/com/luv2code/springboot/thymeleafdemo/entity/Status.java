package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="status")
public class Status {

	// define fields

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;


	@OneToMany(mappedBy="status")
	private Set<Application> applications;

	public Status(int id, String name, Set<Application> applications) {
		this.id = id;
		this.name = name;
		this.applications = applications;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Application> getApplications() {
		return applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

	// define constructors

	public Status() {

	}


}











