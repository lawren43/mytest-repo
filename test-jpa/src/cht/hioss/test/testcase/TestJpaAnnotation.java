package cht.hioss.test.testcase;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cht.hioss.test.entity.Person;

public class TestJpaAnnotation {
	@PersistenceContext
	static EntityManager entityManager;

	public static void main(String[] args) {
		Query query = entityManager.createQuery("from Person p");
		List<Person> personList = query.getResultList();
		for (Person item : personList) {
			System.out.println("personList: " + item.toString());
		}

	}

}
