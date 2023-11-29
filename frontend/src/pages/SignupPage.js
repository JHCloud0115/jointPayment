import styled from '@emotion/styled';
import DefaultLayout from "../layouts/DefaultLayout";
import SignupInputs from "../components/signup";
import {FormProvider, useForm} from "react-hook-form";
const initialFormState ={
    email:"",
    password:"",
    passwordCheck:"",
    memberName:"",
    cellphone:""
}
const SignupPage = ()=>{
    const methods = useForm({defaultValues:initialFormState})
    return(
        <DefaultLayout title={"회원가입"}>
            <FormProvider {...methods}>
                <SignupInputs/>
            </FormProvider>
        </DefaultLayout>
    )
}

export default SignupPage

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
`;
