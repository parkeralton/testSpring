package com.yorksolutions.firstjavaproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MD5Cache {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String input;
    String output;
}
