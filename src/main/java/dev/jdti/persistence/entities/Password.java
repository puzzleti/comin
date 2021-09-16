package dev.jdti.persistence.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "PASSWD")
public class Password implements Serializable{

	private static final long serialVersionUID = 3241188205100963509L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long passwd_id;
	@Column(name = "password")
	private String password;
	@Column(name = "salt")
	private String salt;
	
	public long getPasswd_id() {
		return passwd_id;
	}

	public void setPasswd_id(long passwd_id) {
		this.passwd_id = passwd_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "Password [id=" + passwd_id + ", password=" + password + ", salt=" + salt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(passwd_id, password, salt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Password other = (Password) obj;
		return passwd_id == other.passwd_id && Objects.equals(password, other.password) && Objects.equals(salt, other.salt);
	}

}
