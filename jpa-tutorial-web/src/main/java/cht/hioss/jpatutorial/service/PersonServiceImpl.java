package cht.hioss.jpatutorial.service;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cht.hioss.jpatutorial.dao.PersonDAO;
import cht.hioss.jpatutorial.model.Person;


@Service
public class PersonServiceImpl implements PersonService {
	private final static Logger logger= LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Autowired
	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	@Transactional
	public void addPerson(Person p) {

		this.personDAO.create(p);

	}

	// throw exception will cause transaction to rollback
	@Override
	@Transactional
	public void addPersonRollbackByException(Person p) {
		
		this.personDAO.create(p);

		logger.info("throw runtime exception");
		throw new RollbackException("test runtime exception");

	}

	// call setRollbackOnly() to cause transaction to rollback
	@Override
	@Transactional
	public void addPersonRollbackByApi(Person p) {
		
		this.personDAO.create(p);

		logger.info("setRollbackOnly");
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.update(p);
	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		return this.personDAO.findAll();
	}

	@Override
	@Transactional
	public Person findById(int id) {
		return this.personDAO.findOne(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		Person person = this.personDAO.findOne(id);
		this.personDAO.delete(person);
	}
	
	public Person findByName(String name) {
		Person person = this.personDAO.findByName(name);
		return person;
	}


	public List<Person> listAllByNativeSql() {
		return this.personDAO.listAllByNativeSql();
	}
	
	@Override
	@Transactional
	public List<Object[]> findPersonAndDepartment() {
		
		return personDAO.findPersonAndDepartment();
	}
	
	@Override
	@Transactional
	public void initTestData() {
		personDAO.initTestData();
	}
	
	@Override
	@Transactional
	public Person findByNameAndLock(String name) {
		return personDAO.findByNameAndLock(name);
	}
}
