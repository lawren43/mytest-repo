package cht.hioss.jpatutorial.testcase;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.List;

import javax.persistence.RollbackException;
import javax.security.auth.login.AccountExpiredException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cht.hioss.jpatutorial.dao.PersonDAO;
import cht.hioss.jpatutorial.dto.PersonDTO;
import cht.hioss.jpatutorial.model.Department;
import cht.hioss.jpatutorial.model.Person;
import cht.hioss.jpatutorial.service.PersonService;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
public class TestJpaWithSpring {

	private final static Logger logger = LoggerFactory.getLogger(TestJpaWithSpring.class);

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private PersonService personService;

	private static boolean initialized = false;

	@Before
	public void setUp() throws Exception {
		if (initialized == false) {
			logger.info("setUp():=============================");
			personService.initTestData();
			initialized = true;
		}

	}

	// the second person should fail to persist due to unique constraint on name
	// @Test(expected = Exception.class)
	public void testDuplicatePersonException() {

		logger.info("testDuplicatePersonException():============================================");
		logger.info("add p1");
		Person p1 = new Person();
		p1.setCountry("Canada");
		p1.setName("Merrick");
		personService.addPerson(p1);

		logger.info("add p2");
		Person p2 = new Person();
		p2.setCountry("Canada");
		p2.setName("Merrick");
		personService.addPerson(p2);

	}

	// any change occurs beyond transaction scope won't persist to database
	//@Test
	public void testTransactionScope() {
		logger.info("testTransactionScope():============================================");
		Person p1 = new Person();
		p1.setCountry("Canada");
		p1.setName("Merrick");
		personService.addPerson(p1);
		logger.info("add p1: " + p1.toString());

		p1.setName("Merrick 2");
		logger.info("change p1: " + p1.toString());

		Person p2 = personService.findById(p1.getId());
		
		logger.info("find p2: " + p2.toString());
		
		assertNotEquals(p1.getName(), p2.getName());

	}


	// @Test(expected = RollbackException.class)
	public void testRollbackByException() {
		logger.info("testRollbackByException():=====================================");

		Person p1 = new Person();
		p1.setCountry("Canada");
		p1.setName("Merrick");
		personService.addPersonRollbackByException(p1);

	}

	//@Test
	public void testRollbackByApi() {
		logger.info("testRollbackByException():=====================================");

		Person p1 = new Person();
		p1.setCountry("U.S.");
		p1.setName("Elvis");
		personService.addPersonRollbackByApi(p1);

	}


	// @Test
	public void testAdd() {
		logger.info("testAdd():================================================");
		Person p1 = new Person();
		p1.setCountry("Japan");
		p1.setName("Yoko");
		logger.info("person(before add):" + p1.toString());
		personService.addPerson(p1);
		logger.info("person(after add):" + p1.toString());

		assertNotNull(p1.getId());
	}

	// use jpql to find person by name
	// @Test
	public void testFindByNameQuery() {
		logger.info("testFindByNameQuery():================================================");
		Person p1 = new Person();
		p1.setCountry("Germany");
		p1.setName("Mark");

		personService.addPerson(p1);

		Person p2 = personService.findByName("Mark");

		logger.info("findByName(Mark):" + p2.toString());

		assertNotNull(p2);

	}

	// select two entities at the same time
	// @Test
	public void testQueryJoin2Entities() {
		logger.info("TestQueryJoin2Entities():================================================");
		List<Object[]> resultList = personService.findPersonAndDepartment();
		for (Object[] result : resultList) {

			Person person = (Person) result[0];
			Department department = (Department) result[1];

			logger.info("resultList: " + person.toString() + ", " + department.toString());
		}

	}

	// @Test
	public void testFindAllByNativeSQL() {
		logger.info("testFindAllByNativeSQL():================================================");

		List<Person> persons = personService.listAllByNativeSql();
		for (Person p : persons) {
			logger.info("list: " + p.toString());
			Department d = p.getDepartment();
			logger.info("list2: " + d.toString());
		}
	}

	// @Test
	public void testLock() {
		logger.info("testLock():================================================");

		Person p = personService.findByNameAndLock("Bob");
		logger.info("list: " + p.toString());

	}

	//@Test(expected = EmptyResultDataAccessException.class)
	public void testEmptyResultExcetpion() {
		logger.info("testEmptyResultException():================================================");
		Person p = personService.findByName("John Doe");
	}


	// if unchecked exception occurs, transaction will roll back automatically
	//@Test
	public void testUncheckedException() {
		logger.info("testUncheckedException():================================================");
		Person p1 = new Person();
		p1.setCountry("Japan");
		p1.setName("Kaido");
		logger.info("person(before add):" + p1.toString());
		try {
			personService.addPersonWithUncheckedException(p1);
		}
		catch (Exception e) {
			logger.info("catch unchecked exception:"+ e.toString());
		}
		logger.info("person(after add):" + p1.toString());

		Person p2 = personService.findById(p1.getId());
		
		assertNull(p2);
	}


	// if checked exception occurs, transaction will still commit
	//@Test
	public void testCheckedException() {
		logger.info("testCheckedException():================================================");
		Person p1 = new Person();
		p1.setCountry("France");
		p1.setName("Louis");
		logger.info("person(before add):" + p1.toString());
		try {
			personService.addPersonWithCheckedException(p1);
		}
		catch(Exception e) {
			logger.info("catch checked exception:"+ e.toString());
		}
		logger.info("person(after add):" + p1.toString());
		
		Person p2 = personService.findById(p1.getId());
		
		assertNotNull(p2);
	}

	// if checked exception occurs, transaction will still commit
	@Test
	public void testCheckedExceptionAdvice() {
		logger.info("testCheckedExceptionAdvice():================================================");
		Person p1 = new Person();
		p1.setCountry("Thai");
		p1.setName("Yai");
		logger.info("person(before add):" + p1.toString());
		try {
			personService.addPersonWithCheckedExceptionAdvice(p1);
		}
		catch(Exception e) {
			logger.info("catch checked exception:"+ e.toString());
		}
		logger.info("person(after add):" + p1.toString());
		
		Person p2 = personService.findById(p1.getId());
		
		assertNull(p2);
	}

	// entity association (like one-to-many or many-to-one) may lose beyond transaction scope  
	//@Test(expected = org.hibernate.LazyInitializationException.class)
	public void testTransactionScopeAssociation() {
		logger.info("testTransactionScopeAssociation():============================================");
		Person p1 = personService.findById(2);
		logger.info("find p1: " + p1.toString());
		
		Department d = p1.getDepartment();
		logger.info(d.toString());
	}

	// one should use DTO to transfer data instead of entity object
	//@Test
	public void testDTO() {
		logger.info("testDTO():================================================");
		List<PersonDTO> persons = personService.listPersonDTO();
		
		for (PersonDTO dto: persons) {
			logger.info("find PersonDTO: {}", dto.toString() );
		}
		assertNotEquals(0, persons.size());
	}

}
