package com.oreilly.sbf.services;

import com.oreilly.sbf.entities.Site;
import com.oreilly.sbf.restclient.services.GeoCoderService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class GeoCoderServiceTests {
    private Logger logger = LoggerFactory.getLogger(GeoCoderServiceTests.class);

    @Autowired
    private GeoCoderService service;

    @Test
    public void getLatLngWithoutStreet() {
        Site site = service.getLatLng("Boston", "MA");
        logger.info(site.toString());
        // ... asserts as before ...
    }
}
