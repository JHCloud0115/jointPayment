package org.example.exception.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.exception.errorCode.ErrorCode;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{
    private final ErrorCode errorCode;

}
