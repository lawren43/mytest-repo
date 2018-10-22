/**
 * 
 */
package my.test.testcase.standalone;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.journaldev.spring.model.Person;

/**
 * @author matthew
 *
 */
public class TestJpaStandalone {

	private static int person_id = 0;
	
	public static void createPerson() {
	      EntityManagerFactory emFactory = Persistence.createEntityManagerFactory( "LOCAL_PERSISTENCE" );
	      
	      EntityManager entityManager = emFactory.createEntityManager( );
	      entityManager.getTransaction( ).begin( );
	      
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
	      EntityManagerFactory emFactory = Persistence.createEntityManagerFactory( "LOCAL_PERSISTENCE" );
	      
	      EntityManager entityManager = emFactory.createEntityManager( );
	      entityManager.getTransaction().begin( );
	      
	      Person person = entityManager.find(Person.class, person_id);
	      if (person != null ) {
	    	  System.out.println("find: " + person.toString());
	      }
	      else {
	    	  System.out.println("find: 0" );
	      }

	      entityManager.getTransaction().commit();
	      entityManager.close();
	      emFactory.close();
	}
	
	public static void updatePerson() {
	      EntityManagerFactory emFactory = Persistence.createEntityManagerFactory( "LOCAL_PERSISTENCE" );
	      
	      EntityManager entityManager = emFactory.createEntityManager( );
	      entityManager.getTransaction( ).begin( );

	      Person person = entityManager.find(Person.class, person_id);
	      person.setName("Elvin-x");
	      entityManager.merge(person);
	      System.out.println("update: " + person.toString());
	      
	      entityManager.getTransaction().commit();
	      entityManager.close();
	      emFactory.close();
	}
	
	public static void deletePerson() {
	      EntityManagerFactory emFactory = Persistence.createEntityManagerFactory( "LOCAL_PERSISTENCE" );
	      
	      EntityManager entityManager = emFactory.createEntityManager( );
	      entityManager.getTransaction( ).begin( );
	      
	      Person person = entityManager.find(Person.class, person_id);
	      entityManager.remove(person);
	      System.out.println("delete: " + person.toString());
	      
	      entityManager.getTransaction().commit();
	      entityManager.close();
	      emFactory.close();
	}
	
	public static void listByJPQL() {
	      EntityManagerFactory emFactory = Persistence.createEntityManagerFactory( "LOCAL_PERSISTENCE" );
	      EntityManager entityManager = emFactory.createEntityManager( );
	      entityManager.getTransaction( ).begin( );
	      
	      Query query = entityManager.createQuery("from Person p");
	      List<Person> personList =  query.getResultList();
	      for (Person item: personList) {
	    	  System.out.println("personList: " + item.toString());
	      }
	      
	      query = entityManager.createQuery("select p.name from Person p");
	      List<String> resultList =  query.getResultList();
	      for (String item: resultList) {
	    	  System.out.println("resultList: " + item.toString());
	      }
	      
	      entityManager.getTransaction().commit();
	      entityManager.close();
	      emFactory.close();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	/*
		createPerson();
		findPerson();
		updatePerson();
		findPerson();
		deletePerson();
		findPerson();
		*/
		listByJPQL();

	}

}
