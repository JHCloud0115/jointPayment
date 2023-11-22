import styled from '@emotion/styled';
import axios from "axios";
import {useState} from "react";
import {useNavigate} from "react-router";

const SignupInputs = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [passwordCheck, setPasswordCheck] = useState("")
    const [memberName, setMemberName] = useState("")
    const [cellphone, setCellPhone] = useState("")
    const onSubmit = () => {

            let req = {};
            req.email = email;
            req.password = password;
            req.passwordCheck = passwordCheck;
            req.memberName = memberName;
            req.cellphone = cellphone;

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
            <LoginInputWrap>
                {/*이메일 중복확인*/}
                <InputWrap>
                    <Label>이메일*</Label>
                    <Input
                        value={email}
                        onChange={(event)=>{setEmail(event.target.value)}}
                        minLength={4}
                        maxLength={50}
                        placeholder="이메일 입력"
                    />
                </InputWrap>
                <InputWrap>
                    <Label>비밀번호*</Label>
                    <Input
                        value={password}
                        onChange={(event)=>{setPassword(event.target.value)}}
                        minLength={10}
                        maxLength={50}
                        placeholder="비밀번호 입력"
                        type="password"
                    />
                </InputWrap>
                <InputWrap>
                    <Label>비밀번호 확인*</Label>
                    <Input
                        value={passwordCheck}
                        onChange={(event)=>{setPasswordCheck(event.target.value)}}
                        minLength={10}
                        maxLength={50}
                        placeholder="비밀번호 확인"
                        type="password"
                    />
                </InputWrap>
                <InputWrap>
                    <Label>이름*</Label>
                    <Input
                        value={memberName}
                        onChange={(event)=>{setMemberName(event.target.value)}}
                        minLength={10}
                        maxLength={50}
                        placeholder="이름 입력"
                    />
                </InputWrap>
                <InputWrap>
                    <Label>휴대폰 번호*</Label>
                    <Input
                        value={cellphone}
                        onChange={(event)=>{setCellPhone(event.target.value)}}
                        minLength={10}
                        maxLength={50}
                        placeholder="휴대폰 번호 입력"
                    />
                </InputWrap>
            </LoginInputWrap>

            <Button onClick={()=>onSubmit()}>
               회원가입
            </Button>

        </Wrapper>
    );
};

export default SignupInputs;

const Wrapper = styled.div`
  gap: 24px;
  margin-bottom: 40px;
`;
const LoginInputWrap = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
  margin: 40px 0;
`;
const InputWrap = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
`;
const Label = styled.div`

`;
const Input = styled.input`
  padding: 8px 16px;
  border-radius: 5px;
  transition: all 0.1s ease-in-out;
  font: var(--body-sub);

  border: 1px solid var(--buttonStroke);
  background-color: inherit;
  &::placeholder {
    color: var(--sub);
  }
  &:disabled {
    background: var(--textBox, #f9f9f9);
  }
  &.invalid {
    border: 1px solid var(--status-increase);
    background-color: var(--status-increase-fill);
    color: var(--status-increase);
    -webkit-text-fill-color: var(---status-increase);
    &::placeholder {
      color: var(--status-increase);
      -webkit-text-fill-color: var(--status-increase);
    }
  }
`;
const Button = styled.div`
  color: var(--btnText);
  background-color: var(--brandColor);
  font: var(--body-sub);
  width: 100%;
  height: 56px;
  border-radius: 10px;
  opacity: 0.4;
  display: flex;
  align-items: center;
  justify-content: center;
`;

