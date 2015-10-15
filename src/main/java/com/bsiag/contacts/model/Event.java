package com.bsiag.contacts.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"dateEnd", "comment"})
public class Event extends AbstractEntity {

	private String title;
	private String url;
	
	private Date dateStart;
	private Date dateEnd;
	
	private String city;
	private String country;
	
	private String comment;

    @ManyToMany
    private List<Person> persons;
	    
}
