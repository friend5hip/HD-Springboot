package com.sample.spring.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
	
		Gson gson = new Gson();
		String jsonStr = gson.toJson(Map.of("error", "ERROR_ACCESS_DENIED"));
					
		// 응답의 콘텐츠 타입을 JSON 형식으로 설정 (한글 깨짐 방지)
		response.setContentType("application/json;charset=utf-8");
		response.setStatus(HttpStatus.FORBIDDEN.value());
					
		// 오류 메시지를 클라이언트에게 출력
		PrintWriter printWriter = response.getWriter();
		printWriter.print(jsonStr); // 오류 메시지 출력
		printWriter.close();
		
	}
		
}
