package org.example.model.response;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String message;
    private String accessToken;
    private String refreshToken;
    private Collection<? extends GrantedAuthority> authorities;

}
