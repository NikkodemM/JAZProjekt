import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./layout/Navbar";
import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddCat from "./cats/AddCat";
import EditCat from "./cats/EditCat";
import ViewCat from "./cats/ViewCat";
import AddOwner from "./owners/AddOwner";
import CatFact from "./pages/CatFact";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          {/* Strona główna */}
          <Route exact path="/" element={<Home />} />

          {/* Koty */}
          <Route exact path="/addcat" element={<AddCat />} />
          <Route exact path="/editcat/:id" element={<EditCat />} />
          <Route exact path="/viewcat/:id" element={<ViewCat />} />

          {/* Właściciele */}
          <Route exact path="/addowner" element={<AddOwner />} />

          {/* Fakty o kotach */}
          <Route exact path="/catfact" element={<CatFact />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
