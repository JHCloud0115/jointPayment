package org.example.common.util;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    // 권한 설정시 사용
    private  final TokenProvider tokenProvider;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = resolveToken((HttpServletRequest) request);

        try {
            if (token != null && tokenProvider.validateToken(token)) {
                Authentication authentication = tokenProvider.getAutnentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                setJwtCookie((HttpServletResponse) response, token);
            }

            if (shouldLogout(request)) {
                removeJwtCookie((HttpServletResponse) response);
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        chain.doFilter(request, response);
    }

    // 헤더에서 토큰 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken)){
            return bearerToken.substring(7);
        }
        return null;
    }
    // 로그아웃 url 설정
    private boolean shouldLogout(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        return "/auth/logout".equals(requestURI);
    }
    // 쿠키 제거
    private void removeJwtCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("JWT_TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // 쿠키 만료
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    // 쿠키 설정
    public void setJwtCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("JWT_TOKEN", token); //
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) TimeUnit.HOURS.toSeconds(1)); // 1시간 유효
        cookie.setPath("/"); // 쿠키의 사용 범위 설정
        response.addCookie(cookie); // 클라이언트로 쿠키 전송
    }




}
