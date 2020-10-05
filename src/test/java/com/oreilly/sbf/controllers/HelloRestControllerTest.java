package com.oreilly.sbf.controllers;

import com.oreilly.sbf.entities.Greeting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void greetWithoutName() {
        ResponseEntity<Greeting> entity = template.getForEntity("/rest", Greeting.class);
        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        //Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, entity.getHeaders().getContentType());
        Greeting response = entity.getBody();
        Assertions.assertEquals("Hello, World!", response.getMessage());
    }

    @Test
    public void greetWithName() {
        Greeting response = template.getForObject("/rest?name=Dolly", Greeting.class);
        Assertions.assertEquals("Hello, Dolly!", response.getMessage());
    }

}
