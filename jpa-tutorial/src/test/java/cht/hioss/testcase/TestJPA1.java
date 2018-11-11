/**
 * 
 */
package cht.hioss.testcase;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cht.hioss.jpatutorial.model.Department;
import cht.hioss.jpatutorial.model.Person;
import cht.hioss.jpatutorial.model.PersonDetail;

/**
 * @author lawren
 *
 */
public class TestJPA1 {

	final static Logger logger = LoggerFactory.getLogger(TestJPA1.class);
	static int global_person_id = 0;
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		logger.info("setUp():============================="); 
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-tutorial-unit");
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Person p1 = entityManager.find(Person.class, 2);
		p1.setCountry("Canada");

		logger.info("clear person table");
		Query query = entityManager.createQuery("delete from Person p where p.id > 4");
		query.executeUpdate();
		
		Person p2 = new Person();
		p2.setCountry("U.S.");
		p2.setName("Usher");
		entityManager.persist(p2);
		global_person_id = p2.getId();
		logger.info("add person:"+p2.toString());

		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
	}

	
	//@Test
	public void testQueryFromPerson() throws InterruptedException {

		logger.info("testQueryFromPerson():========================");
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-tutorial-unit");
		EntityManager entityManager = emFactory.createEntityManager();
		
		logger.info("begin find person");
		Person person = entityManager.find(Person.class, 2);
		if (person != null) {
			logger.info("find person: " + person.toString());
			
			logger.info("begin find department");
			Department department = person.getDepartment();
			if (department != null) {
				logger.info("find department: " + department.toString());
			}
			
			logger.info("begin find personDetail");
			PersonDetail pd = person.getPersonDetail();
			if (pd != null) {
				logger.info("find personDetail: " + pd.toString());
			}

		} else {
			logger.info("find: 0");
		}

		entityManager.close();
		emFactory.close();

		assertNotNull(person);

	}

	
	//@Test
	public void testQueryFromDepartment() {

		logger.info("testQueryFromDepartment():========================");
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-tutorial-unit");
		EntityManager entityManager = emFactory.createEntityManager();

		Department department = entityManager.find(Department.class, 1);
		if (department != null) {
			logger.info("find department: " + department.toString());

			List<Person> persons = department.getPersons();
			if (persons != null) {
				for (Person p : persons) {
					logger.info("find person: " + p.toString());
				}
			}

		} else {
			logger.info("find: 0");
		}

		entityManager.close();
		emFactory.close();

		assertNotNull(department);

	}

	//@Test
	public void testAdd() {
		logger.info("testAdd():========================");
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-tutorial-unit");

		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Person person = new Person();
		person.setCountry("France");
		person.setName("Frank");
		logger.info("before persist person:" + person.toString());
		entityManager.persist(person);
		logger.info("after persist person:" + person.toString());

		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();

		assertNotNull(person);
	}
	
	//@Test
	public void testUpdate() {
		
		logger.info("testUpdate():========================");
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-tutorial-unit");

		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Person p = entityManager.find(Person.class, 2);
		p.setCountry("Taiwan");
		
		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
	}

	//@Test
	public void testDelete() {
		logger.info("testDelete():========================");
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-tutorial-unit");

		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Person p = entityManager.find(Person.class, global_person_id );
		entityManager.remove(p);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
	}
	
	@Test
	public void testEmptyResult() {
		logger.info("testEmptyResult():========================");
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-tutorial-unit");

		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Person p = entityManager.find(Person.class, 2555 );
		
		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
		
		assertNull(p);
		
	}

}
