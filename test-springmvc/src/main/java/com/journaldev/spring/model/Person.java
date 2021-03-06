package com.journaldev.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author pankaj
 *
 */
@Entity
@Table(name = "PERSON")
@NamedQueries(
	{ 
		@NamedQuery(name = "list all person", query = "Select p from Person p"),
		@NamedQuery(name = "find person by name", query = "Select p from Person p where p.name=:name") 
	}
)
public class Person {

	@Transient
	private final static Logger logger = LoggerFactory.getLogger(Person.class);

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String country;
	
	@Column(name="modify_date")
	private Date modifyDate;
	
	@Version
	private int version;

	public Person() {
		super();
	}

	public Person(int id, String name, String country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@PreUpdate
	@PrePersist
	public void preAddOrUpdate() {
		this.modifyDate = new Date();
		logger.info("preAddOrUpdate() update modifyDate to :"+ this.modifyDate);
	}


	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", country=" + country + ", version=" + version;
	}
	
}
