package com.sample.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.spring.dto.PageRequestDto;
import com.sample.spring.dto.PageResponseDto;
import com.sample.spring.dto.TodoDto;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoServiceTest {
	
	@Autowired
	TodoService todoService;
	
	@Test
	public void testList() {
		PageRequestDto pageRequestDto = PageRequestDto.builder()
				.page(2)
				.size(10)
				.build();
		
		PageResponseDto<TodoDto> response = todoService.getList(pageRequestDto);
		
		log.info(response);
	}
}
