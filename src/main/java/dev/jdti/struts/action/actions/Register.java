package dev.jdti.struts.action.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import dev.jdti.encryption.encrypt.bcrypt.PasswordEncryptionBcryptService;
import dev.jdti.persistence.entities.Password;
import dev.jdti.persistence.entities.Person;
import dev.jdti.persistence.service.PasswordService;
import dev.jdti.persistence.service.PersonService;

public class Register extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(Register.class);
	
	private Person personBean;
	private String password;
	private String confirmPassword;

	public String execute() throws Exception {
		
		savePersonToDb();
		LOG.info("password set and saved in person!");

		return SUCCESS;

	}

	private void savePersonToDb() {
		
		PersonService personService = new PersonService();
		PasswordService passwordService = new PasswordService();
		
		String salt = "no_salt";
		Password password = createPassword(this.password, salt);
		this.personBean.setPassword(password);
		
		passwordService.savePassword(password);
		personService.savePerson(personBean);
		
	}
	
	private Password createPassword(String passwd, String slt) {
		Password psswd = new Password();
		String passHash = hashPassword(passwd);
		psswd.setPassword(passHash);
		psswd.setSalt(slt);
		return psswd;
	}
	

	private String hashPassword(String passwd) {
		PasswordEncryptionBcryptService pebcs = new PasswordEncryptionBcryptService();
		return pebcs.getEncryptedPassword(passwd);
	}

	public void validate() {

		if (personBean.getFirstname().length() == 0) {
			addFieldError("personBean.firstname", "First name is required.");
		}
		if (personBean.getLastname().length() == 0) {
			addFieldError("personBean.lastname", "Last name is required.");
		}
		if (personBean.getEmail().length() == 0) {
			addFieldError("personBean.email", "First name is required.");
		}
		if (personBean.getAge() < 18) {
			addFieldError("personBean.age", "Age is required and must be 18 or older");
		}
		if("".equals(getPassword())){
			addFieldError("password", getText("password.required"));
		}
		if("".equals(getConfirmPassword())){
			addFieldError("confirmPassword", getText("cpassword.required"));
		}
		
		if(!(getPassword().equals(getConfirmPassword()))){
			addFieldError("confirmPassword", getText("cpassword.notmatch"));
		}
	}

	public Person getPersonBean() {
		return personBean;
	}

	public void setPersonBean(Person person) {
		personBean = person;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
