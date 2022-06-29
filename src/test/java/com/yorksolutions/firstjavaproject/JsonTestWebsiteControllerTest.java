package com.yorksolutions.firstjavaproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class JsonTestWebsiteControllerTest {
    @LocalServerPort
    int port;

    @Mock
    JsonTestWebsite jsonTestWebsite;

    @Mock
    HttpServletRequest servMock;
    @Autowired
    JsonTestWebsiteController controller;

    @Test
    void itShouldCallJsonTestWebsiteAndReturnItsValue(){

        when(servMock.getRemoteAddr()).thenReturn("it was called");
        //when(jsonTestWebsite.getIP("it was called")).thenReturn("called again");
        //controller.setJsonTestWebsite(jsonTestWebsite);
        controller.setHttpServletRequest(servMock);
        final RestTemplate rest = new RestTemplate();
        final ResponseEntity<String> actual = rest.getForEntity("http://localhost:" + port + "/getIP", String.class);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("{\"ip\":\"it was called\"}", actual.getBody());
rest.getForObject("http://www.google.com", String.class);
    }
}
