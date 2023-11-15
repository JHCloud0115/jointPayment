import './App.css';
import {Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage";
import styled from "@emotion/styled";
import "./styles/globals.scss";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";
import GamePage from "./pages/GamePage";

function App() {
  return (
    <AppComponent>
        <Routes>
            <Route path={"/"} element={<HomePage/>}/>
            <Route path={"/game"} element={<GamePage/>}/>
            <Route path={"/login"} element={<LoginPage/>}/>
            <Route path={"/regist"} element={<SignupPage/>}/>
        </Routes>
    </AppComponent>
  );
}

export default App;

const AppComponent = styled.div``;