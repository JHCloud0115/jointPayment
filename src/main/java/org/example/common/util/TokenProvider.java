package org.example.common.util;

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
    private final String secretKey = "joint_payment_secret_key";
    private final int accessTokenValidity = 1000 * 60 * 30; // 30분
    private final int refreshTokenValidity = 1000 * 60 * 60 * 24 * 7; // 7일

    public String createAccessToken(String id) throws Exception {
        return createToken(id, accessTokenValidity);
    }

    public String createRefreshToken(String id) throws Exception {
        return createToken(id, refreshTokenValidity);
    }

    private String createToken(String id, int validity) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date now = new Date();
        Date validityDate = new Date(now.getTime() + validity);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        return Jwts.builder()
                .setHeader(headerMap)
                .setIssuer(id)
                .setExpiration(validityDate)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
}
