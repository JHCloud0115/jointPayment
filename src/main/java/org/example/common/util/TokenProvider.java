package org.example.common.util;

<<<<<<< Updated upstream
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
=======
<<<<<<< Updated upstream
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.common.constant.ApplicationConstant;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
>>>>>>> Stashed changes

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
<<<<<<< Updated upstream
import java.util.*;
import java.util.stream.Collectors;
=======
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
=======
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
>>>>>>> Stashed changes
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
        Claims claims = Jwts.claims().setSubject(email); // user 식별할 값
        claims.put("auth","auth");

=======
<<<<<<< Updated upstream
=======
        Claims claims = Jwts.claims().setSubject(email); // user 식별할 값
        claims.put("auth","auth");
        claims.put("email",email);

>>>>>>> Stashed changes
>>>>>>> Stashed changes

        Date now = new Date();

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

<<<<<<< Updated upstream
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validity))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
=======
<<<<<<< Updated upstream
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");
>>>>>>> Stashed changes


<<<<<<< Updated upstream
    // 토큰에서 회원 정보 추출
    public Authentication getAutnentication (String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
=======
        return builder.compact();
=======
        return Jwts.builder()
                .setSubject("token")
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
            if(!validateTokenExpired(token)){
                return null;
            }
            return email;

        }catch(Exception e){
            e.printStackTrace();
            return  null;
        }
>>>>>>> Stashed changes
    }
>>>>>>> Stashed changes

        if(claims.get("auth")==null){
            throw  new RuntimeException("권한 정보가 없음");
        }

<<<<<<< Updated upstream
        List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);

    }

=======
<<<<<<< Updated upstream
        // 쿠키 확인
        if (!ObjectUtils.isEmpty(request.getCookies())) {
            Cookie cookieToken = Arrays
                    .stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(ApplicationConstant.COOKIE_ACCESS_TOKEN))
                    .findFirst()
                    .orElse(new Cookie(ApplicationConstant.COOKIE_ACCESS_TOKEN, null));

            if (StringUtils.hasText(cookieToken.getValue())){
                return cookieToken.getValue();
            }
=======

    public boolean validateTokenExpired(String token) throws Exception{
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e){
            throw  new Exception("Expired");
        }catch (UnsupportedJwtException e){
            throw  new Exception("UnsupportedJwtException");
        }catch (IllegalArgumentException e){
            throw  new Exception("IllegalArgumentException");
>>>>>>> Stashed changes
        }
>>>>>>> Stashed changes

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
