package com.sample.spring.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.google.gson.Gson;
import com.sample.spring.dto.MemberDTO;
import com.sample.spring.util.JWTUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class APILoginSuccessHandler	implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("############################");
		log.info("#######Authentication#######");
		log.info("############################");
		
		MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
		
		// 클레임에 토큰을 넣어준다.
		Map<String, Object> claims = memberDTO.getClaims();
		
		String accessToken = JWTUtil.generateToken(claims, 10);		// 클레임에 10분 동안 유효한 accessToken을 담는다.
		String refreshToken = JWTUtil.generateToken(claims, 60*24);	// 클레임에 1일 동안 유효한 refreshToekn을 담는다.
		
		claims.put("accessToken", accessToken);
		claims.put("refreshToken", refreshToken);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(claims);
		response.setContentType("application/json;charset=utf=8");	// 한글이 깨지는 것을 방지하기 위한 코드다.
		
		// memberDTO를 화면에 출력한다.
		PrintWriter printWriter = response.getWriter();
		printWriter.print(jsonStr);
		printWriter.close();
		
	}
	
}
