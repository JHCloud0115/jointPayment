import styled from "@emotion/styled";

const SvgWrapper = ({
                        children,
                        diameter = 24,
                        cursor = "default",
                        ...props
                    }) => {
    return (
        <Wrapper diameter={diameter} cursor={cursor} {...props}>
            {children}
        </Wrapper>
    );
};

export default SvgWrapper;

const Wrapper = styled.div`
  width: ${(p) => `${p.diameter}px`};
  height: ${(p) => `${p.diameter}px`};
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: ${(p) => p.cursor};
`;
