import styled from '@emotion/styled';
import axios from "axios";
import React, {useState} from "react";
import {useNavigate} from "react-router";
import EmailInput from "./EmailInput";
import {useFormContext} from "react-hook-form";
import PasswordInput from "./PasswordInput";
import NameInput from "./NameInput";
import PhoneInput from "./PhoneInput";
import Button from "../../button";

const SignupInputs = () => {
    const navigate = useNavigate();
    const {
        watch,
        handleSubmit,
        formState: { errors },
    } = useFormContext();
    const { email ,passwd, passwdConfirm, name, phone} = watch()
    console.log(email)
    const onSubmit = () => {

        let req = {};
        req.email = email;
        req.password = passwd;
        req.passwordCheck = passwdConfirm;
        req.memberName = name;
        req.cellphone = phone;

        console.log(req)
        axios.post(" http://localhost:8080/member/regist", req).then((res) => {
            const response = res.data;
            console.log(response)
            if (response.code === "0") {
                navigate("/login")
            }else{
                alert(response.message)
            }
        });
    };
    return (
        <Wrapper>
            <InputWrap>
                <EmailInput/>
                <PasswordInput/>
                <NameInput/>
                <PhoneInput/>
            </InputWrap>

            <Button
                disabled={errors.email || errors.passwd ||errors.passwdConfirm || errors.name ||errors.phone
                    ||!email||!passwd||!passwdConfirm||!name||!phone}
                onClick={handleSubmit(onSubmit)}>
                회원가입
            </Button>
        </Wrapper>
    );
};

export default React.memo(SignupInputs);

const Wrapper = styled.div`
  margin: 40px 0;
`;
const InputWrap = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
  margin: 40px 0;
`;

