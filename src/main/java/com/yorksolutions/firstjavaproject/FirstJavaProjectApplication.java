package com.yorksolutions.firstjavaproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.client.RestTemplate;

//exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class}
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class FirstJavaProjectApplication {

    public static void main(String[] args) {

        Integer int1 = 127;
        Integer int2 = 127;
        Integer int3 = 128;
        Integer int4 = 128;

        System.out.println(int1==int2);
        System.out.println(int3==int4);

        SpringApplication.run(FirstJavaProjectApplication.class, args);
        //String json = "{\"Name\":\"Parker\"}";
//        String name = "parker";
        final var rest = new RestTemplate();
        JSONObject json = new JSONObject();
//        json.put("Name", "Parker");
//        json.put("Area Code", 55104);
//        json.put("Last Name", "Alton");
//
        final var ipResponse = rest.getForEntity("http://ip.jsontest.com", String.class);
//        final var headerResponse = rest.getForObject("http://header.jsontest.com", String.class);
//        final var dateResponse = rest.getForObject("http://date.jsontest.com", String.class);
//        final var echoResponse = rest.getForObject("http://echo.jsontest.com/test1/1/test2/2", String.class);
//        final var valResponse = rest.getForObject("http://validate.jsontest.com/?json=" + json, String.class, json);
//        final var arbResponse = rest.getForObject("http://code.jsontest.com/", String.class);
//        final var cookieResponse = rest.getForObject("http://cookie.jsontest.com/", String.class);
//        final var md5Response = rest.getForObject("http://md5.jsontest.com/?text=parker", String.class);
//        final var md52Response = rest.getForObject("http://md5.jsontest.com/?text={name}", String.class, name);

        //final var Response = rest.getForObject("http://headers.jsontest.com", String.class);
        //final var Response = rest.getForObject("http://headers.jsontest.com", String.class);
        //getforobject
//        System.out.println("IP " + ipResponse.getBody());
//        System.out.println("Header " + headerResponse);
//        System.out.println("date " + dateResponse);
//        System.out.println("echo " + echoResponse);
//        System.out.println("val " + valResponse);
//        System.out.println("arb " + arbResponse);
//        System.out.println("cookieResponse " + cookieResponse);
//        System.out.println("md5 1 " + md5Response);
//        System.out.println("md5 2 " + md52Response);
    }
}
