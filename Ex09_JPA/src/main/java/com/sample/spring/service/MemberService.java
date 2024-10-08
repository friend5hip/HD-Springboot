package com.sample.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sample.spring.domain.Member;
import com.sample.spring.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository repo;
	
	public List<Member> selectAll() {
		return repo.findAll();
// 		return repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	// Create
	public Member insert(Member member) {
		Member returnMember = repo.save(member);
		return returnMember;
	}
	
	// Read
	public Optional<Member> view(Long id) {
		// Optional은 데이터가 없을 때 에러를 방지하기 위한 타입이다.
		Optional<Member> member = repo.findById(id);
		return member;
	}
	
	// Delete
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Page<Member> selectNameLike(String search, Pageable pageable) {
		Page<Member> member = repo.findByNameLike(search, pageable);
		return member;
	}
}
