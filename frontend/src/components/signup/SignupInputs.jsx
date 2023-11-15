import styled from '@emotion/styled';
import axios from "axios";

const SignupInputs = () => {
    const onSubmit = () => {

            let req = {};
            req.email = "seeun@naver.com";
            req.password = "Newlink!234";
            req.passwordCheck = "Newlink!234";
            req.name = "박세은";
            req.cellphone = "01025456557";

            console.log(req)
            axios.post(" http://localhost:8080/member/regist", req).then((res) => {
                const recv = res.data;
                console.log(recv)
                if (res.code !== "0") {
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
                        minLength={4}
                        maxLength={50}
                        placeholder="이메일 입력"
                    />
                </InputWrap>
                <InputWrap>
                    <Label>비밀번호*</Label>
                    <Input
                        minLength={10}
                        maxLength={50}
                        placeholder="비밀번호 입력"
                        type="password"
                    />
                </InputWrap>
                <InputWrap>
                    <Label>비밀번호 확인*</Label>
                    <Input
                        minLength={10}
                        maxLength={50}
                        placeholder="비밀번호 확인"
                        type="password"
                    />
                </InputWrap>
                <InputWrap>
                    <Label>이름*</Label>
                    <Input
                        minLength={10}
                        maxLength={50}
                        placeholder="이름 입력"
                        type="password"
                    />
                </InputWrap>
                <InputWrap>
                    <Label>휴대폰 번호*</Label>
                    <Input
                        minLength={10}
                        maxLength={50}
                        placeholder="휴대폰 번호 입력"
                        type="password"
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

