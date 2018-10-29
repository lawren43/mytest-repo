package com.journaldev.spring.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	//@PersistenceContext(unitName= "LOCAL_PERSISTENCE")
	@PersistenceContext
	EntityManager entityManager;
/*
    protected EntityManager getEntityManager(){
        return this.entityManager;
    }
 
*/
	@Override
	public void addPerson(Person p) {
		entityManager.persist(p);
		logger.info("Person saved successfully, Person Details="+p);
		//logger.info("setRollbackOnly");
		//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		
		//logger.info("throw runtime exception");
		//throw new RuntimeException("test runtime exception");

	}

	@Override
	public void updatePerson(Person p) {
		entityManager.merge(p);
		logger.info("Person updated successfully, Person Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		
		List<Person> personsList = entityManager.createQuery("from Person").getResultList();
		for(Person p : personsList){
			logger.info("Person List::"+p);
		}
		return personsList;
	}

	@Override
	public Person getPersonById(int id) {
		Person p = (Person) entityManager.find(Person.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

	@Override
	public void removePerson(int id) {
		Person p = (Person) entityManager.find(Person.class, new Integer(id));
		if(null != p){
			entityManager.remove(p);
		}
		logger.info("Person deleted successfully, person details="+p);
	}

}
