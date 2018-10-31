package cht.hioss.jpatutorial.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;

	//bi-directional many-to-one association to Person
	//@OneToMany(mappedBy="department", fetch = FetchType.LAZY)
	//private List<Person> persons;

	public Department() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
/*
	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setDepartment(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setDepartment(null);

		return person;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", persons no=" + (persons==null? "null":"n") + "]";
	}

*/
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	
}