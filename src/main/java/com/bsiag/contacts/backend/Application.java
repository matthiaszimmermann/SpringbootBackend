package com.bsiag.contacts.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

import com.bsiag.contacts.model.AbstractEntity;

@SpringBootApplication
@EntityScan(basePackageClasses=AbstractEntity.class)
//see http://stackoverflow.com/questions/14069449/autowiring-fails-not-an-managed-type
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
