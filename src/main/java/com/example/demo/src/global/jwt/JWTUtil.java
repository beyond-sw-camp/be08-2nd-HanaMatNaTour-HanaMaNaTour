package com.example.demo.src.global.jwt;

import com.example.demo.src.user.model.Role;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

@Component
public class JWTUtil { // jwt 생성, 검증
    private SecretKey secretKey;
    public static final int accessTokenExpireDuration = 6000000; // 액세스 토큰 만료 기간 : 100분
    public static final int refreshTokenExpireDuration = 1209600000;  // 리프레시 토큰 만료 기간 : 14일
    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {

        // 암호화 할 때 어떤 알고리즘 쓸지
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // provideId 반환 메서드
    public String getUserUUId(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userUUId", String.class);
    }

    // role 반환 메서드
    public Role getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", Role.class);
    }

    public String getCategory(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    // 토큰이 소멸 (유효기간 만료) 하였는지 검증 메서드
    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    // 토큰 생성
    public String createJwt(String category, String userUUId, Role role, Long expiredMs) {

        if(Objects.equals(category, "access")){
            return Jwts.builder()
                    .claim("category", category) // jwt 종류 (access, refresh)
                    .claim("userUUId", userUUId)
                    .claim("role", role.name())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + accessTokenExpireDuration))
                    .signWith(secretKey)
                    .compact();
        } else if(Objects.equals(category, "refresh")) {
            return Jwts.builder()
                    .claim("category", category) // jwt 종류 (access, refresh)
                    .claim("userUUId", userUUId)
                    .claim("role", role.name())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + refreshTokenExpireDuration))
                    .signWith(secretKey)
                    .compact();
        }else{
            return null;
        }


    }

    public void addRefreshTokenInCookie(String refreshToken, HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
    }

    public void addAccessTokenInHeader(String accessToken, HttpServletResponse response) {
        response.addHeader("Authorization", "Bearer " + accessToken);
    }
}
