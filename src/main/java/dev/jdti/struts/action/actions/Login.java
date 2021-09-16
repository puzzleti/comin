package dev.jdti.struts.action.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport {

	private static final long serialVersionUID = -8992026465669687624L;

	private static final Logger LOG = LoggerFactory.getLogger(Login.class);

	public String execute() throws Exception {

		return SUCCESS;
	}

}
