package com.yorksolutions.firstjavaproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IpCache {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String ip;
}
