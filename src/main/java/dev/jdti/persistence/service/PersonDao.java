package dev.jdti.persistence.service;

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
}