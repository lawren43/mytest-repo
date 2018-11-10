package cht.hioss.jpatutorial.service;

import java.util.List;

import cht.hioss.jpatutorial.model.Person;



public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
	public Person getPersonByName(String name);
	
	/* for test */
	public void addPersonRollbackByException(Person p);
	public void addPersonRollbackByApi(Person p);
	
	public List<Object> findPersonAndDepartment();
}
