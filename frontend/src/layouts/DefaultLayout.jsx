import styled from "@emotion/styled";
import SvgWrapper from "../components/SvgWrapper";
import ArrowLeftSvg from "../components/svgs/ArrowLeftSvg";
import {useNavigate} from "react-router";

const DefaultLayout = ({children, title=""})=>{
    const navigate = useNavigate();
    return(
        <LayoutWrapper>
            <ContentHeader>
                <SvgWrapper onClick = {()=> navigate(-1)}>
                    <ArrowLeftSvg diameter={18} color="var(--main)" />
                </SvgWrapper>
                <h1>{title}</h1>
            </ContentHeader>
            <Content>{children}</Content>
        </LayoutWrapper>)
}

export  default DefaultLayout

const LayoutWrapper = styled.div`
  display: flex;
  justify-content: center;
  height: 100vh;
`
const ContentHeader = styled.div`
  width: 100%;
  max-width: 480px;
  height: 48px;
  padding: 0px 16px;
  position: fixed;
  top: 0;
  left: 50%;
  z-index: 3;
  transform: translateX(-50%);

  display: grid;
  grid-template-columns: 24px 1fr;
  column-gap: 16px;
  align-items: center;

  border-bottom: 1px solid transparent;
  transition: all 0.15s ease-in-out;

  &.scrolled {
    border-bottom: ${(p) => `1px solid ${p.borderColor}`};
  }

  > h1 {
    display: flex;
    align-items: center;
    font: var(--heading2);
  }
`;

const Content = styled.div`
    width: 100%;
    max-width: 480px;
    padding: 16px;
    margin-top: 16px;
 `