import styled from '@emotion/styled';
const HomeHeader = () => {
    return (
        <Wrapper>
            <h1>Joint Payment</h1>
        </Wrapper>
    );
};

export default HomeHeader;

const Wrapper = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 100;
  background: lightblue;
`;
