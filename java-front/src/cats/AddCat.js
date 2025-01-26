import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function AddCat() {
  const [cat, setCat] = useState({
    name: "",
    age: "",
    breedName: "",
    ownerId: "",
  });

  const [breeds, setBreeds] = useState([]);
  const [owners, setOwners] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    loadBreeds();
    loadOwners();
  }, []);

  const loadBreeds = async () => {
    try {
      const result = await axios.get("http://localhost:8080/breeds/all");
      setBreeds(result.data);
    } catch (error) {
      console.error("Error loading breeds:", error);
    }
  };

  const loadOwners = async () => {
    try {
      const result = await axios.get("http://localhost:8080/owners/all");
      setOwners(result.data);
    } catch (error) {
      console.error("Error loading owners:", error);
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
      await axios.post("http://localhost:8080/cats/add", payload, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      navigate("/");
      alert("Kot został pomyślnie dodany!");
    } catch (error) {
      console.error("Error adding cat:", error);
      alert("Nie udało się dodać kota. Spróbuj ponownie.");
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-8">
          <div className="card shadow-lg border-0">
            <div className="card-header bg-primary text-white text-center">
              <h3>Dodaj Kota</h3>
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
                  Dodaj Kota
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
