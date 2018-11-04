/**
 * 
 */
package cht.hioss.jpatutorial.dao;

import org.springframework.stereotype.Repository;

import cht.hioss.jpatutorial.model.PersonDetail;

/**
 * @author lawren
 * @param <T>
 *
 */
@Repository
public class PersonDetailDAOImpl<T> extends AbstractJpaDAO<PersonDetail> implements PersonDetailDAO {

	public PersonDetailDAOImpl() {
		setClazz(PersonDetail.class);
	}


}
