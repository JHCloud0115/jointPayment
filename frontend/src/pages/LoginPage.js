import styled from '@emotion/styled';
import DefaultLayout from "../layouts/DefaultLayout";
import LoginInputs from "../components/login/LoginInputs";
const LoginPage = ()=>{
    return(
        <DefaultLayout title={"로그인"}>
            <LoginInputs/>
        </DefaultLayout>
    )
}

export default LoginPage

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
`;
