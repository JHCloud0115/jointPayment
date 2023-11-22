import Nav from "../components/layouts/Nav";
import styled from "@emotion/styled";

const HomeLayout = ({children, title=""})=>{
    return(
        <LayoutWrapper>
            <Content>{children}</Content>
            <Nav />
        </LayoutWrapper>)
}

export  default HomeLayout

const LayoutWrapper = styled.div`
  width: 100%;
  height: 100vh;
  max-width: 480px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`

const Content = styled.div`
 `