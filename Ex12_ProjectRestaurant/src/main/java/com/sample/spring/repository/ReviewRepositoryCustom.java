package com.sample.spring.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.sample.spring.model.ReviewEntity;

public interface ReviewRepositoryCustom {
	// Slice: page를 구현하기 위한 오브젝트
	Slice<ReviewEntity> findSliceByFoodId(Long foodId, Pageable page);
}
