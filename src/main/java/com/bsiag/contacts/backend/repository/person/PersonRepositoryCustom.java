package com.bsiag.contacts.backend.repository.person;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;

import com.bsiag.contacts.model.Person;

public interface PersonRepositoryCustom {
	EntityLinks getEntityLinks();
	HttpEntity<Resources<Resource<Person>>> customSearch(String criteria);
}
