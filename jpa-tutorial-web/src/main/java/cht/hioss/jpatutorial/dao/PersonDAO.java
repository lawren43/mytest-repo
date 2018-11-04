/**
 * 
 */
package cht.hioss.jpatutorial.dao;

import java.util.List;

import cht.hioss.jpatutorial.model.Person;

/**
 * @author lawren
 *
 */
public interface PersonDAO {

	public Person findOne(long id);

	public List<Person> findAll();

	public void create(Person entity);
	
	public Person update(Person entity);

	public void delete(Person entity);

	public void deleteById(long entityId);


}
