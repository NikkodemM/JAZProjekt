import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

export default function Home() {
  const [cats, setCats] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadCats();
  }, []);

  const loadCats = async () => {
    const result = await axios.get("http://localhost:8080/cats/all");
    setCats(result.data);
  };

  const deleteCat = async (id) => {
    if (window.confirm("Are you sure you want to delete this cat?")) {
      await axios.delete(`http://localhost:8080/cats/del/${id}`);
      loadCats();
    }
  };

  const goToCatFact = () => {
    navigate("/catfact");
  };

  return (
    <div className="container my-5">
      <div className="row">
        <div className="col text-center mb-4">
          <h1 className="display-4 text-primary">Lista Kotów</h1>
          <p className="lead text-muted">
            Zarządzaj swoimi kotami i odkrywaj ciekawe fakty o nich!
          </p>
          <hr className="w-50 mx-auto" />
        </div>
      </div>

      <div className="row justify-content-center">
        <div className="col-md-10">
          <div className="card shadow-lg border-0">
            <div className="card-header bg-primary text-white text-center">
              <h2>Koty w Twojej Kolekcji</h2>
            </div>
            <div className="card-body p-4">
              {cats.length > 0 ? (
                <table className="table table-hover table-striped text-center align-middle">
                  <thead className="table-dark">
                    <tr>
                      <th>ID</th>
                      <th>Imię</th>
                      <th>Rasa</th>
                      <th>Wiek</th>
                      <th>Właściciel</th>
                      <th>Akcje</th>
                    </tr>
                  </thead>
                  <tbody>
                    {cats.map((cat, index) => (
                      <tr key={index}>
                        <td>{index + 1}</td>
                        <td>{cat.name}</td>
                        <td>{cat.breed.name}</td>
                        <td>{cat.age}</td>
                        <td>{cat.owner?.name || "Brak właściciela"}</td>
                        <td>
                          {/* Przycisk View */}
                          <Link
                            to={`/viewcat/${cat.id}`}
                            className="btn btn-success btn-sm mx-1"
                          >
                            <i className="bi bi-eye"></i> View
                          </Link>

                          {/* Przycisk Edit */}
                          <Link
                            to={`/editcat/${cat.id}`}
                            className="btn btn-warning btn-sm mx-1"
                          >
                            <i className="bi bi-pencil"></i> Edit
                          </Link>

                          {/* Przycisk Delete */}
                          <button
                            onClick={() => deleteCat(cat.id)}
                            className="btn btn-danger btn-sm mx-1"
                          >
                            <i className="bi bi-trash"></i> Delete
                          </button>

                          {/* Przycisk Cat Fact */}
                          <button
                            onClick={goToCatFact}
                            className="btn btn-info btn-sm mx-1"
                          >
                            <i className="bi bi-chat-left-text"></i> Cat Fact
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              ) : (
                <p className="text-center fs-5">Brak kotów w bazie danych.</p>
              )}
            </div>
            <div className="card-footer text-muted text-center">
              Wszystkie dane są aktualne na dzień dzisiejszy.
            </div>
          </div>
        </div>
      </div>

      {/* Przycisk dodawania nowego kota */}
      <div className="row mt-4">
        <div className="col text-center">
          <Link to="/addcat" className="btn btn-primary btn-lg shadow-sm">
            Dodaj Nowego Kota
          </Link>
        </div>
      </div>
    </div>
  );
}
