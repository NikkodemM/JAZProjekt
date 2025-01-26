package com.example.JazProjekt.catfact;

import com.example.JazProjekt.model.CatFact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class CatFactController {

    @Autowired
    private CatFactService catFactService;

    @GetMapping("/get/catfact")
    public CatFact getRandomCatFact() {
        return catFactService.getRandomCatFact();
    }
}