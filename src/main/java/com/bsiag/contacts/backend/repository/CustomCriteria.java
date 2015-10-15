package com.bsiag.contacts.backend.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomCriteria {
    private String key;
    private String operation;
    private Object value;
}
