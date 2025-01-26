package com.example.JazProjekt.repository;

import com.example.JazProjekt.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    Optional<Breed> findByName(String name);
}

