package org.example.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class TokenProvider {
    // 암호화 키
    private String secretKey = "joint_payment_secret_key";

    public String createToken(String id) throws Exception {
        // 유효 기간 밀리초 * 초 * 분 * 시 * 일
        int Validity = 1000 * 60 * 60 * 24 * 3;

        // 암호화 알고리즘
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 발급 시간 및 만료 시간
        Date createdTime = new Date();
        Date expireTime = new Date(createdTime.getTime() + Validity);

        // 서명에 담을 데이터
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 헤더에 담을 데이터
        Map<String, Object> headerMap = new HashMap<String, Object>();

        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        // 토큰 빌드
        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setIssuer(id)
                .setExpiration(expireTime)
                .setIssuedAt(createdTime)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }


}
