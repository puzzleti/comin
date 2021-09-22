package dev.jdti.struts.action.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import dev.jdti.encryption.encrypt.bcrypt.PasswordEncryptionBcryptService;
import dev.jdti.persistence.entities.Person;
import dev.jdti.persistence.service.PersonService;

public class Login extends ActionSupport {

	private static final long serialVersionUID = -8992026465669687624L;

	private static final Logger LOG = LoggerFactory.getLogger(Login.class);

	private String password;
	
	private String email;

	public String execute() throws Exception {
		
		if(isNoValidCredentials()) {
			return INPUT;
		}
		
		if(!isPersonAuthenticated()){
			return ERROR;
		}
		
		return SUCCESS;
	}

	private boolean isPersonAuthenticated() {
		
		PersonService personService = new PersonService();
		Person person = personService.getPersonByEmail(email);
		
		return isPersonIdentified(person, password);
	}

	private boolean isPersonIdentified(Person person,String passwordLogin) {
		if(person == null) {
			return false;
		}
		PasswordEncryptionBcryptService pebs = new PasswordEncryptionBcryptService(); 
		return pebs.authenticate(passwordLogin, person.getPasswordString());
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void validate() {

		//TODO: diesen hack ersetzen
		if(isNoValidCredentials()) {
			return;
		}
		
		if (this.getEmail().length() == 0) {
			addFieldError("email", getText("email.required"));
		}
		
		if("".equals(this.getPassword())){
			addFieldError("password", getText("password.required"));
		}
		
	}

	private boolean isNoValidCredentials() {
		if(this.email == null || this.password == null) {
			return true;
		}
		return false;
	}
	
	

}
