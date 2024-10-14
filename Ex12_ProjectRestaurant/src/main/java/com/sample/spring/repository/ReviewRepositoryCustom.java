package com.sample.spring.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.sample.spring.model.ReviewEntity;

public interface ReviewRepositoryCustom {
	// Slice: 페이징를 구현하기 위한 오브젝트(Pageable이 전과 후 Slice가 있는지 
	Slice<ReviewEntity> findSliceByFoodId(Long foodId, Pageable page);
}
