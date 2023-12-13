import styled from '@emotion/styled';
import {useNavigate} from "react-router";
import {useMemo, useState} from "react";
import axios from "axios";
import UseFormInput from "../../inputs/FormInput";
import {useFormContext} from "react-hook-form";
import Buttons from "../../button";


const FindIdInputs = () => {
    const navigate = useNavigate();
    const {
        watch,
        reset,
        register,
        setError,
        clearErrors,
        handleSubmit,
        formState: { errors },
    } = useFormContext();
    const{ name, phone } = watch();
    const [email, setEmail] =useState("")

    const validate = useMemo(
        () => ({
            /**
             * @type {RegisterOptions}
             */
            name: {
                required: "이름을 입력해주세요.",
                onChange: (e) => {
                    if (e.target.value === "") {
                        setError("name", {
                            type: "required",
                            message: "이름을 입력해주세요.",
                        });
                        return;
                    }
                    clearErrors("name");
                },
            },
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

    const onSubmit = () => {
        let req = {};
        req.memberName = name;
        req.cellphone = phone;

        console.log(req)

        axios.post(" http://localhost:8080/member/find/email", req).then((res) => {
            const response = res.data;
            console.log(response)
            if (response.email) {
                setEmail(response.email)
                alert("이메일은 "+ response.email + " 입니다.")
                reset()
            }else{
                alert(response.message)
            }
        });

    };

    return (
        <Wrapper>
            <InputWrap>
                <UseFormInput
                    minLength={4}
                    maxLength={50}
                    placeholder="이름 입력"
                    label="이름"
                    err={errors.name}
                    {...register("name", validate.name)}
                />
                <UseFormInput
                    placeholder="휴대폰 번호 입력"
                    label="휴대폰 번호"
                    err={errors.phone}
                    {...register("phone", validate.phone)}
                />
            </InputWrap>

            <Buttons
                disabled={errors.name || errors.phone || !name || !phone}
                onClick={handleSubmit(onSubmit)}>
                아이디 찾기
            </Buttons>

        </Wrapper>
    );
};

export default FindIdInputs;

const Wrapper = styled.div`
  gap: 24px;
  margin-bottom: 40px;
`;

const InputWrap = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin: 24px 0;
`;
