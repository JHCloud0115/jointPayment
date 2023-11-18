package org.example.config.datasource;


import org.example.common.util.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

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
                .sessionManagement().disable();

//                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider),
//                        UsernamePasswordAuthenticationFilter.class); // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
        // + 토큰에 저장된 유저정보를 활용하여야 하기 때문에 CustomUserDetailService 클래스를 생성합니다.
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}
