package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="category")
public class Category {

	// define fields

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="photos", nullable = true, length = 64)
	private String photos;


	@OneToMany(mappedBy="category")
	private Set<Job> jobs;


	public Category(int id, String name, String photos, Set<Job> jobs) {
		this.id = id;
		this.name = name;
		this.photos = photos;
		this.jobs = jobs;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public Category(int id, String name, String photos) {
		this.id = id;
		this.name = name;
		this.photos = photos;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

// define constructors

	public Category() {

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

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
}











