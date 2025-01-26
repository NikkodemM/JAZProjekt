package com.example.JazProjekt;

import com.example.JazProjekt.controller.OwnerController;
import com.example.JazProjekt.model.Owner;
import com.example.JazProjekt.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OwnerControllerTest {

    @InjectMocks
    private OwnerController ownerController;

    @Mock
    private OwnerRepository ownerRepository;

    @Test
    void testGetAllOwners() {
        MockitoAnnotations.initMocks(this);

        List<Owner> mockOwners = new ArrayList<>();
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("John Doe");
        mockOwners.add(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setName("Jane Doe");
        mockOwners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(mockOwners);

        List<Owner> result = ownerController.getAllOwners();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());

        verify(ownerRepository, times(1)).findAll();
    }


    @Test
    void testGetOwnerByIdNotFound() {
        MockitoAnnotations.initMocks(this);

        when(ownerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> ownerController.getOwnerById(99L));
    }

    @Test
    void testAddOwner() {
        MockitoAnnotations.initMocks(this);

        Owner newOwner = new Owner();
        newOwner.setName("John Doe");

        when(ownerRepository.save(newOwner)).thenReturn(newOwner);


        ResponseEntity<Owner> response = ownerController.addOwner(newOwner);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getName());

        verify(ownerRepository, times(1)).save(newOwner);
    }
}
