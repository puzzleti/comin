package dev.jdti.persistence.service;

import java.util.List;

import dev.jdti.persistence.entities.Password;

public class PasswordService {
	
	private PasswdDao passwordDao = new PasswdDao();

	public PasswordService() {
		super();
	}

	public List<Password> getAllPasswords() {
		return passwordDao.findAll();
	}
	
	public Password findPasswordById(long id) {
		return passwordDao.read(id);
	}
	
	public void savePassword(Password password) {
		passwordDao.create(password);
	}
	
	public void deletePassword(Password password) {
		passwordDao.delete(password);
	}
	
	public void deletePasswordById(long id) {
		passwordDao.deletePasswdById(id);
	}
	
	public void updatePassword(Password password) {
		passwordDao.update(password);
	}
	

}
