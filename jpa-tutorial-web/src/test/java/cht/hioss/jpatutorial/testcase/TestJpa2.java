package cht.hioss.jpatutorial.testcase;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cht.hioss.jpatutorial.dao.PersonDAO;
import cht.hioss.jpatutorial.model.Person;
import cht.hioss.jpatutorial.service.PersonService;


@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)

public class TestJpa2 {

	private final static Logger logger = LoggerFactory.getLogger(TestJpa2.class);
	
	@Autowired
    private PersonDAO personDAO; 
	
	@Autowired
    private PersonService personService; 

	@Before
	public void setUp() throws Exception {
	}

	
	//@Test
	//@Transactional
	public void testDuplicatePerson() {
		
		try {
			
		logger.info("add p1");
		Person p1 = new Person();
		p1.setCountry("Canada");
		p1.setName("Merrick");
		personService.addPerson(p1);

		logger.info("add p2");
		Person p2 = new Person();
		p2.setCountry("Canada");
		p2.setName("Merrick");
		//personService.addPerson(p2);
		}
		catch (Exception e) {
			logger.error("catch exception: {} ", e);
		}
		
	}
	
	@Test
	@Transactional
	public void testDTO() {
		logger.info("add p1");
		Person p1 = new Person();
		p1.setCountry("Canada");
		p1.setName("Merrick");
		personService.addPerson(p1);
		
		p1.setName("Merrick2");
		logger.info("change p1:"+p1.toString());

		logger.info("add p2");
		Person p2 = new Person();
		p2.setCountry("Canada");
		p2.setName("Flix");
		personService.addPerson(p2);

	}
	
	
	//@Test
	//@Transactional
	public void testUncheckedException() {
		logger.info("start");
		
		
		Person p1 = new Person();
		p1.setCountry("Canada");
		p1.setName("Merrick");
		//personDAO.addPerson(p1);
		personService.addPerson(p1);
		
		//logger.info("setRollbackOnly");
		//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		
		//logger.info("throw runtime exception");
		//throw new RuntimeException("test runtime exception");
		//throw new RollbackException("test runtime exception");
		
	}
	
	//@Test
	@Transactional
	public void test2() {
		Person p1 = new Person();
		p1.setCountry("Canada");
		p1.setName("Merrick");
		personService.addPerson(p1);

		
		Person p2 = new Person();
		p2.setCountry("Canada");
		p2.setName("Merrick2");
		personService.addPerson(p2);
		
		List<Person> personList = personService.listPersons();
		for (Person item : personList) {
			System.out.println("personList: " + item.toString());
		}

	}
	

}
