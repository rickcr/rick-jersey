package net.learntechnology.jersey;

import net.learntechnology.domain.Person;
import net.learntechnology.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Path("/persons")
public class PersonWS {
	private final static Logger logger = LoggerFactory.getLogger(PersonWS.class);

	@Autowired
	private PersonService personService;

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML})
	public Person fetchPerson(@PathParam("id") Integer id) {
		return personService.fetchPerson(id);
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces (MediaType.APPLICATION_XML)
	public Person addPerson(Person person) {
		personService.addPerson(person);
		return person;
	}

	@GET
	@Produces (MediaType.APPLICATION_XML)
	@XmlElementWrapper(name = "persons")
	public List<Person> fetchPersons() {
		List<Person> persons = personService.fetchAll();
		return persons;
	}

}
