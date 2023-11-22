import styled from '@emotion/styled';
import DefaultLayout from "../layouts/DefaultLayout";
import SignupInputs from "../components/signup/SignupInputs";
const SignupPage = ()=>{
    return(
        <DefaultLayout title={"회원가입"}>
            <SignupInputs/>
        </DefaultLayout>
    )
}

export default SignupPage

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
`;
