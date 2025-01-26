package com.example.JazProjekt.repository;

import com.example.JazProjekt.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
