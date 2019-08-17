package spring.boot.batchProcessing.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import spring.boot.batchProcessing.bean.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {
	private static final Log log = LogFactory.getLog(PersonItemProcessor.class);
	@Override
	public Person process(Person person) throws Exception {
		String firstName = person.getFirstName().toUpperCase();
		String lastName = person.getLastName().toLowerCase();
		Person transformedPerson = new Person(firstName,lastName);
		log.info("convert ("+person+") into ("+transformedPerson+")");
		return transformedPerson;
	}

}
