package com.sample.spring.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sample.spring.security.APILoginFailHandler;
import com.sample.spring.security.APILoginSuccessHandler;
import com.sample.spring.security.filter.JWTCheckFilter;
import com.sample.spring.security.handler.CustomAccessDeniedHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@EnableMethodSecurity	 // 접근 권한 사용 가능
@Configuration 			 // Spring Configuration 클래스임을 명시
@Log4j2
@RequiredArgsConstructor // final이나 @NonNull로 선언된 필드를 포함하는 생성자를 자동으로 생성
public class CustomSecurityConfig {
	
	@Bean // Spring Security의 필터 체인을 정의하는 메서드
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("################################# Security Config #################################");
		
		// CORS 설정
		http.cors(httpSecurityCorsConfigurer -> {
			httpSecurityCorsConfigurer.configurationSource(CorsConfigurationSource());
		});
		
		// CSRF 보호 비활성화 (REST API나 Stateless 방식에서는 보통 사용하지 않음)
		http.csrf(config -> config.disable());
		
		// 세션 관리 설정: 세션을 만들지 않도록 Stateless 정책 사용
		http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		// 로그인 설정: 로그인 페이지 경로와 성공/실패 시 처리 핸들러 지정
		http.formLogin(
				config -> {
					config.loginPage("/api/member/login"); // 커스텀 로그인 페이지 경로
					config.successHandler(new APILoginSuccessHandler()); // 로그인 성공 시 Token 발행 및 응답 처리
					config.failureHandler(new APILoginFailHandler()); // 로그인 실패 시 처리
				}
				);
		
		// JWT 검증 필터를 UsernamePasswordAuthenticationFilter 전에 추가
		http.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);
		
		// 권한에 의한 접근 제어
		http.exceptionHandling(config -> {
			config.accessDeniedHandler(new CustomAccessDeniedHandler());
		});
		
		// 필터 체인 설정 반환
		return http.build();
	}
	
	// 비밀번호 암호화를 위한 BCryptPasswordEncoder 빈 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// CORS 설정을 위한 Bean 정의
	@Bean
	public CorsConfigurationSource CorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // 모든 도메인에서의 접근 허용
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")); // 허용할 HTTP 메서드 설정
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); // 허용할 요청 헤더 설정
        configuration.setAllowCredentials(true); // 자격 증명을 포함한 요청 허용

        // 모든 경로에 대해 설정 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
