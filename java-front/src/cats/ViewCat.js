import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

export default function ViewCat() {
  const [cat, setCat] = useState(null); // Dane kota
  const { id } = useParams(); // Pobranie ID kota z URL

  useEffect(() => {
    loadCat();
  }, []);

  // Pobranie danych kota z backendu
  const loadCat = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/cats/${id}`);
      setCat(result.data);
    } catch (error) {
      console.error("Error loading cat:", error);
      alert("Nie udało się załadować danych kota.");
    }
  };

  if (!cat) return <p>Ładowanie danych...</p>; // Wyświetl komunikat podczas ładowania

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-8">
          <div className="card shadow-lg border-0">
            <div className="card-header bg-primary text-white text-center">
              <h3>Szczegóły Kota</h3>
            </div>
            <div className="card-body">
              <table className="table table-bordered table-striped">
                <tbody>
                  <tr>
                    <th>Imię</th>
                    <td>{cat.name}</td>
                  </tr>
                  <tr>
                    <th>Wiek</th>
                    <td>{cat.age}</td>
                  </tr>
                  <tr>
                    <th>Rasa</th>
                    <td>{cat.breed?.name || "Brak danych"}</td>
                  </tr>
                  <tr>
                    <th>Właściciel</th>
                    <td>{cat.owner?.name || "Brak właściciela"}</td>
                  </tr>
                </tbody>
              </table>

              {/* Przycisk powrotu */}
              <div className="text-center mt-4">
                <Link to="/" className="btn btn-primary">
                  Powrót do Strony Głównej
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
