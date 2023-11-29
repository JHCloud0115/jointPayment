import React, { useMemo } from "react";
import { RegisterOptions, useFormContext } from "react-hook-form";
import styled from '@emotion/styled';
import ApplicationConstants from "../../common/ApplicationConstants";
import UseFormInput from "../inputs/FormInput";


const EmailInput = () => {
    const {
        register,
        setError,
        clearErrors,
        watch,
        formState: { errors },
    } = useFormContext();
    const validate = useMemo(
        () => ({
            /**
             * @type {RegisterOptions}
             */
            email: {
                required: "4자 이상의 영문 및 숫자의 조합으로 입력해주세요.",
                minLength: {
                    value: 4,
                    message: "4자 이상의 영문 및 숫자의 조합으로 입력해주세요.",
                },
                maxLength: {
                    value: 50,
                    message: "50자 이하의 영문 및 숫자의 조합으로 입력해주세요.",
                },
                pattern: {
                    value: ApplicationConstants.EMAIL_REGEX,
                    message: "이메일 형식으로 입력해주세요.",
                },
                onChange: (e) => {
                    const replaced = e.target.value.replace(/\s/gi, "");
                    e.target.value = replaced;
                    if (e.target.value === "" || e.target.value.length < 4) {
                        setError("email", {
                            type: "required",
                            message: "4자 이상의 영문 및 숫자의 조합으로 입력해주세요.",
                        });
                        return;
                    }
                    if (e.target.value.length > 50) {
                        setError("email", {
                            type: "maxLength",
                            message: "50자 이하의 영문 및 숫자의 조합으로 입력해주세요.",
                        });
                        return;
                    }
                    if (!ApplicationConstants.EMAIL_REGEX.test(e.target.value)) {
                        setError("email", {
                            type: "regExp",
                            message: "이메일 형식으로 입력해주세요.",
                        });
                        return;
                    }
                    clearErrors("email");
                },
            },
        }),
        [setError, clearErrors]
    );


    return (
        <Wrapper>
            <UseFormInput
                label="이메일*"
                minLength="4"
                maxLength="50"
                placeholder="이메일 입력"
                err={errors.email}
                {...register("email", validate.email)}
            />
        </Wrapper>
    );
};

export default React.memo(EmailInput);

const Wrapper = styled.form`
  width: 100%;
  display: flex;
  flex-direction: column;
`;
