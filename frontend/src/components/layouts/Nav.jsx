import styled from '@emotion/styled';
import HomeSvg from "../svgs/HomeSvg";
import {useNavigate} from "react-router";
import LoginUtils from "../../utils/LoginUtils";
const Nav = () => {
    const navigate = useNavigate();
    const isLogin = LoginUtils.isLogin()
    return (
        <Wrapper>
            <NavWrapper>
                <div>
                    <NavContent>
                        <IconWrapper onClick={()=>navigate("/")}>
                            <HomeSvg/>
                        </IconWrapper>
                        <p>홈</p>
                    </NavContent>
                </div>
                <div>
                    <NavContent>
                        <IconWrapper onClick={()=>navigate("/game")}>
                            <HomeSvg
                            />
                        </IconWrapper>
                        <p>게임하기</p>
                    </NavContent>
                </div>
                <div>
                    {isLogin ?
                        <NavContent >
                            <IconWrapper onClick={()=>navigate("/mypage")}>
                                <HomeSvg
                                />
                            </IconWrapper>
                            <p>내 정보</p>
                        </NavContent>:
                        <NavContent >
                            <IconWrapper onClick={()=>navigate("/login")}>
                                <HomeSvg
                                />
                            </IconWrapper>
                            <p>로그인</p>
                        </NavContent>
                    }

                </div>
            </NavWrapper>
        </Wrapper>
    );
};

export default Nav;

const Wrapper = styled.div`
  width: 100%;
  height: 70px;
  position: fixed;
  left: 50%;
  bottom: 0;
  transform: translateX(-50%);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 2;

`;
const NavWrapper = styled.div`
  max-width: 480px;
  height: 56px;
  width: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: 1fr;
  > div {
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
  }
`;
const NavContent = styled.div`
  width: 24px;
  display: flex;
  padding-top: 8px;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  gap: 4px;

  > p {
    font: var(--meta);
    color: var(--disableTxt);
    white-space: nowrap;
    transition: all 0.15s ease-in-out;
  }
`;
const IconWrapper = styled.div`
  width: 24px;
  height: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
`;
