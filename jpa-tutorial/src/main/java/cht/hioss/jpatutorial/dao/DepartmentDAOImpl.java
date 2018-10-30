/**
 * 
 */
package cht.hioss.jpatutorial.dao;

import org.springframework.stereotype.Repository;

import cht.hioss.jpatutorial.model.Department;

/**
 * @author lawren
 * @param <T>
 *
 */
@Repository
public class DepartmentDAOImpl<T> extends AbstractJpaDAO<Department> implements DepartmentDAO {

	public DepartmentDAOImpl() {
		setClazz(Department.class);
	}

}
