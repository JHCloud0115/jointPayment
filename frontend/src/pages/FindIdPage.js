import DefaultLayout from "../layouts/DefaultLayout";
import FindIdInputs from "../components/pages/findId/FindIdInputs";
import {FormProvider, useForm} from "react-hook-form";
const initialFormState = {
    id: "",
    phone: "",
};
const FindIdPage = ()=>{
    const methods = useForm({defaultValues:initialFormState})
    return(
        <DefaultLayout title={"아이디 찾기"}>
            <FormProvider {...methods}>
                <FindIdInputs/>
            </FormProvider>
        </DefaultLayout>
    )
}

export default FindIdPage