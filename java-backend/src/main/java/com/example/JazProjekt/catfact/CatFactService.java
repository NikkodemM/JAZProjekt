package com.example.JazProjekt.catfact;


import com.example.JazProjekt.model.CatFact;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatFactService {

    private final RestTemplate restTemplate;
    private final String CAT_FACT_API = "https://catfact.ninja/fact";

    public CatFactService() {
        this.restTemplate = new RestTemplate();
    }

    public CatFact getRandomCatFact() {
        return restTemplate.getForObject(CAT_FACT_API, CatFact.class);
    }
}
