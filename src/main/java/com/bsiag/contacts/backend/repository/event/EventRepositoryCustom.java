package com.bsiag.contacts.backend.repository.event;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;

import com.bsiag.contacts.model.Event;

public interface EventRepositoryCustom {
	HttpEntity<Resources<Resource<Event>>> customSearch(String criteria);
}
