package com.sample.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample.spring.model.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>, ReviewRepositoryCustom {
	
	// QureyDSL대신 JPA Query 어노테이션 사용
	@Query("select avg(r.score) from ReviewEntity r where r.foodId = :foodId")
	Double getAvgScoreByFoodId(@Param("foodId")Long foodId);
	
}
