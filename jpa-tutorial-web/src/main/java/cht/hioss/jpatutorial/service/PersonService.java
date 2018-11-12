package cht.hioss.jpatutorial.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cht.hioss.jpatutorial.model.Person;



public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person findById(int id);
	public void removePerson(int id);
	public Person findByName(String name);
	
	/* for test */
	public void addPersonRollbackByException(Person p);
	public void addPersonRollbackByApi(Person p);
	public List<Object[]> findPersonAndDepartment();
	public void initTestData();
	
	public List<Person> listAllByNativeSql();
	
	public Person findByNameAndLock(String name);
}
