package com.bsiag.contacts.backend.repository.event;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bsiag.contacts.model.Event;

@RepositoryRestResource
public interface EventRepository extends CrudRepository<Event, Long>, QueryDslPredicateExecutor<Event>, EventRepositoryCustom {}
