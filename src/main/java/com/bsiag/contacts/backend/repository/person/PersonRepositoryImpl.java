package com.bsiag.contacts.backend.repository.person;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bsiag.contacts.model.Person;

@RestController
@ComponentScan("hello.repo")
public class PersonRepositoryImpl implements PersonRepositoryCustom, ResourceProcessor<Resource<Person>> {

	final static String LINK_SELF = "self";
	final static String LINK_COMPANY = "company";

	@Autowired
	private EntityLinks entityLinks;

	@Autowired
	private PersonRepository personRepo;

	@RequestMapping(value="/search/person", method = RequestMethod.GET)
	public HttpEntity<Resources<Resource<Person>>> customSearch(@RequestParam("criteria") String criteria) {

		PersonPredicateBuilder builder = new PersonPredicateBuilder(criteria);
		Resources<Resource<Person>> persons = Resources.wrap(personRepo.findAll(builder.predicates()));

		// add links to individual elements
		for(Resource<Person> r: persons) {
			processPerson(r, true);
		}

		// add link to search url
		persons.add(linkTo(methodOn(PersonRepositoryImpl.class).customSearch(criteria)).withSelfRel());

		return new ResponseEntity<>(persons, HttpStatus.OK);		
	}

	@Override
	public EntityLinks getEntityLinks() {
		return entityLinks;
	}
	
	@Override
	public Resource<Person> process(Resource<Person> r) {
		return processPerson(r, false);
	}

	/**
	 * exists to adapt the output of custom search to the format of the default format of the spring rest crud repo 
	 * @param r
	 * @param addRelationLinks
	 * @return
	 */
	private Resource<Person> processPerson(Resource<Person> r, boolean addRelationLinks) {
		Person p = r.getContent();

		if(!r.hasLink(LINK_SELF)) {
			r.add(entityLinks.linkForSingleResource(Person.class,p.getId()).withRel(LINK_SELF));
		}

		if(!r.hasLink(LINK_COMPANY) && addRelationLinks) {
			r.add(entityLinks.linkForSingleResource(Person.class,"" + p.getId() + "/" + LINK_COMPANY).withRel(LINK_COMPANY));			
		}

		return r;
	}
}
