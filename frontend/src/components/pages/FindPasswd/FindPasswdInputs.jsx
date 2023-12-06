import styled from '@emotion/styled';
import {useNavigate} from "react-router";
import {useMemo} from "react";
import axios from "axios";
import UseFormInput from "../../inputs/FormInput";
import {useFormContext} from "react-hook-form";
import Buttons from "../../button";
import ApplicationConstants from "../../../common/ApplicationConstants";


const FindPasswdInputs = () => {
    const navigate = useNavigate();
    const {
        watch,
        register,
        setError,
        clearErrors,
        handleSubmit,
        formState: { errors },
    } = useFormContext();
    const{ email, name, phone } = watch();

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
        req.email = email;
        req.memberName = name;
        req.cellphone = phone;

        console.log(req)

        axios.post(" http://localhost:8080/member/find/password", req).then((res) => {
            const response = res.data;
            console.log(response)
            if (response.code === "0") {
                alert(response.data)
                // navigate("/login")
            }else{
                alert(response.message)
            }
        });

    };

    return (
        <Wrapper>
            <InputWrap>
                <UseFormInput
                    placeholder="이메일 입력"
                    label="이메일"
                    err={errors.email}
                    {...register("email", validate.email)}
                />
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
                disabled={errors.email || errors.name || errors.phone || !email || !name || !phone}
                onClick={handleSubmit(onSubmit)}>
                비밀번호 찾기
            </Buttons>
        </Wrapper>
    );
};

export default FindPasswdInputs;

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
