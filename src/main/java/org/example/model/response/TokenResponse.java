package org.example.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {
    private String message;
    private String accessToken;
    private String refreshToken;

    public TokenResponse(String message, String accessToken, String refreshToken) {
        this.message = message;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;

    }
}
