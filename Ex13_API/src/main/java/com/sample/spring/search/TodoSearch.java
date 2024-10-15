package com.sample.spring.search;

import org.springframework.data.domain.Page;

import com.sample.spring.dto.PageRequestDto;
import com.sample.spring.model.TodoEntity;

public interface TodoSearch {
	
	public Page<TodoEntity> search1(PageRequestDto pageRequestDto);
}
