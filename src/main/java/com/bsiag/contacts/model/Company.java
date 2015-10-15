package com.bsiag.contacts.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"logoUrl", "phone", "email", "comment", "persons"})
public class Company extends AbstractEntity {

	private String name;
	private String logoUrl;
	private String url;
	
	private String street;
	private String city;
	private String country;
	
	private String phone;
	private String email;
	
	private String comment;

    @OneToMany
    @JoinColumn(name = "company_id")    
    private Set<Person> persons;
	    
}
