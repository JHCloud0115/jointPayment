import styled from '@emotion/styled';
import HomeLayout from "../layouts/HomeLayout";
const HomePage = ()=>{
    return(
        <HomeLayout>
            <Wrapper>
                <Content>
                    Content
                </Content>
            </Wrapper>
        </HomeLayout>
    )
}

export default HomePage

const Wrapper = styled.div`
  position: relative;
  width: 100%;
`;
const Content = styled.div`
  padding-top: 70px;
`;