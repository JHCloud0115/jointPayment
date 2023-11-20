package org.example.common.util;

import io.jsonwebtoken.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.example.mapper.member.MemberTokenMapper;
import org.example.model.response.TokenResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    private final String secretKey = "joint_payment_secret_key";
    private final int accessTokenValidity = 1000 * 60 * 30; // 30분
    private final int refreshTokenValidity = 1000 * 60 * 60 * 24 * 7; // 7일

    private AuthenticationManagerBuilder authenticationManagerBuilder;

    public TokenProvider(
            AuthenticationManagerBuilder authenticationManagerBuilder
    ){
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }


    public String createAccessToken(@Param("email") String email) throws Exception {
        return createToken(email,accessTokenValidity);
    }

    public String createRefreshToken(@Param("email") String email) throws Exception {
        return createToken(email,refreshTokenValidity);
    }

    // 토큰 생성
    public String createToken(@Param("email") String email,int validity) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Claims claims = Jwts.claims().setSubject(email); // user 식별할 값
        claims.put("auth","auth");


        Date now = new Date();

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validity))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }


    // 토큰에서 회원 정보 추출
    public Authentication getAutnentication (String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        if(claims.get("auth")==null){
            throw  new RuntimeException("권한 정보가 없음");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);

    }


    public boolean validateToken(String token) throws Exception{
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e){
            throw  new Exception("Expired");
        }catch (UnsupportedJwtException e){
            throw  new Exception("UnsupportedJwtException");
        }catch (IllegalArgumentException e){
            throw  new Exception("IllegalArgumentException");
        }
    }


}
