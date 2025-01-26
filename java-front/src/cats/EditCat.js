import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

export default function EditCat() {
  const [cat, setCat] = useState({
    name: "",
    age: "",
    breedName: "",
    ownerId: "",
  });

  const [breeds, setBreeds] = useState([]);
  const [owners, setOwners] = useState([]);

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    loadCat();
    loadBreeds();
    loadOwners();
  }, []);

  const loadCat = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/cats/${id}`);
      setCat({
        name: result.data.name,
        age: result.data.age,
        breedName: result.data.breed.name,
        ownerId: result.data.owner?.id || "",
      });
    } catch (error) {
      console.error("Error loading cat:", error);
      alert("Nie udało się załadować danych kota.");
    }
  };

  const loadBreeds = async () => {
    try {
      const result = await axios.get("http://localhost:8080/breeds/all");
      setBreeds(result.data);
    } catch (error) {
      console.error("Error loading breeds:", error);
      alert("Nie udało się załadować listy ras.");
    }
  };

  const loadOwners = async () => {
    try {
      const result = await axios.get("http://localhost:8080/owners/all");
      setOwners(result.data);
    } catch (error) {
      console.error("Error loading owners:", error);
      alert("Nie udało się załadować listy właścicieli.");
    }
  };

  const onInputChange = (e) => {
    setCat({ ...cat, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = {
        ...cat,
        breed: { name: cat.breedName },
        owner: { id: cat.ownerId },
      };
      await axios.put(`http://localhost:8080/cats/${id}`, payload, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      navigate("/");
      alert("Kot został pomyślnie zaktualizowany!");
    } catch (error) {
      console.error("Error updating cat:", error);
      alert("Nie udało się zaktualizować kota.");
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-8">
          <div className="card shadow-lg border-0">
            <div className="card-header bg-primary text-white text-center">
              <h3>Edytuj Kota</h3>
            </div>
            <div className="card-body">
              <form onSubmit={onSubmit}>
                <div className="mb-3">
                  <label htmlFor="name" className="form-label">
                    Imię Kota
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Wprowadź imię kota"
                    name="name"
                    value={cat.name}
                    onChange={onInputChange}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="age" className="form-label">
                    Wiek Kota
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    placeholder="Wprowadź wiek kota"
                    name="age"
                    value={cat.age}
                    onChange={onInputChange}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="breed" className="form-label">
                    Rasa Kota
                  </label>
                  <select
                    className="form-select"
                    name="breedName"
                    value={cat.breedName}
                    onChange={onInputChange}
                  >
                    <option value="">Wybierz rasę</option>
                    {breeds.map((breed) => (
                      <option key={breed.id} value={breed.name}>
                        {breed.name}
                      </option>
                    ))}
                  </select>
                </div>
                <div className="mb-3">
                  <label htmlFor="owner" className="form-label">
                    Właściciel Kota
                  </label>
                  <select
                    className="form-select"
                    name="ownerId"
                    value={cat.ownerId}
                    onChange={onInputChange}
                  >
                    <option value="">Wybierz właściciela</option>
                    {owners.map((owner) => (
                      <option key={owner.id} value={owner.id}>
                        {owner.name}
                      </option>
                    ))}
                  </select>
                </div>
                <button type="submit" className="btn btn-primary w-100">
                  Zaktualizuj Kota
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
