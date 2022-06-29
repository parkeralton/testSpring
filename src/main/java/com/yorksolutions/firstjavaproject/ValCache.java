package com.yorksolutions.firstjavaproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ValCache {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String input;
    Boolean validate;
    String message;

}
