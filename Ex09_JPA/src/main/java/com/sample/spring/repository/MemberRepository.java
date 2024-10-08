package com.sample.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.spring.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	// CRUD 프레임워크 제공
	Page<Member> findByNameLike(String keyword, Pageable pageable);
}
