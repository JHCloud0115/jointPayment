import React, { useMemo } from "react";
import { RegisterOptions, useFormContext } from "react-hook-form";
import styled from '@emotion/styled';
import UseFormInput from "../inputs/FormInput";
import CommonUtils from "../../utils/CommonUtils";

const PasswordInput = () => {
    const {
        watch,
        register,
        setError,
        clearErrors,
        formState: { errors },
    } = useFormContext();
    const { passwd, passwdConfirm } = watch();
    const validate = useMemo(
        () => ({
            /**
             * @type {RegisterOptions}
             */
            passwd: {
                required: "10자 이상 입력해주세요.",
                minLength: {
                    value: 10,
                    message: "10자 이상 입력해주세요.",
                },
                maxLength: {
                    value: 50,
                    message: "50자 이하로 입력해주세요.",
                },
                validate: (value) => {
                    if (!CommonUtils.isPasswordRegex(value)) {
                        return "대문자, 소문자, 숫자, 특수문자 중 2가지를 조합하여 10자 이상 입력해주세요.";
                    }
                    return true;
                },
                onChange: (e) => {
                    const replaced = e.target.value.replace(/\s/gi, "");
                    e.target.value = replaced;
                    if (e.target.value !== passwdConfirm) {
                        setError("passwdConfirm", {
                            type: "unmatched",
                            message: "입력하신 비밀번호와 일치하지 않습니다.",
                        });
                    }
                    if (!e.target.value || e.target.value.length < 10) {
                        setError("passwd", {
                            type: "minLength",
                            message: "10자 이상 입력해주세요.",
                        });
                        return;
                    }
                    if (e.target.value.length > 50) {
                        setError("passwd", {
                            type: "maxLength",
                            message: "50자 이하로 입력해주세요.",
                        });
                        return;
                    }

                    if (!CommonUtils.isPasswordRegex(e.target.value)) {
                        setError("passwd", {
                            type: "regExp",
                            message:
                                "대문자, 소문자, 숫자, 특수문자 중 2가지를 조합하여 10자 이상 입력해주세요.",
                        });
                        return;
                    }

                    clearErrors("passwd");
                },
            },
            /**
             * @type {RegisterOptions}
             */
            passwdConfirm: {
                required: "10자 이상 입력해주세요.",
                validate: (value) => {
                    if (value !== passwd) {
                        return "입력하신 비밀번호와 일치하지 않습니다.";
                    }
                    return true;
                },
                onChange: (e) => {
                    if (e.target.value !== passwd) {
                        setError("passwdConfirm", {
                            type: "unmatched",
                            message: "입력하신 비밀번호와 일치하지 않습니다.",
                        });
                        return;
                    }
                    clearErrors("passwdConfirm");
                },
            },
        }),
        [setError, clearErrors, passwd, passwdConfirm]
    );
    return (
        <Wrapper>
            <UseFormInput
                label="비밀번호*"
                type="password"
                placeholder="비밀번호 입력"
                autoComplete="new-password"
                minLength="10"
                maxLength="50"
                defaultMessage="영문 대소문자,숫자,특수문자를 2개 이상 포함한 10~50자"
                err={errors.passwd}
                {...register("passwd", validate.passwd)}
            />
            <UseFormInput
                label="비밀번호 확인*"
                type="password"
                placeholder="비밀번호 확인"
                autoComplete="new-password"
                minLength="10"
                maxLength="50"
                err={errors.passwdConfirm}
                {...register("passwdConfirm", validate.passwdConfirm)}
            />
        </Wrapper>
    );
};

export default React.memo(PasswordInput);

const Wrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 24px;
`;

