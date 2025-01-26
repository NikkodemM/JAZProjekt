package com.example.JazProjekt;

import com.example.JazProjekt.model.CatFact;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CatFactServiceTest {

    @Test
    void testGetRandomCatFactFromLocalhost() {
        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = "http://localhost:8080/get/catfact";
        CatFact response = restTemplate.getForObject(apiUrl, CatFact.class);

        assertNotNull(response);
        assertNotNull(response.getFact());
        System.out.println("Cat Fact: " + response.getFact());
    }
}
