package net.learntechnology.service;

import net.learntechnology.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {
	private final static Logger logger = LoggerFactory.getLogger(PersonService.class);

	private static final Map<Integer, Person> personsMap = new HashMap<Integer, Person>();
	static {
	 	personsMap.put(1, new Person(1, "Britta Perry"));
		personsMap.put(2, new Person(2, "Annie Edison"));
	}

	public Person fetchPerson(Integer id) {
		logger.debug("fetching person for id {}",id);
		return personsMap.get(id);
	}

	public void addPerson(Person person) {
		logger.debug("adding person {}",person);
		personsMap.put(person.getId(), person);
	}

	public List<Person> fetchAll() {
		List<Person> persons =  new ArrayList<Person>(personsMap.values());
		logger.debug("persons = {}",persons);
		return persons;
	}
}
