/**
 * 
 */
package cht.hioss.jpatutorial.dao;

import org.springframework.stereotype.Repository;

import cht.hioss.jpatutorial.model.Person;

/**
 * @author lawren
 * @param <T>
 *
 */
@Repository
public class PersonDAOImpl<T> extends AbstractJpaDAO<Person> implements PersonDAO {

	public PersonDAOImpl() {
		setClazz(Person.class);
	}


}
