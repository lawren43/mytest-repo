package cht.hioss.jpatutorial.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the person_details database table.
 * 
 */
@Entity
@Table(name="person_details")
@NamedQuery(name="PersonDetail.findAll", query="SELECT p FROM PersonDetail p")
public class PersonDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String title;

	//bi-directional one-to-one association to Person
	@OneToOne(mappedBy="personDetail")
	private Person person;

	public PersonDetail() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "PersonDetail [id=" + id + ", title=" + title + ", person=" + person.getId() + "]";
	}

}