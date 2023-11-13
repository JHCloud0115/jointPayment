import styled from '@emotion/styled';
import HomeLayout from "../layouts/HomeLayout";
const HomePage = ()=>{
    return(
        <HomeLayout>
            <Wrapper>
                <Content>
                    Joint Payment
                </Content>
            </Wrapper>
        </HomeLayout>
    )
}

export default HomePage

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
`;
const Content = styled.div`
`;