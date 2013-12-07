package net.learntechnology;

import net.learntechnology.domain.Person;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class PersonTest {
	private final static Logger logger = LoggerFactory.getLogger(PersonTest.class);

	@Test
	public void should_fetch_person_by_id() {
		Client client = ClientBuilder.newClient();
		Person person = client.target("http://localhost:8080/rick-jersey/rest/persons/2")
			.request(MediaType.APPLICATION_XML_TYPE).get(Person.class);
		logger.debug("Person: {}", person);
	}

	@Test
	public void should_add_person() {
		Person person = new Person(3, "Jeff Winger");
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/rick-jersey/rest/persons/add")
			.request(MediaType.APPLICATION_XML).post(Entity.entity(person, MediaType.APPLICATION_XML));
		person = response.readEntity(Person.class);
		//OR:
		//person = client.target("http://localhost:8080/rick-jersey/rest/person/add")
		//	.request(MediaType.APPLICATION_XML_TYPE).post(Entity.entity(person, MediaType.APPLICATION_XML), Person.class);
		logger.debug("response code: {}", response.getStatus());
		logger.debug("Person: {}", person);
	}

	@Test
	public void should_fetch_all() {
		Client client = ClientBuilder.newClient();
		List<Person> persons = client.target("http://localhost:8080/rick-jersey/rest/persons")
					.request(MediaType.APPLICATION_XML_TYPE).get(new GenericType<List<Person>>(){});
		logger.debug("persons = {}",persons);
	}

}
