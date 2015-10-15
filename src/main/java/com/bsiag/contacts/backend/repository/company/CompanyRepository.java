package com.bsiag.contacts.backend.repository.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bsiag.contacts.model.Company;

@RepositoryRestResource
public interface CompanyRepository extends CrudRepository<Company, Long> {}
