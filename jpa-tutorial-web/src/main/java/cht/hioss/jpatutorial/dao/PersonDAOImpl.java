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


}
