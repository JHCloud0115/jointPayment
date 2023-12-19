package org.example.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "01001", "Invalid parameter included"),
    INTERNAL_SERVER_ERROR(HttpStatus.OK, "01003", "Internal server error"),
    NOT_FOUND(HttpStatus.OK, "01004","Not found"),
    TOKEN_EXPIRED(HttpStatus.OK, "01005","token not acceptable"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


}
