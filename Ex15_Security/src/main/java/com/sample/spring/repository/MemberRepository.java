package com.sample.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample.spring.domain.Member;

// MemberRepository는 JPA를 통해 Member 엔티티를 데이터베이스에서 처리하기 위한 레포지토리 인터페이스입니다.
public interface MemberRepository extends JpaRepository<Member, String> {

    // @EntityGraph는 쿼리 수행 시 연관된 엔티티들을 함께 로딩(Fetch Join)하는 방법을 정의합니다.
    // 여기서는 "memberRoleList" 속성(연관 엔티티)을 함께 로딩하도록 설정되어 있습니다.
    @EntityGraph(attributePaths = ("memberRoleList"))
    
    // @Query 어노테이션을 사용해 JPQL로 커스텀 쿼리를 정의
    // "select m from Member m where m.email = :email" : Member 엔티티에서 이메일이 특정 값과 일치하는 회원을 조회하는 쿼리
    @Query("select m from Member m where m.email = :email")
    
    // 이메일을 기반으로 Member 엔티티와 연관된 role 정보를 가져오는 메서드
    // @Param 어노테이션을 사용해 쿼리의 :email 파라미터와 메서드의 email 인수를 연결
    Member getWithRole(@Param("email") String email);
}
