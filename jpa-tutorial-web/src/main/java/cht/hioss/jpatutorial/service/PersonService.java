package cht.hioss.jpatutorial.service;

import java.util.List;

import javax.security.auth.login.AccountExpiredException;

import org.springframework.transaction.annotation.Transactional;

import cht.hioss.jpatutorial.model.Person;



public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person findById(long id);
	public void removePerson(long id);
	public Person findByName(String name);
	
	/* for test */
	public void addPersonRollbackByException(Person p);
	public void addPersonRollbackByApi(Person p);
	public List<Object[]> findPersonAndDepartment();
	public void initTestData();
	
	public List<Person> listAllByNativeSql();
	
	public Person findByNameAndLock(String name);

	public void addPersonWithUncheckedException(Person p);
	public void addPersonWithCheckedException(Person p) throws AccountExpiredException;
	public void addPersonWithCheckedExceptionAdvice(Person p) throws AccountExpiredException;
}
