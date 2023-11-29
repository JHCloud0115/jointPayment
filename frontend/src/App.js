import './App.css';
import {Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage";
import styled from "@emotion/styled";
import "./styles/globals.scss";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";
import GamePage from "./pages/GamePage";
import FindIdPage from "./pages/FindIdPage";
import FindPasswdPage from "./pages/FindPasswdPage";

function App() {
  return (
    <AppComponent>
        <Routes>
            <Route path={"/"} element={<HomePage/>}/>
            <Route path={"/game"} element={<GamePage/>}/>
            <Route path={"/login"} element={<LoginPage/>}/>
            <Route path={"/regist"} element={<SignupPage/>}/>
            <Route path={"/findId"} element={<FindIdPage/>}/>
            <Route path={"/findPasswd"} element={<FindPasswdPage/>}/>
        </Routes>
    </AppComponent>
  );
}

export default App;

const AppComponent = styled.div``;