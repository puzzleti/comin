package dev.jdti.persistence.service;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dev.jdti.persistence.entities.Person;

public class PersonDao extends BaseDAO<Person> {

	private static final String PERSISTENCE_UNIT_NAME = "h2";

	public PersonDao() {
		super(PERSISTENCE_UNIT_NAME);
	}

	public void deletePersonById(long id) {
		Person p = this.read(id);
		this.delete(p);
	}
	
	public Person getPersonByEmail(String email)  {
		Person person = null;
		Session session = getEM().unwrap(Session.class);
        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Person> cr = cb.createQuery(Person.class);
        Root<Person> root = cr.from(Person.class);
        cr.select(root).where(cb.equal(root.get("email"), email));

        Query query = session.createQuery(cr);
        query.setMaxResults(1);
        @SuppressWarnings("unchecked")
		List<Person> result = query.getResultList();
        session.close();

        return result.size()>0 ? result.get(0) : person;
  }
	
	
}