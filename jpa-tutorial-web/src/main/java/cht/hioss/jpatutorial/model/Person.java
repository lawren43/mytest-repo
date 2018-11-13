package cht.hioss.jpatutorial.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name = "PERSON")
@NamedQueries(
	{ 
		@NamedQuery(name = "Person.findAll", query="SELECT p FROM Person p"),
		@NamedQuery(name = "Person.findByName", query = "Select p from Person p where p.name=:name") 
	}
)
@NamedNativeQueries(
	{
		@NamedNativeQuery(name = "Person.findAllBySQL", query ="SELECT * FROM Person ")
	}
)
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	private final static Logger logger = LoggerFactory.getLogger(Person.class);

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String country;

	private String name;

	//bi-directional many-to-one association to Department
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="department_id", referencedColumnName="id")
	private Department department;

	//bi-directional one-to-one association to PersonDetail
	@OneToOne(mappedBy="person", fetch = FetchType.LAZY)
	//@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="id", referencedColumnName="person_id")
	private PersonDetail personDetail;

	@Column(name="modify_date")
	private Date modifyDate;
	
	@Version
	private int version;

	
	
	public Person() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public PersonDetail getPersonDetail() {
		return this.personDetail;
	}

	public void setPersonDetail(PersonDetail personDetail) {
		this.personDetail = personDetail;
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
		return "Person [id=" + id + ", country=" + country + ", name=" + name 
				+ ", version="+ version +", modifyDate="+ modifyDate + "]";
	}

	
}