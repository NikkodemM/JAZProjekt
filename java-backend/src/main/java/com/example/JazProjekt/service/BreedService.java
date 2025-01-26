package com.example.JazProjekt.service;

import com.example.JazProjekt.model.Breed;
import com.example.JazProjekt.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreedService {

    @Autowired
    private BreedRepository breedRepository;

    private final String API_URL = "https://catfact.ninja/breeds";

    public void fetchAndSaveBreeds() {
        RestTemplate restTemplate = new RestTemplate();

        // Pobierz dane z API
        BreedResponse response = restTemplate.getForObject(API_URL, BreedResponse.class);

        if (response != null && response.getData() != null) {
            List<Breed> breedsToSave = new ArrayList<>();

            for (BreedApiData apiData : response.getData()) {
                Breed breed = new Breed();
                breed.setName(apiData.getBreed());  // Mapowanie 'breed' na 'name'
                breed.setCountry(apiData.getCountry());
                breed.setOrigin(apiData.getOrigin());
                breed.setCoat(apiData.getCoat());
                breed.setPattern(apiData.getPattern());

                breedsToSave.add(breed);
            }

            // Zapisz dane w bazie danych
            breedRepository.saveAll(breedsToSave);
            System.out.println("Dane ras zostały zapisane w bazie danych.");
        } else {
            System.out.println("Nie udało się pobrać danych z API.");
        }
    }
}
