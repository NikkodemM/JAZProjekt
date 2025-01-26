package com.example.JazProjekt.controller;

import com.example.JazProjekt.model.Breed;
import com.example.JazProjekt.repository.BreedRepository;
import com.example.JazProjekt.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breeds")
public class BreedController {

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private BreedService breedService;

    @GetMapping("/fetch")
    public String fetchAndSaveBreeds() {
        breedService.fetchAndSaveBreeds();
        return "Dane ras zostały pobrane i zapisane w bazie danych.";
    }

    @GetMapping("/all")
    public List<Breed> getAllBreeds() {
        return breedRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Breed> addBreed(@RequestBody Breed newBreed) {
        Breed savedBreed = breedRepository.save(newBreed);
        return ResponseEntity.ok(savedBreed);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Breed> getBreedByName(@PathVariable String name) {
        Breed breed = breedRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Breed not found with name " + name));
        return ResponseEntity.ok(breed);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Breed> getBreedById(@PathVariable Long id) {
        Breed breed = breedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Breed not found with id " + id));
        return ResponseEntity.ok(breed);
    }
}
