import './App.css';
import {Route, Routes} from "react-router-dom";
import {Global} from "@emotion/react";
import {reset} from "./styles/reset";
import Header from "./components/layouts/header";
import HomePage from "./pages/HomePage";
import styled from "@emotion/styled";

function App() {
  return (
    <AppComponent>
        <Global styles={reset}/>
        <Header/>
        <Routes>
            <Route path={"/"} element={<HomePage/>}/>
        </Routes>
    </AppComponent>
  );
}

export default App;

const AppComponent = styled.div``;