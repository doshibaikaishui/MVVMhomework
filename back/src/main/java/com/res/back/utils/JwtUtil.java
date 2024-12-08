package com.res.back.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    // Token 有效期 (14 天)
    public static final long JWT_TTL = 60 * 60 * 1000L * 24 * 14;
    // 签名密钥
    public static final String JWT_KEY = "SDFGjhdsfalshdfHFdsjkdsfds121232131afasdfac";

    // 生成随机 UUID
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // 创建默认有效期的 JWT
    public static String createJWT(String subject) {
        return createJWT(subject, null); // 默认使用 JWT_TTL
    }

    // 创建自定义有效期的 JWT
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    // 获取 JWT Builder
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL; // 默认有效期
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject) // 设置 Token 中的主题（用户信息）
                .setIssuer("sg") // 签发者
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 签名算法
                .setExpiration(expDate); // 过期时间
    }

    // 生成密钥
    public static SecretKey generalKey() {
        byte[] encodeKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "HmacSHA256");
    }

    // 解析 JWT
    public static Claims parseJWT(String jwt) {
        try {
            SecretKey secretKey = generalKey();
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (SignatureException e) {
            // 签名异常，可能是 Token 被篡改
            return null;
        } catch (Exception e) {
            // 其他异常处理（包括 Token 过期）
            return null;
        }
    }

    // 验证 JWT 是否有效
    public static boolean isTokenValid(String jwt) {
        try {
            parseJWT(jwt); // 如果能解析成功，则 Token 有效
            return true;
        } catch (Exception e) {
            return false; // 解析失败，Token 无效
        }
    }
}
