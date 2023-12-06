package org.example.common.util;

import io.jsonwebtoken.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

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
        claims.put("email",email);


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

    //토큰 검증
    public String validateToken(String token){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.get("email", String.class);

            if(Objects.isNull(email)){
                return null;
            }
            if(!validateExpiredToken(token)){
                return null;
            }
            return email;

        }catch(Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    public boolean validateExpiredToken(String token) throws Exception{
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
