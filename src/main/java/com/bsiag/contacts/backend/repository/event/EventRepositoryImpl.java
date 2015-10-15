package com.bsiag.contacts.backend.repository.event;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bsiag.contacts.model.Event;

@RestController
public class EventRepositoryImpl implements EventRepositoryCustom, ResourceProcessor<Resource<Event>> {

	final static String LINK_SELF = "self";
	final static String LINK_PERSONS = "persons";

	// TODO find out why this does not work
//	@Autowired
//	private EntityLinks entityLinks;

	@Autowired
	private EventRepository eventRepo;

	@RequestMapping(value="/search/event", method = RequestMethod.GET)
	public HttpEntity<Resources<Resource<Event>>> customSearch(@RequestParam("criteria") String criteria) {

		EventPredicateBuilder builder = new EventPredicateBuilder(criteria);
		Resources<Resource<Event>> events = Resources.wrap(eventRepo.findAll(builder.predicates()));

		// add links to individual elements
		for(Resource<Event> e: events) {
			processEvent(e, true);
		}

		// add link to search url
		events.add(linkTo(methodOn(EventRepositoryImpl.class).customSearch(criteria)).withSelfRel());

		return new ResponseEntity<>(events, HttpStatus.OK);		
	}

	@Override
	public Resource<Event> process(Resource<Event> e) {
		return processEvent(e, false);
	}

	/**
	 * exists to adapt the output of custom search to the format of the default format of the spring rest crud repo 
	 * @param r
	 * @param addRelationLinks
	 * @return
	 */
	private Resource<Event> processEvent(Resource<Event> r, boolean addRelationLinks) {
// TODO once the autowiring of EntityLinks is working the code below can be used again
		
//Event e = r.getContent();
//
//		if(!r.hasLink(LINK_SELF)) {
//			r.add(entityLinks.linkForSingleResource(Event.class,e.getId()).withRel(LINK_SELF));
//		}
//
//		if(!r.hasLink(LINK_PERSONS) && addRelationLinks) {
//			r.add(entityLinks.linkForSingleResource(Event.class,"" + e.getId() + "/" + LINK_PERSONS).withRel(LINK_PERSONS));			
//		}

		return r;
	}
}
