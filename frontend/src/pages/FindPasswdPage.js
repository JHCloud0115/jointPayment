import DefaultLayout from "../layouts/DefaultLayout";
import {FormProvider, useForm} from "react-hook-form";
import FindPasswdInputs from "../components/pages/FindPasswd/FindPasswdInputs";
const initialFormState = {
    email: "",
    name: "",
    phone:""
};
const FindPasswdPage = ()=>{
    const methods = useForm({defaultValues:initialFormState})
    return(
        <DefaultLayout title={"비밀번호 찾기"}>
            <FormProvider {...methods}>
                <FindPasswdInputs/>
            </FormProvider>
        </DefaultLayout>
    )
}

export default FindPasswdPage


