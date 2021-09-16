package dev.jdti.persistence.service;

import dev.jdti.persistence.entities.Password;


public class PasswdDao extends BaseDAO<Password>{
	
	private static final String PERSISTENCE_UNIT_NAME = "h2";

	public PasswdDao() {
		super(PERSISTENCE_UNIT_NAME);
	}

	public void deletePasswdById(long id) {
		Password p = this.read(id);
		this.delete(p);
	}

}
