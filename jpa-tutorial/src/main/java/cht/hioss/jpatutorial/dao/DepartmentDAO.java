/**
 * 
 */
package cht.hioss.jpatutorial.dao;

import java.util.List;

import cht.hioss.jpatutorial.model.Department;

/**
 * @author lawren
 *
 */
public interface DepartmentDAO {

	public Department findOne(long id);

	public List<Department> findAll();

	public void create(Department entity);
	
	public Department update(Department entity);

	public void delete(Department entity);

	public void deleteById(long entityId);

}
