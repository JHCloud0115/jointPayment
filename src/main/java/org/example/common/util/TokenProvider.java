package org.example.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.ibatis.annotations.Param;
import org.example.mapper.member.MemberTokenMapper;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenProvider {
    private final String secretKey = "joint_payment_secret_key";
    private final int accessTokenValidity = 1000 * 60 * 30; // 30분
    private final int refreshTokenValidity = 1000 * 60 * 60 * 24 * 7; // 7일

    private final MemberTokenMapper memberTokenMapper;

    public TokenProvider(MemberTokenMapper memberTokenMapper) {
        this.memberTokenMapper = memberTokenMapper;
    }

    public String createAccessToken(@Param("email") String email) throws Exception {
        return createToken(email);
    }

    public String createRefreshToken(@Param("email") String email) throws Exception {
        return createToken(email);
    }

    // 토큰 생성
    public String createToken(@Param("email") String email) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Claims claims = Jwts.claims().setSubject(email); // user 식별할 값

        Date now = new Date();
        Date validityDate = new Date(now.getTime() + refreshTokenValidity);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        return Jwts.builder()
                .setHeader(headerMap)
                .setClaims(claims)
                .setExpiration(validityDate)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }


    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
