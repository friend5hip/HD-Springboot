package com.sample.spring.domain;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Getter
@ToString(exclude = "memberRoleList")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member2")
public class Member {
	@Id
	private String email;
	
	private String pw;
	private String nickname;
	private boolean social;
	
	@ElementCollection(fetch = FetchType.LAZY)	// 간단한 DB 생성
	@Builder.Default
	private List<MemberRole> memberRoleList = new ArrayList<>();
	
	public void addRole(MemberRole memberRole) {
		memberRoleList.add(memberRole);
	}
	
	public void clearRoles() {
		memberRoleList.clear();
	}

	public void changePw(String pw) {
		this.pw = pw;
	}

	public void changeNickname(String nickname) {
		this.nickname = nickname;
	}

	public void changeSocial(boolean social) {
		this.social = social;
	}
	
	
}
