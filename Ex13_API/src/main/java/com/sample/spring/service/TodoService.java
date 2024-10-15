package com.sample.spring.service;

import com.sample.spring.dto.PageRequestDto;
import com.sample.spring.dto.PageResponseDto;
import com.sample.spring.dto.TodoDto;
import com.sample.spring.model.TodoEntity;

public interface TodoService {
	public TodoDto get(Long tno);
	
	public Long post(TodoDto dto);
	
	public void update(TodoDto dto);
	
	public void delete(Long tno);
	
	public PageResponseDto<TodoDto> getList(PageRequestDto pageRequestDto);
	
	default TodoDto entitytoDto(TodoEntity todo) {
		TodoDto todoDto = TodoDto.builder()
				.tno(todo.getTno())
				.title(todo.getTitle())
				.writer(todo.getWriter())
				.dueDate(todo.getDueDate())
				.complete(todo.isComplete())
				.build();
		return todoDto;
	}
	
	default TodoEntity dtotoEntity(TodoDto todoDto) {
		TodoEntity todoEntity = TodoEntity.builder()
				.tno(todoDto.getTno())
				.title(todoDto.getTitle())
				.writer(todoDto.getWriter())
				.dueDate(todoDto.getDueDate())
				.complete(todoDto.isComplete())
				.build();
		return todoEntity;
	}
}
