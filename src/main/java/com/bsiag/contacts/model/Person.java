package com.bsiag.contacts.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"gender", "phone", "mobile", "phoneWork", "emailWork", "comment"})
public class Person extends AbstractEntity {

	private String firstName;
	private String lastName;
	private String gender;
	private Date dateOfBirth;

	private String pictureUrl;

	private String street;
	private String city;
	private String country;

	private String phone;
	private String mobile;
	private String email;

	private String position;
	private String phoneWork;
	private String emailWork;	

	private String comment;
	
    @ManyToOne
    private Company company;
    
}
