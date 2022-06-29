package com.yorksolutions.firstjavaproject;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

public class JsonTestWebsite {
    //getIP function
    public String getIP(String request) {

        JSONObject json = new JSONObject();
        json.put("ip", request);
        return json.toString();
    }

}
