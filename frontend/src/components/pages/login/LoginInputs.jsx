import styled from '@emotion/styled';
import {useNavigate} from "react-router";
import {useMemo} from "react";
import axios from "axios";
import UseFormInput from "../../inputs/FormInput";
import {useFormContext} from "react-hook-form";
import Buttons from "../../button";
import LoginUtils from "../../../utils/LoginUtils";


const LoginInputs = () => {
    const navigate = useNavigate();
    const {
        watch,
        register,
        setError,
        clearErrors,
        handleSubmit,
        formState: { errors },
    } = useFormContext();
    const{ id, passwd } = watch();

    const validate = useMemo(
        () => ({
            /**
             * @type {RegisterOptions}
             */
            id: {
                required: "아이디를 입력해주세요.",
                maxLength: {
                    value: 50,
                    message: "50자 이하의 영문 및 숫자의 조합으로 입력해주세요.",
                },
                onChange: (e) => {
                    const replaced = e.target.value.replace(/\s/gi, "");
                    e.target.value = replaced;

                    if (!e.target.value || e.target.value === "") {
                        setError("id", {
                            type: "required",
                            message: "아이디를 입력해주세요.",
                        });
                        return;
                    }
                    clearErrors("id");
                },
            },
            /**
             * @type {RegisterOptions}
             */
            passwd: {
                required: "비밀번호를 입력해주세요.",
                maxLength: {
                    value: 50,
                    message: "50자 이하로 입력해주세요.",
                },
                onChange: (e) => {
                    const replaced = e.target.value.replace(/\s/gi, "");
                    e.target.value = replaced;
                    if (!e.target.value || e.target.value === "") {
                        setError("passwd", {
                            type: "required",
                            message: "비밀번호를 입력해주세요.",
                        });
                        return;
                    }
                    clearErrors("passwd");
                },
            },
        }),
        [setError, clearErrors]
    );

    const onSubmit = () => {
        let req = {};
        req.email = id;
        req.password = passwd;

        console.log(req)
        axios.post(" http://localhost:8080/auth/login", req).then((res) => {
            const response = res.data;
            console.log(response)

            // if (response.code === "0") {
            //     navigate("/home")
            // }else{
            //     alert(response.message)
            // }

            if (response.accessToken) {
                LoginUtils.login(response.accessToken);
                navigate("/")
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
                    placeholder="아이디 입력"
                    label="아이디"
                    err={errors.id}
                    {...register("id", validate.id)}
                />
                <UseFormInput
                    minLength={10}
                    maxLength={50}
                    placeholder="비밀번호 입력"
                    label="비밀번호"
                    type="password"
                    err={errors.passwd}
                    {...register("passwd", validate.passwd)}
                />
            </InputWrap>

            <Buttons
                disabled={errors.id || errors.passwd || !id || !passwd}
                onClick={handleSubmit(onSubmit)}>
                로그인
            </Buttons>
            <SubBtnWrap>
                <SubButton onClick={()=>navigate("/regist")}>
                    회원가입
                </SubButton>
                <SubButton onClick={()=>navigate("/findId")}>
                    아이디 찾기
                </SubButton>
                <SubButton onClick={()=>navigate("/findPasswd")}>
                    비밀번호 찾기
                </SubButton>
            </SubBtnWrap>

        </Wrapper>
    );
};

export default LoginInputs;

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

const SubBtnWrap = styled.div`
  display: flex;
  gap: 16px;
  justify-content: center;
  margin: 16px 0;
`;

const SubButton = styled.div`
    cursor: pointer;

`;
