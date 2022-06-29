package com.yorksolutions.firstjavaproject;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Clob;

@Entity
public class HeaderCache {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
String header;
String header2;
}
