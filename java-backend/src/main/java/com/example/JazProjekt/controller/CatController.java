package com.example.JazProjekt.controller;

import com.example.JazProjekt.model.Breed;
import com.example.JazProjekt.model.Cat;
import com.example.JazProjekt.model.Owner;
import com.example.JazProjekt.repository.BreedRepository;
import com.example.JazProjekt.repository.CatRepository;
import com.example.JazProjekt.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
@CrossOrigin("http://localhost:3000")
public class CatController {

    @Autowired
    private OwnerRepository OwnerRepository;

    @Autowired
    private CatRepository catRepository;
    @Autowired
    private BreedRepository breedRepository;

    @PostMapping("/add")
    public ResponseEntity<Cat> addCat(@RequestBody Cat newCat) {
        // Obsługa właściciela
        if (newCat.getOwner() != null && newCat.getOwner().getId() != null) {
            Owner owner = OwnerRepository.findById(newCat.getOwner().getId())
                    .orElseThrow(() -> new RuntimeException("Owner not found with id " + newCat.getOwner().getId()));
            newCat.setOwner(owner);
        }

        // Obsługa rasy
        if (newCat.getBreed() != null && newCat.getBreed().getName() != null) {
            Breed breed = breedRepository.findByName(newCat.getBreed().getName())
                    .orElseThrow(() -> new RuntimeException("Breed not found with name " + newCat.getBreed().getName()));
            newCat.setBreed(breed);
        }

        // Zapis kota w bazie
        Cat savedCat = catRepository.save(newCat);
        return ResponseEntity.ok(savedCat);
    }



    @GetMapping("/all")
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cat getCatById(@PathVariable Long id) {
        return catRepository.findById(id).orElseThrow(() -> new RuntimeException("Cat not found"));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long id) {
        catRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("put/{id}")
    public ResponseEntity<Cat> updateCat(@RequestBody Cat updatedCat, @PathVariable Long id) {
        // Znajdź istniejącego kota w bazie danych
        Cat existingCat = catRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cat not found with id " + id));

        // Aktualizuj dane kota
        existingCat.setName(updatedCat.getName());
        existingCat.setAge(updatedCat.getAge());

        // Obsługa rasy (Breed)
        if (updatedCat.getBreed() != null && updatedCat.getBreed().getName() != null) {
            Breed breed = breedRepository.findByName(updatedCat.getBreed().getName())
                    .orElseThrow(() -> new RuntimeException("Breed not found with name " + updatedCat.getBreed().getName()));
            existingCat.setBreed(breed);
        }

        // Obsługa właściciela (Owner)
        if (updatedCat.getOwner() != null && updatedCat.getOwner().getId() != null) {
            Owner owner = OwnerRepository.findById(updatedCat.getOwner().getId())
                    .orElseThrow(() -> new RuntimeException("Owner not found with id " + updatedCat.getOwner().getId()));
            existingCat.setOwner(owner);
        }

        // Zapisz zaktualizowanego kota w bazie danych
        Cat savedCat = catRepository.save(existingCat);

        return ResponseEntity.ok(savedCat);
    }





}
