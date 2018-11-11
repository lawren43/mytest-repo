/**
 * 
 */
package cht.hioss.jpatutorial.dao;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cht.hioss.jpatutorial.model.Person;
import cht.hioss.jpatutorial.model.PersonDetail;

/**
 * @author lawren
 * @param <T>
 *
 */
@Repository
public class PersonDAOImpl<T> extends AbstractJpaDAO<Person> implements PersonDAO {
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	public PersonDAOImpl() {
		setClazz(Person.class);
	}
	

	public Person findByName(String name) {
		Query query = entityManager.createNamedQuery("Person.findByName");
		query.setParameter("name", name);
		Person person = (Person) query.getSingleResult();
		
		return person;
	}

	public List<Person> findAllByNameQuery() {

		Query query = entityManager.createNamedQuery("Person.findAll");
		List<Person> personList = query.getResultList();
		for (Person item : personList) {
			System.out.println("personList: " + item.toString());
		}
		
		return personList;
	}
	
	public List<Object[]> findPersonAndDepartment() {
		Query query = entityManager.createQuery("select p, d from Person p, Department d where p.department = d");
		List<Object[]> resultList = query.getResultList();
		
		return resultList;

	}
	
	public List<Person> listAllByNativeSql() {
		List<Person> list = entityManager.createNativeQuery("select * from person where department_id is not null", Person.class)
				.getResultList();
		return list;
	}

	public void initTestData() {
		logger.info("clear person table");
		Query query = entityManager.createQuery("delete from Person p where p.id > 4");
		query.executeUpdate();
		
		Person p1 = entityManager.find(Person.class, 2);
		p1.setCountry("Canada");
		p1.setName("Bob");
		logger.info("reset person(2):"+p1.toString());

		Person p2 = new Person();
		p2.setCountry("U.S.");
		p2.setName("Usher");
		entityManager.persist(p2);
		logger.info("add person:"+p2.toString());

	}

}
