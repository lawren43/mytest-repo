/**
 * 
 */
package my.test.testcase.standalone;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.journaldev.spring.model.Person;

/**
 * @author matthew
 *
 */
public class TestJpaStandalone {

	private static int person_id = 0;

	public static void createPerson() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("LOCAL_PERSISTENCE");

		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Person person = new Person();
		person.setName("Elvin");
		person.setCountry("Denmark");

		entityManager.persist(person);

		person_id = person.getId();

		System.out.println("create: " + person.toString());
		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
	}

	public static void findPerson() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("LOCAL_PERSISTENCE");

		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Person person = entityManager.find(Person.class, person_id);
		if (person != null) {
			System.out.println("find: " + person.toString());
		} else {
			System.out.println("find: 0");
		}

		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
	}

	public static void updatePerson() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("LOCAL_PERSISTENCE");

		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Person person = entityManager.find(Person.class, person_id);
		person.setName("Elvin-x");
		entityManager.merge(person);
		System.out.println("update: " + person.toString());

		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
	}

	public static void findPersonByName() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("LOCAL_PERSISTENCE");

		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createNamedQuery("find person by name");
		query.setParameter("name", "Adam");
		List<Person> personList = query.getResultList();
		for (Person item : personList) {
			System.out.println("findByName: " + item.toString());
		}

		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
	}

	public static void deletePerson() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("LOCAL_PERSISTENCE");

		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Person person = entityManager.find(Person.class, person_id);
		entityManager.remove(person);
		System.out.println("delete: " + person.toString());

		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
	}

	public static void listByJPQL() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("LOCAL_PERSISTENCE");
		EntityManager entityManager = emFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Query query = entityManager.createQuery("from Person p");
		List<Person> personList = query.getResultList();
		for (Person item : personList) {
			System.out.println("personList: " + item.toString());
		}

		query = entityManager.createQuery("select p.name from Person p");
		List<String> resultList = query.getResultList();
		for (String item : resultList) {
			System.out.println("resultList: " + item.toString());
		}

		transaction.commit();
		entityManager.close();
		emFactory.close();

	}
	
	public static void findByCriteria() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("LOCAL_PERSISTENCE");
		EntityManager entityManager = emFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	   CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
	   Root<Person> from = criteriaQuery.from(Person.class);

		   System.out.println("Select all records");
		   CriteriaQuery<Object> select = criteriaQuery.select(from);
		   TypedQuery<Object> typedQuery = entityManager.createQuery(select);
		   List<Object> resultList = typedQuery.getResultList();

		   
			for (Object item : resultList) {
				System.out.println("personList: " + ((Person)item).toString());
			}

			  System.out.println("Select all records by follow ordering");
			   CriteriaQuery<Object> select1 = criteriaQuery.select(from);
			   select1.orderBy(criteriaBuilder.asc(from.get("id")));
			   TypedQuery<Object> typedQuery1 = entityManager.createQuery(select);
			   List<Object> resultList1 = typedQuery1.getResultList();

				for (Object item : resultList1) {
					System.out.println("personList: " + ((Person)item).toString());
				}
		   
			entityManager.close();
			emFactory.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * createPerson(); 
		 * findPerson(); 
		 * updatePerson(); 
		 * findPerson();
		 * deletePerson(); 
		 * findPerson(); 
		 * listByJPQL();
		findPersonByName();
		 */
		
		
		findByCriteria();
	}

}
