package com.sample.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")	// USER 권한을 가진 유저만
	@GetMapping("/test")
	public String getTest() {
		return "test";
	}
	
	@GetMapping("/api/test")
	public Map<String, Object> testView() {
		Map<String , Object> response = new HashMap<>();
		response.put("id", "1234");
		return response;
	}
}
