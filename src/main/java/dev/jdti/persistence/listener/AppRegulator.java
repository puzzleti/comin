package dev.jdti.persistence.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.jdti.persistence.entities.Password;
import dev.jdti.persistence.entities.Person;
import dev.jdti.persistence.service.PasswordService;
import dev.jdti.persistence.service.PersonService;

/**
 * Application Lifecycle Listener implementation class AppRegulator
 *
 */
@WebListener
public class AppRegulator implements ServletContextListener {

	private static final Logger LOG = LoggerFactory.getLogger(AppRegulator.class);
	Server server;
    public AppRegulator() {
		server = new Server();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	LOG.info("Stopping h2 server");
		server.shutdown();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	LOG.info("Starting h2 server");
		try {
			server.runTool("-tcp", "-web", "-ifNotExists");
			saveInitPersonDb();
			
		} catch (SQLException e) {
			LOG.error("h2 Server konnte nicht gestartet werden");
			e.printStackTrace();
		}
    }

	private void saveInitPersonDb() {

		PersonService personService = new PersonService();
		PasswordService passwordService = new PasswordService();
		
		Password psswd = new Password();
		psswd.setPassword("thim2!!");
		psswd.setSalt("adm123");
		passwordService.savePassword(psswd);
		
		Person person = new Person("Thim","Admin", 53, "thim@admin.org");
		person.setPassword(psswd);
		personService.savePerson(person);
	}
	
}
