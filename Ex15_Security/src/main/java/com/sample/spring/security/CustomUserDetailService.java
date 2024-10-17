package com.sample.spring.security;

import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.spring.domain.Member;
import com.sample.spring.dto.MemberDTO;
import com.sample.spring.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	
	private final MemberRepository memberRepository;	// final 키워드로 @Autowired를 생략 가능하다.
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("------------------- load username ------------------- " + username);
		
		Member member = memberRepository.getWithRole(username); // username = email
		
		if (member == null) {
			throw new UsernameNotFoundException("Username Not Found.");
		}
		
		MemberDTO memberDTO = new MemberDTO(
				member.getEmail(),
				member.getPw(),
				member.getNickname(),
				member.isSocial(),
				member.getMemberRoleList()
					.stream().map(memberRole -> memberRole.name()).collect(Collectors.toList())
				);
		
		log.info(memberDTO);
		
		return memberDTO;
	}
	
}
