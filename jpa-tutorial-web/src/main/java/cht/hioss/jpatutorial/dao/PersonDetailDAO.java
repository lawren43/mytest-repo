/**
 * 
 */
package cht.hioss.jpatutorial.dao;

import java.util.List;

import cht.hioss.jpatutorial.model.PersonDetail;

/**
 * @author lawren
 *
 */
public interface PersonDetailDAO {

	public PersonDetail findOne(long id);

	public List<PersonDetail> findAll();

	public void create(PersonDetail entity);
	
	public PersonDetail update(PersonDetail entity);

	public void delete(PersonDetail entity);

	public void deleteById(long entityId);

}
