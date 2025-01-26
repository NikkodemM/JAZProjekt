import React, { useState, useEffect } from "react";
import axios from "axios";

export default function CatFact() {
  const [catFact, setCatFact] = useState("");

  const loadCatFact = async () => {
    try {
      const result = await axios.get("http://localhost:8080/get/catfact");
      setCatFact(result.data.fact);
    } catch (error) {
      console.error("Error loading cat fact:", error);
    }
  };

  useEffect(() => {
    loadCatFact();
  }, []);

  return (
    <div className="container my-5">
      <div className="row justify-content-center">
        <div className="col-md-8">
          <div className="card shadow-lg border-0">
            <div className="card-header bg-primary text-white text-center">
              <h2>Fakty o kotkach</h2>
            </div>
            <div className="card-body text-center">
              <p className="card-text fs-4">
                {catFact || "Ładowanie faktu o kotach..."}
              </p>
              <button className="btn btn-primary mt-3" onClick={loadCatFact}>
                Pobierz Nowy Fakt
              </button>
            </div>
            <div className="card-footer text-muted text-center">
              Codzienna dawka wiedzy o kotach!
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
