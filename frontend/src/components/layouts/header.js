import styled from '@emotion/styled';
const Header = () => {
    return (
        <Wrapper>
            headers
        </Wrapper>
    );
};

export default Header;

const Wrapper = styled.header`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 77px;
  padding: 0 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 100;
`;
