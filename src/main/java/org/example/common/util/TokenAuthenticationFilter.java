package org.example.common.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    public TokenAuthenticationFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractToken(request);

        if (token != null) {
            String email = tokenProvider.validateToken(token);
            // TODO 이주희 : 무슨기능인지 알아오기
//            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            request.setAttribute("email",email);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String memberToken = request.getHeader("Authorization");

        if (memberToken != null && memberToken.startsWith("Bearer")) {
            return memberToken.substring(7);
        }

        return null;
    }

}
