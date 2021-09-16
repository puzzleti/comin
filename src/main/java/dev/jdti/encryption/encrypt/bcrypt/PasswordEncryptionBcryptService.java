package dev.jdti.encryption.encrypt.bcrypt;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptionBcryptService {
	
	private static int workload = 10;
	
	public PasswordEncryptionBcryptService() {
		super();
	}


	public String getEncryptedPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return hashed_password;
	}

	
	public boolean authenticate(String password_plaintext, String psswd_hash) {
		boolean password_verified = false;

		if(null == psswd_hash || !psswd_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, psswd_hash);

		return(password_verified);
	}
}
