package com.sample.spring.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

// MemberDTO 클래스는 Spring Security의 User 클래스를 상속받아 확장된 사용자 정보를 담는 DTO(Data Transfer Object)입니다.
public class MemberDTO extends User {
	private String email, pw, nickname; // 사용자 이메일, 비밀번호, 닉네임
	private boolean social; // 소셜 로그인 여부를 나타내는 필드
	private List<String> roleNames = new ArrayList<>(); // 사용자의 권한(Role) 목록

	// 생성자: MemberDTO 객체를 생성하며, 부모 클래스(User) 생성자에 필요한 정보도 전달
	public MemberDTO(String email, String pw, String nickname, boolean social, List<String> roleNames) {
		// 부모 클래스(User)의 생성자를 호출하여 email과 권한 정보를 전달
		super(email, pw, roleNames.stream()
			.map(str -> new SimpleGrantedAuthority("ROLE_" + str)) // roleNames 리스트의 각 권한에 "ROLE_" 접두사를 붙여 SimpleGrantedAuthority로 변환
			.collect(Collectors.toList()));
		
		// MemberDTO 객체의 필드 초기화
		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
		this.social = social;
		this.roleNames = roleNames;
	}
	
	// 사용자의 정보를 Map 형식으로 반환하는 메서드
	public Map<String, Object> getClaims() {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("email", email);
		dataMap.put("pw", pw);
		dataMap.put("nickname", nickname);
		dataMap.put("social", social);
		dataMap.put("roleNames", roleNames);
		
		return dataMap;
	}
}
