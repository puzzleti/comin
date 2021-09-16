package dev.jdti.struts.action.utils;

import java.util.List;

import dev.jdti.persistence.entities.Person;
import dev.jdti.persistence.service.PersonService;

public class DbUtil {

	public static void printAllPersonsFromDB() {
		PersonService personService = new PersonService();
		List<Person> resultList = personService.getAllPersons();
		resultList.forEach((x) -> System.out.printf("- %s - %s %s %n", x.getId(), x.getFirstname(), x.getLastname()));
	}
	
}
