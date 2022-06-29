package com.yorksolutions.firstjavaproject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@CrossOrigin
@RestController
public class JsonTestWebsiteController {
    HttpServletRequest httpServletRequest;
    JsonTestWebsite jsonTestWebsite;

    final MD5CacheRepository md5Repository;
    final ValCacheRepository valRepository;
    final IpCacheRepository ipRepository;
    final HeaderCacheRepository headerRepository;
    @Autowired
    JsonTestWebsiteController(MD5CacheRepository md5Repository, ValCacheRepository valRepository, IpCacheRepository ipRepository, HeaderCacheRepository headerRepository){
        this.md5Repository = md5Repository;
        this.valRepository = valRepository;
        this.ipRepository = ipRepository;
        this.headerRepository = headerRepository;
        jsonTestWebsite = new JsonTestWebsite();
        httpServletRequest = null;
    }
    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    void setHttpServletRequest(HttpServletRequest httpServletRequest){
        this.httpServletRequest = httpServletRequest;
    }
    @GetMapping(value = "/ip", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getIP(HttpServletRequest request) {
        IpCache ipCache = new IpCache();
        if( this.httpServletRequest != null){
            request = this.httpServletRequest;
        }
        final Optional<IpCache> result = ipRepository.findByIp(request.getRemoteAddr());
        if (result.isEmpty()){
           ipCache.ip = request.getRemoteAddr();
           ipRepository.save(ipCache);
        }

        return  jsonTestWebsite.getIP(request.getRemoteAddr());
    }
//===================================================================

    @GetMapping(value = "/header", produces = MediaType.APPLICATION_JSON_VALUE)
    public String listAllHeaders(
            @RequestHeader Map<String, String> headers) {
        HeaderCache headerCache = new HeaderCache();
        JSONObject json = new JSONObject();
        headers.forEach((key, value) -> {
            json.put(key, value);
        });
        final Optional<HeaderCache> result = headerRepository.findByHeader(json.toString().substring(0,255));
        if(result.isEmpty()){
            int max = json.toString().length();
                    if (max>510) max = 510;
            headerCache.header = json.toString().substring(0,255);
            headerCache.header2 = json.toString().substring(255, max);
            headerRepository.save(headerCache);
        }
        return json.toString();
    }

    //TODO
    @GetMapping(value = "/time", produces = MediaType.APPLICATION_JSON_VALUE)
    public String date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss a");
        JSONObject json = new JSONObject();
        LocalDate date =  LocalDate.now();
        String time =  LocalTime.now().format(dtf);


        json.put("time", time);


        return json.toString();
    }
    @GetMapping(value = "/date", produces = MediaType.APPLICATION_JSON_VALUE)
    public String date2() {
        JSONObject json = new JSONObject();
        LocalDate date =  LocalDate.now();
        LocalTime time =  LocalTime.now();
        json.put("time", time);
        json.put("date", date);
        return json.toString();
    }
    private HttpServletRequest request;



    @RequestMapping(value = "/echo/**", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object echo() {
        JSONObject json = new JSONObject();
        String id = request.getRequestURI().substring(1);
        String[] split = id.split("/");
        try {
            for (int i = 1; i < split.length; i = i + 2) {
                json.put(split[i], split[i + 1]);
            }
        } catch (RuntimeException e) {
            return "Please enter valid json object";
        }
        return json.toString();
    }
float f = 2.3f;
double s = 4;
float g = 4;
double q = 323_32_3.3;
    @RequestMapping(value = "/val/{json}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String val(@PathVariable("json") String json) {
        final Optional<ValCache> result = valRepository.findByInput(json);
        JSONObject response = new JSONObject();
        ValCache valCache = new ValCache();
        if(result.isPresent()){
            if (result.get().validate){
                return response.put("valid", true).toString();
            }else{
                response.put("valid", false);
                response.put("error", result.get().message);
                return response.toString();
            }
        }
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            response.put("valid", false);
            response.put("error", e.getMessage());
            valCache.input = json;
            valCache.validate = false;
            valCache.message = e.getMessage();
            valRepository.save(valCache);
            return response.toString();
        }
        valCache.input = json;
        valCache.validate = true;
        valRepository.save(valCache);
        return response.put("valid", true).toString();
    }


    @GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object code(HttpServletRequest request) {
        String IP = request.getRemoteAddr();
        String fn = "alert";
        JSONPObject jsonp = new JSONPObject(fn, IP);
        return jsonp;
    }

    @GetMapping(value = "/cookie", produces = MediaType.APPLICATION_JSON_VALUE)
    public String setCookie(HttpServletResponse response) {
        JSONObject json = new JSONObject();
        Cookie cookie = new Cookie("Name", "Parker");
        response.addCookie(cookie);
        json.put("cookie_added", cookie.getValue());
        return json.toString();
    }


    @GetMapping(value = "/MD5/{text}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String md5(@PathVariable("text") String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final Optional<MD5Cache> result = md5Repository.findByInput(text);
        JSONObject json = new JSONObject();
        if (result.isPresent()){
            json.put("original", text);
            json.put("md5", result.get().output);
            return json.toString();
        }

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] answer = md.digest(text.getBytes("UTF-8"));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < answer.length; ++i) {
            sb.append(Integer.toHexString((answer[i] & 0xFF) | 0x100).substring(1, 3));
        }
        json.put("original", text);
        json.put("md5", sb.toString());
        MD5Cache md5Cache = new MD5Cache();
        md5Cache.input=text;
        md5Cache.output=sb.toString();
        md5Repository.save(md5Cache);
        return json.toString();
    }

    //===============================================
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

//    @GetMapping("/ip")
//    String ip() {
//        final RestTemplate rest = new RestTemplate();
//        return rest.getForObject("http://ip.jsontest.com", String.class);
//    }

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
