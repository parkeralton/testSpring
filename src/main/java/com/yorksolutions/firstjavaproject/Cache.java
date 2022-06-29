package com.yorksolutions.firstjavaproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cache {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    Integer input;
    String output;
}
