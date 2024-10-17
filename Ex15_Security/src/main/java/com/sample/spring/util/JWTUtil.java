package com.sample.spring.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

// JWTUtil 클래스는 JWT(Json Web Token)를 생성하고 검증하는 기능을 제공합니다.
@Log4j2
public class JWTUtil {

    // 대칭키 방식으로 사용할 시크릿 키(임시값). 실제 서비스에서는 안전한 방법으로 관리해야 함.
    private static String key = "1234567890123456789012345678901234567890";

    // JWT 토큰을 생성하는 메서드
    public static String generateToken(Map<String, Object> valueMap, int min) {

        SecretKey key = null; // HMAC-SHA 키를 저장할 변수

        try {
            // 시크릿 키를 UTF-8로 인코딩하여 HMAC-SHA 알고리즘에 맞는 키로 변환
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));

        } catch (Exception e) {
            // 키 변환 중 예외 발생 시 런타임 예외를 던짐
            throw new RuntimeException(e.getMessage());
        }

        // JWT 토큰 생성
        String jwtStr = Jwts.builder()
                .setHeader(Map.of("typ", "JWT")) // 헤더 설정: typ는 JWT
                .setClaims(valueMap) // 클레임 설정: 사용자가 제공한 valueMap에서 가져옴
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant())) // 발행 시간
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant())) // 만료 시간 (현재 시간 + min)
                .signWith(key) // 시크릿 키를 사용하여 서명
                .compact(); // JWT 문자열로 변환

        return jwtStr; // 생성된 JWT 반환
    }

    // JWT 토큰을 검증하고 클레임 정보를 반환하는 메서드
    public static Map<String, Object> validateToken(String token) {

        Map<String, Object> claim = null; // 클레임 정보를 담을 변수

        try {
            // 시크릿 키를 HMAC-SHA 알고리즘에 맞게 변환
            SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));

            // 토큰을 파싱하고 서명 검증을 수행한 후 클레임 정보 추출
            claim = Jwts.parserBuilder()
                    .setSigningKey(key) // 서명 검증에 사용할 시크릿 키 설정
                    .build()
                    .parseClaimsJws(token) // JWT 파싱 및 서명 검증
                    .getBody(); // 클레임 정보 추출

        } catch (MalformedJwtException malformedJwtException) {
            // JWT의 형식이 잘못된 경우
            throw new CustomJWTException("MalFormed");
        } catch (ExpiredJwtException expiredJwtException) {
            // JWT가 만료된 경우
            throw new CustomJWTException("Expired");
        } catch (InvalidClaimException invalidClaimException) {
            // 클레임이 유효하지 않은 경우
            throw new CustomJWTException("Invalid");
        } catch (JwtException jwtException) {
            // 그 외의 JWT 관련 오류
            throw new CustomJWTException("JWTError");
        } catch (Exception e) {
            // 그 외의 모든 예외 처리
            throw new CustomJWTException("Error");
        }

        return claim; // 검증된 클레임 정보를 반환
    }

}
