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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cht.hioss.jpatutorial.model.Department;
import cht.hioss.jpatutorial.model.Person;


/**
 * @author lawren
 *
 */
public class TestJPA1 {

	final static Logger logger = LoggerFactory.getLogger(TestJPA1.class); 
	int person_id = 2;

	@Test
	public void testQuery() {

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-tutorial-unit");
		EntityManager entityManager = emFactory.createEntityManager();
		
		Person person = entityManager.find(Person.class, person_id);
		if (person != null) {
			logger.info("find person: " + person.toString());
			Department department = person.getDepartment();
			if (department != null) {
				logger.info("find department: " + department.toString());
			}

		} else {
			logger.info("find: 0");
		}

		entityManager.close();
		emFactory.close();
		
		assertNotNull(person);

	}

	@Test
	public void testAdd() {

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-tutorial-unit");

		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Person person = new Person();
		person.setCountry("U.S.");
		person.setName("Usher");
		try {
		entityManager.persist(person);
		logger.info("persist person:"+person.toString());
		}
		catch (Exception e) {
			logger.error("catch exception:",e);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
		
		assertNotNull(person);

	}

}
