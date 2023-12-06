import { useMemo } from "react";
import { useFormContext } from "react-hook-form";
import styled from '@emotion/styled';
import UseFormInput from "../../inputs/FormInput";

const NameInput = () => {
    const {
        register,
        setError,
        clearErrors,
        formState: { errors },
    } = useFormContext();
    const validate = useMemo(
        () => ({
            /**
             * @type {RegisterOptions}
             */
            name: {
                required: "이름을 입력해주세요.",
                onChange: (e) => {
                    if (e.target.value === "") {
                        setError("name", {
                            type: "required",
                            message: "이름을 입력해주세요.",
                        });
                        return;
                    }
                    clearErrors("name");
                },
            },
        }),
        [setError, clearErrors]
    );
    return (
        <Wrapper>
            <UseFormInput
                label="이름*"
                placeholder="이름 입력"
                maxLength={30}
                err={errors.name}
                {...register("name", validate.name)}
            />
        </Wrapper>
    );
};

export default NameInput;

const Wrapper = styled.div`
  width: 100%;
`;
