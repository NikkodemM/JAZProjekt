package com.example.JazProjekt;

import com.example.JazProjekt.controller.BreedController;
import com.example.JazProjekt.model.Breed;
import com.example.JazProjekt.repository.BreedRepository;
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

class BreedControllerTest {

    @InjectMocks
    private BreedController breedController;

    @Mock
    private BreedRepository breedRepository;

    @Test
    void testGetAllBreeds() {
        MockitoAnnotations.initMocks(this);

        List<Breed> mockBreeds = new ArrayList<>();
        Breed breed1 = new Breed();
        breed1.setId(1L);
        breed1.setName("Siamese");
        mockBreeds.add(breed1);

        Breed breed2 = new Breed();
        breed2.setId(2L);
        breed2.setName("Persian");
        mockBreeds.add(breed2);

        when(breedRepository.findAll()).thenReturn(mockBreeds);

        List<Breed> result = breedController.getAllBreeds();

        assertEquals(2, result.size());
        assertEquals("Siamese", result.get(0).getName());
        assertEquals("Persian", result.get(1).getName());

        verify(breedRepository, times(1)).findAll();
    }

    @Test
    void testGetBreedById() {
        MockitoAnnotations.initMocks(this);

        Breed mockBreed = new Breed();
        mockBreed.setId(1L);
        mockBreed.setName("Siamese");

        when(breedRepository.findById(1L)).thenReturn(Optional.of(mockBreed));

        ResponseEntity<Breed> response = breedController.getBreedById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Siamese", response.getBody().getName());

        verify(breedRepository, times(1)).findById(1L);
    }

    @Test
    void testAddBreed() {
        MockitoAnnotations.initMocks(this);

        Breed newBreed = new Breed();
        newBreed.setName("Siamese");

        when(breedRepository.save(newBreed)).thenReturn(newBreed);

        ResponseEntity<Breed> response = breedController.addBreed(newBreed);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Siamese", response.getBody().getName());

        verify(breedRepository, times(1)).save(newBreed);
    }

    @Test
    void testGetBreedByIdNotFound() {
        MockitoAnnotations.initMocks(this);

        when(breedRepository.findById(99L)).thenReturn(Optional.empty());

        try {
            breedController.getBreedById(99L);
            throw new AssertionError("Expected RuntimeException not thrown");
        } catch (RuntimeException e) {
            assertEquals("Breed not found with id 99", e.getMessage());
            verify(breedRepository, times(1)).findById(99L);
        }
    }
}
