import { css, cx } from "@emotion/css";
import styled from "@emotion/styled";
import React from "react";

const Button = (props) => {
    return (
        <Wrapper>
            {props.type === "sub" ? (
                <SubButton
                    className={props.disabled ? cx(disabledStyles) : cx(activeSub)}
                    onClick={props.onClick}
                    {...props}
                >
                    {props.children}
                </SubButton>
            ) : (
                <MainButton
                    className={props.disabled ? cx(disabledStyles) : cx(activeMain)}
                    onClick={props.onClick}
                    {...props}
                >
                    {props.children}
                </MainButton>
            )}
        </Wrapper>
    );
};

export default Button;

Button.defaultProps = {
    width: "100%",
    height: "56px",
    margin: "0px",
    padding: "0px",
    font: "var(--body16)",
    onClick: () => {},
};
const activeMain = css`
  color: var(--btnText);
  background-color: var(--brandColor) !important;
  cursor: pointer;
  &:active {
    background-color: var(--pressedBtnMain);
  }
`;
const activeSub = css`
  color: var(--main);
  background-color: var(--seconBtn) !important;
  border: 1px solid var(--buttonStroke);
  cursor: pointer;
  &:active {
    background-color: var(--pressedBtnSub);
  }
`;
const disabledStyles = css`
  color: var(--disableTxt);
  background-color: var(--brandColor) !important;
  border: 0px;
  opacity: 40%;
`;
const Wrapper = styled.div``;
const MainButton = styled.button`
  font: ${(props) => props.font};
  font-weight: ${(props) => props.fontWeight};
  font-size: ${(props) => props.fontSize};
  line-height: ${(props) => props.fontHeight};
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  margin: ${(props) => props.margin};
  padding: ${(props) => props.padding};
  border: 0px;
  border-radius: ${(props) =>
    props.borderRadius ? props.borderRadius : "5px"};
  text-align: center;
  white-space: nowrap;
`;
const SubButton = styled.button`
  font: ${(props) => props.font};
  font-weight: ${(props) => props.fontWeight};
  font-size: ${(props) => props.fontSize};
  line-height: ${(props) => props.fontHeight};
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  margin: ${(props) => props.margin};
  padding: ${(props) => props.padding};
  border-radius: ${(props) =>
    props.borderRadius ? props.borderRadius : "5px"};
  text-align: center;
  white-space: nowrap;
`;
