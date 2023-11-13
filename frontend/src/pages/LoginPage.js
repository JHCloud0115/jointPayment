import styled from '@emotion/styled';
import DefaultLayout from "../layouts/DefaultLayout";
import LoginInputs from "../components/Login/LoginInputs";
const LoginPage = ()=>{
    return(
        <DefaultLayout title={"로그인"}>
            <Wrapper>
                <Content>
                 <LoginInputs/>
                </Content>
            </Wrapper>
        </DefaultLayout>
    )
}

export default LoginPage

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
`;
const Content = styled.div`
`;