import HomeHeader from "../components/layouts/header/HomeHeader";
import Nav from "../components/layouts/Nav";
import styled from "@emotion/styled";

const HomeLayout = ({children, title=""})=>{
    return(
        <LayoutWrapper>
            <HomeHeader />
            <Content>{children}</Content>
            <Nav />
        </LayoutWrapper>)
}

export  default HomeLayout

const LayoutWrapper = styled.div`

  
`
const Content = styled.div`  
  width: 100%;
  max-width: 480px;
  height: 100%;

 `