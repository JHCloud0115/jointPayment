import { forwardRef, useCallback, useRef } from "react";
import styled from '@emotion/styled';

/**
 * @typedef {object} PrototypeInputProps
 * @property {string} type
 * @property {string} label
 * @property {Function} onFocus
 * @property {Function} onBlur
 * @property {string} placeholder
 * @property {"text" | "decimal" | "numeric" | "email" | "url"} inputMode
 * @property {string} count
 * @property {string} height
 * @property {string} marginTop
 * @property {string} marginBottom
 * @property {string} autoComplete
 * @property {boolean} errorHidden
 * @property {string} defaultMessage
 */

const UseFormInput = forwardRef(
    /**
     * @param {PrototypeInputProps} props
     * @returns
     * @param ref
     */
    (
        {
            type = "text",
            label = "",
            onFocus = () => {},
            onBlur = () => {},
            placeholder = "",
            inputMode = "text",
            isValidState = { validated: true, message: "", type: "" },
            count = "",
            err = null,
            height = "48px",
            marginTop = "0",
            marginBottom = "0",
            autoComplete = "off",
            errorHidden = false,
            defaultMessage = null,
            ...rest
        },
        ref
    ) => {

        const wrapperRef = useRef(null);

        // const handleOutsideClick = useCallback(() => {
        //   if (!wrapperRef || !wrapperRef.current) return;
        //   wrapperRef.current.childNodes.forEach((node) => {
        //     if (node.tagName === "INPUT" && document.activeElement === node) {
        //       node.blur();
        //     }
        //   });
        // }, [wrapperRef]);

        // useClickOutside(wrapperRef, handleOutsideClick);

        return (
            <Wrapper marginTop={marginTop} marginBottom={marginBottom}>
                {label && <Label>{label}</Label>}
                <InputWrapper ref={wrapperRef} height={height}>
                    <Input
                        ref={ref}
                        className={`${err?.message ? "invalid" : ""}`}
                        onFocus={onFocus}
                        onBlur={onBlur}
                        placeholder={placeholder}
                        inputMode={inputMode}
                        type={type}
                        autoComplete={autoComplete}
                        {...rest}
                    />
                    {/*<Count>{count}</Count>*/}
                </InputWrapper>

                <ValidationMessage
                    className={`${err?.message ? "invalid" : ""} ${
                        errorHidden ? "hidden" : ""
                    }`}
                >
                    {err?.message}
                </ValidationMessage>
                {!!defaultMessage && !err?.message ? (
                    <DefaultMessage>{defaultMessage}</DefaultMessage>
                ) : null}
            </Wrapper>
        );
    }
);


export default UseFormInput;

const Wrapper = styled.div`
  margin-top: ${(props) => props.marginTop};
  margin-bottom: ${(props) => props.marginBottom};
`;

const Label = styled.p`
`;
const InputWrapper = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 8px;
  height: ${(props) => props.height};
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

const ValidationMessage = styled.p`
  display: block;
  font: var(--meta);
  margin-bottom: 4px;
  color: var(--status-increase);
  background-color: transparent;
  opacity: 0;
  transition: all 0.1s ease-in-out;

  &.hidden {
    display: none;
  }

  &.invalid {
    opacity: 1;
  }
`;
const Count = styled.span`
  display: block;
  position: absolute;
  top: 50%;
  right: 16px;
  transform: translateY(-50%);
  font: var(--body-sub);
  color: var(--sub);
  background-color: transparent;
`;
const DefaultMessage = styled.p`
  color: var(--sub, #868d9a);
  font: var(--body-sub);
`;
