import { useMemo } from "react";
import { useFormContext } from "react-hook-form";
import styled from '@emotion/styled';
import UseFormInput from "../inputs/FormInput";

const PhoneInput = () => {
    const {
        register,
        setError,
        clearErrors,
        formState: { errors },
    } = useFormContext();
    const validate = useMemo(
        () => ({
            /**
             * @type {RegisterOptions}
             */
            phone: {
                required: "휴대폰 번호를 입력해주세요.",
                onChange: (e) => {
                    if (e.target.value === "") {
                        setError("phone", {
                            type: "required",
                            message: "휴대폰 번호를 입력해주세요.",
                        });
                        return;
                    }
                    clearErrors("phone");
                },
            },
        }),
        [setError, clearErrors]
    );
    return (
        <Wrapper>
            <UseFormInput
                label="휴대폰 번호*"
                placeholder="휴대폰 번호 입력"
                err={errors.phone}
                {...register("phone", validate.phone)}
            />
        </Wrapper>
    );
};

export default PhoneInput;

const Wrapper = styled.div`
  width: 100%;
`;
