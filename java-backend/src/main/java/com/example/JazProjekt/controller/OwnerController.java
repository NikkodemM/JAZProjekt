package com.example.JazProjekt.controller;

import com.example.JazProjekt.model.Owner;
import com.example.JazProjekt.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @PostMapping("/add")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner newOwner) {
        Owner savedOwner = ownerRepository.save(newOwner);
        return ResponseEntity.ok(savedOwner);
    }


    @GetMapping("/all")
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable Long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not found"));
    }
}
