package com.example.JazProjekt.service;

import java.util.List;

public class BreedResponse {

    private List<BreedApiData> data; // Lista ras

    // Gettery i Settery
    public List<BreedApiData> getData() {
        return data;
    }

    public void setData(List<BreedApiData> data) {
        this.data = data;
    }
}
