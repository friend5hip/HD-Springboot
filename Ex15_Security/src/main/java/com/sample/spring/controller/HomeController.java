package com.sample.spring.controller;

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
}
