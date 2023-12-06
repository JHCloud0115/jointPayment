import styled from '@emotion/styled';
import DefaultLayout from "../layouts/DefaultLayout";
import LoginInputs from "../components/pages/login/LoginInputs";
import {FormProvider, useForm} from "react-hook-form";

const initialFormState = {
    id: "",
    passwd: "",
};
const LoginPage = ()=>{
    const methods = useForm({ defaultValues: initialFormState });
    return(
        <DefaultLayout title={"로그인"}>
            <FormProvider {...methods}>
                <LoginInputs/>
            </FormProvider>
        </DefaultLayout>
    )
}

export default LoginPage

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
`;
