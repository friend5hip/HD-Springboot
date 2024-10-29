package com.sample.spring.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.sample.spring.dto.MemberDTO;
import com.sample.spring.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {
	
	// 허용된 URL만 필터링 대상에서 제외하여 처리한다.
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getRequestURI();  // 요청 URI 추출
		log.info("Checking URL: " + path);
		
		// /api/member/login 경로는 JWT 검증을 하지 않음 (로그인 요청은 필터 적용 안 함)
		if (path.startsWith("/api/member")) {
			return true;
		}
		
//		if (path.startsWith("/test")) { return true; }

		// 위 조건에 해당하지 않으면 필터를 적용 (JWT 토큰 검증)
		return false;	
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			log.info("################## do Filter ##################");

			// HTTP 요청에서 Authorization 헤더 추출
			String authHeaderStr = request.getHeader("Authorization");
			
			// Authorization 헤더에서 'Bearer ' 부분을 제외하고 JWT 토큰 추출
			String accessToken = authHeaderStr.substring(7);
			
			log.info("Authorization Header: " + authHeaderStr); // 헤더 내용 로그로 출력
			
			// JWT 토큰을 검증하고, 클레임을 추출
			Map<String, Object> claims = JWTUtil.validateToken(accessToken);
			
			log.info("JWT Claims: " + claims); // JWT에서 추출한 클레임 내용 로그로 출력
			
			// JWT에서 이메일, 비밀번호, 닉네임, 소셜 로그인 여부, 권한 리스트를 클레임에서 추출
			String email = (String) claims.get("email");
			String pw = (String) claims.get("pw");
			String nickname = (String) claims.get("nickname");
			Boolean social = (boolean) claims.get("social");
			List<String> roleNames = (List<String>) claims.get("roleNames");
			
			// MemberDTO 객체 생성하여 사용자 정보를 담음
			MemberDTO memberDTO = new MemberDTO(email, pw, nickname, social.booleanValue(), roleNames);
			
			log.info("memberDTO: " + memberDTO); // MemberDTO 내용 로그로 출력
			log.info(memberDTO.getAuthorities()); // MemberDTO의 권한 정보 로그로 출력
			
			// 인증 정보를 생성하여 SecurityContext에 설정
			// UsernamePasswordAuthenticationToken을 사용하여 인증 토큰 생성
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(memberDTO, pw, memberDTO.getAuthorities());
			
			// 인증 정보를 SecurityContext에 저장하여 후속 처리가 가능하도록 설정
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
			// 필터 체인에서 다음 필터로 요청을 전달
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			// JWT 검증 중 오류가 발생한 경우 예외 처리
			// 오류 발생 시 JSON 형식으로 오류 메시지를 응답
			Gson gson = new Gson();
			String jsonStr = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
			
			// 응답의 콘텐츠 타입을 JSON 형식으로 설정 (한글 깨짐 방지)
			response.setContentType("application/json;charset=utf-8");	
			
			// 오류 메시지를 클라이언트에게 출력
			PrintWriter printWriter = response.getWriter();
			printWriter.print(jsonStr); // 오류 메시지 출력
			printWriter.close();
		}
		
	}
	
}
