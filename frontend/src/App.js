import './App.css';
import {Route, Routes} from "react-router-dom";
import HomeHeader from "./components/layouts/header/HomeHeader";
import HomePage from "./pages/HomePage";
import styled from "@emotion/styled";
import "./styles/globals.scss";
import Nav from "./components/layouts/Nav";

function App() {
  return (
    <AppComponent>
        <HomeHeader/>
        <Routes>
            <Route path={"/"} element={<HomePage/>}/>
        </Routes>
    </AppComponent>
  );
}

export default App;

const AppComponent = styled.div``;