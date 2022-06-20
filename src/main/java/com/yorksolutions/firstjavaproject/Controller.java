package com.yorksolutions.firstjavaproject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;


@RestController
public class Controller {


    StringBuilder builder = new StringBuilder();
    String test;

    @GetMapping("/getIP")
    public String getIP() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String IP = request.getRemoteAddr();
        return IP;
    }

    @GetMapping("/getHeader")
    public StringBuilder listAllHeaders(
            @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            builder.append(String.format("Header '%s' = %s <br>", key, value));
        });

        return builder;
    }

    @GetMapping("/time")
    public String date(){
        Date date = new Date();
                return date.toString();
    }
    //Tell tomcat to call this method when http://localhost:8080/hello is called with a GET request
    @GetMapping("/hello")
    String helloWorld() {
        return "hello world";
    }

    //URL parameter http://localhost:8080/param?arg1=val1&arg2=val2
    @GetMapping("/param")
    String hi(@RequestParam(name = "someArg") String arg1, String arg2) {
        return "you passed " + arg1 + " and " + arg2;
    }

    @GetMapping("/ip")
    String ip() {
        final RestTemplate rest = new RestTemplate();
        return rest.getForObject("http://ip.jsontest.com", String.class);
    }

    private static class IP {
        @JsonProperty
        String ip;
    }

    @GetMapping("/ipjson")
    String ipjson() {
        final RestTemplate rest = new RestTemplate();
        //
        final IP ipResponse = rest.getForObject("http://ip.jsontest.com", IP.class);
        return ipResponse.ip;
    }
}
