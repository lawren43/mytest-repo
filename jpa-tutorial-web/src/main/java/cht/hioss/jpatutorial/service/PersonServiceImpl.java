package cht.hioss.jpatutorial.service;

import java.util.List;
import java.util.Vector;

import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.security.auth.login.AccountExpiredException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cht.hioss.jpatutorial.dao.PersonDAO;
import cht.hioss.jpatutorial.dto.PersonDTO;
import cht.hioss.jpatutorial.model.Department;
import cht.hioss.jpatutorial.model.Person;
import cht.hioss.jpatutorial.model.PersonDetail;


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
	public Person findById(long id) {
		return this.personDAO.findOne(id);
	}

	@Override
	@Transactional
	public void removePerson(long id) {
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
	
	@Transactional
	public void addPersonWithUncheckedException(Person p) {
		personDAO.create(p);
		throw new RuntimeException();
	}

	@Transactional
	public void addPersonWithCheckedException(Person p) throws AccountExpiredException {
		personDAO.create(p);
		throw new AccountExpiredException();
	}

	@Transactional
	public void addPersonWithCheckedExceptionAdvice(Person p) throws AccountExpiredException {
		personDAO.create(p);
		throw new AccountExpiredException();
	}

	@Transactional
	public List<PersonDTO> listPersonDTO() {
		List<Person> persons = personDAO.findAll();
		Vector<PersonDTO> dtoList = new Vector<PersonDTO>();
		
		for (Person p : persons) {
			PersonDTO dto = new PersonDTO();
			dto.setId(p.getId());
			dto.setCountry(p.getCountry());
			dto.setName(p.getName());
			dto.setModifyDate(p.getModifyDate());
			dto.setVersion(p.getVersion());
			Department d = p.getDepartment();
			if (d != null) {
				dto.setDepartmentId(p.getDepartment().getId());
				dto.setDepartmentName(p.getDepartment().getName());
			}
			PersonDetail pd = p.getPersonDetail();
			if (pd != null) {
				dto.setPersonDetailTitle(pd.getTitle());
			}
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
}
