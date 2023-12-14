package org.example.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    NOT_FOUND(HttpStatus.NOT_FOUND,"Not found"),
    TOKEN_EXPIRED(HttpStatus.NOT_ACCEPTABLE,"token not acceptable"),

    ;

    private final HttpStatus httpStatus;
    private final String message;

}
