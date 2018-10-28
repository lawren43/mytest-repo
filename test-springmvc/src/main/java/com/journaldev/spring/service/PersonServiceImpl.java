package com.journaldev.spring.service;

import java.util.List;

import javax.persistence.RollbackException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.model.Person;

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
		
		try {
		this.personDAO.addPerson(p);

		//logger.info("setRollbackOnly");
		//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		
		//logger.info("throw runtime exception");
		//throw new RollbackException("test runtime exception");
		}
		catch (Exception e) {
			logger.info("catch exception: {}", e);
			throw e;
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		return this.personDAO.listPersons();
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.personDAO.getPersonById(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.personDAO.removePerson(id);
	}

}
