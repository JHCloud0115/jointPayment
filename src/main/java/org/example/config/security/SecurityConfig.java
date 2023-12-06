package org.example.config.security;

import org.example.common.util.TokenAuthenticationFilter;
import org.example.common.util.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterAfter(new TokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/member/login","/member/regist", "/auth/logout").permitAll()
                .and()
                .logout() // 로그아웃 설정 추가
                .logoutUrl("/auth/logout") // 로그아웃 URL 지정
                .logoutSuccessUrl("/member/login") // 로그아웃 성공 시 리다이렉트할 URL 지정
                .invalidateHttpSession(true) // HTTP 세션 무효화
                .deleteCookies("JSESSIONID", "accessToken"); // 로그아웃 시 삭제할 쿠키 지정



        return http.build();
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
