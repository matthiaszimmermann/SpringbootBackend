package com.bsiag.contacts.backend.repository.person;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bsiag.contacts.model.Person;

@RepositoryRestResource
public interface PersonRepository 
extends CrudRepository<Person, Long>, QueryDslPredicateExecutor<Person>, PersonRepositoryCustom {}
