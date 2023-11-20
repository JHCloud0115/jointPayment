package org.example.config.datasource;


import org.example.common.util.JwtAuthenticationFilter;
import org.example.common.util.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/member/lgoin")
                .loginProcessingUrl("/member/login")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/member/logout") // 로그아웃 URL
                .logoutSuccessUrl("/member/home") // 로그아웃 성공 시 이동할 페이지
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .addFilter(new JwtAuthenticationFilter(tokenProvider))
                .sessionManagement().disable();

    }
}
