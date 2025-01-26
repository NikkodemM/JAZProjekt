package com.example.JazProjekt;

import com.example.JazProjekt.controller.CatController;
import com.example.JazProjekt.model.Cat;
import com.example.JazProjekt.repository.CatRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CatControllerTest {

    @InjectMocks
    private CatController catController;

    @Mock
    private CatRepository catRepository;

    @Test
    void testGetAllCats() {
        MockitoAnnotations.initMocks(this);

        List<Cat> mockCats = new ArrayList<>();
        Cat cat1 = new Cat();
        cat1.setId(1L);
        cat1.setName("Whiskers");
        mockCats.add(cat1);

        Cat cat2 = new Cat();
        cat2.setId(2L);
        cat2.setName("Fluffy");
        mockCats.add(cat2);

        when(catRepository.findAll()).thenReturn(mockCats);

        List<Cat> result = catController.getAllCats();

        assertEquals(2, result.size());
        assertEquals("Whiskers", result.get(0).getName());
        assertEquals("Fluffy", result.get(1).getName());


        verify(catRepository, times(1)).findAll();
    }

    @Test
    void testAddCat() {
        MockitoAnnotations.initMocks(this);


        Cat newCat = new Cat();
        newCat.setName("Whiskers");

        when(catRepository.save(newCat)).thenReturn(newCat);


        ResponseEntity<Cat> response = catController.addCat(newCat);


        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Whiskers", response.getBody().getName());


        verify(catRepository, times(1)).save(newCat);
    }


    @Test
    void testUpdateCat() {

        MockitoAnnotations.initMocks(this);

        Cat existingCat = new Cat();
        existingCat.setId(1L);
        existingCat.setName("Whiskers");


        Cat updatedData = new Cat();
    }


    }
