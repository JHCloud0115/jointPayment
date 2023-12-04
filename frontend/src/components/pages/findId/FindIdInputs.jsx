import styled from '@emotion/styled';
import {useNavigate} from "react-router";
import {useMemo} from "react";
import axios from "axios";
import UseFormInput from "../../inputs/FormInput";
import {useFormContext} from "react-hook-form";
import Buttons from "../../button";


const FindIdInputs = () => {
    const navigate = useNavigate();
    const {
        watch,
        register,
        setError,
        clearErrors,
        handleSubmit,
        formState: { errors },
    } = useFormContext();
    const{ name, phone } = watch();

    const validate = useMemo(
        () => ({
            /**
             * @type {RegisterOptions}
             */
            name: {
                required: "이름을 입력해주세요.",
                maxLength: {
                    value: 50,
                    message: "50자 이하의 영문 및 숫자의 조합으로 입력해주세요.",
                },
            },
            /**
             * @type {RegisterOptions}
             */
            passwd: {
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
        req.name = name;
        req.cellphone = phone;

        console.log(req)

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
                    {...register("id", validate.name)}
                />
                <UseFormInput
                    placeholder="휴대폰 번호 입력"
                    label="휴대폰 번호"
                    err={errors.phone}
                    {...register("passwd", validate.phone)}
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
