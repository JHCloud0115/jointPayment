import styled from '@emotion/styled';
import {useNavigate} from "react-router";

const LoginInputs = () => {
    const navigate = useNavigate();
    return (
        <Wrapper>
            <LoginInputWrap>
                <InputWrap>
                    <Label>아이디</Label>
                    <Input
                        minLength={4}
                        maxLength={50}
                        placeholder="아이디 입력"
                    />
                </InputWrap>
                <InputWrap>
                    <Label>비밀번호</Label>
                    <Input
                        minLength={10}
                        maxLength={50}
                        placeholder="비밀번호 입력"
                        type="password"
                    />
                </InputWrap>
            </LoginInputWrap>

            <Button>
                로그인
            </Button>
            <SubBtnWrap>
                <SubButton onClick={()=>navigate("/regist")}>
                    회원가입
                </SubButton>
                <SubButton>
                    아이디 찾기
                </SubButton>
                <SubButton>
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
  display: flex;
  align-items: center;  
  justify-content: center;
  opacity: 0.4;
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
