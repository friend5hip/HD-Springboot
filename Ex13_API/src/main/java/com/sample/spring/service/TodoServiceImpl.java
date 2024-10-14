package com.sample.spring.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.spring.dto.TodoDto;
import com.sample.spring.model.TodoEntity;
import com.sample.spring.repository.TodoRepository;

public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public TodoDto get(Long tno) {
		Optional<TodoEntity> result = todoRepository.findById(tno);
		TodoEntity todo = result.orElseThrow();
		return entitytoDto(todo);
	}

	@Override
	public Long post(TodoDto dto) {
		TodoEntity todo = dtotoEntity(dto);
		TodoEntity result  = todoRepository.save(todo);
		
		return result.getTno();
	}

	@Override
	public void update(TodoDto dto) {
		Optional<TodoEntity> result = todoRepository.findById(dto.getTno());
		TodoEntity todo = result.orElseThrow();
		
		todo.changeTitle(dto.getTitle());
		todo.changeComplete(true);
		todo.changeDueDate(LocalDate.now());
		
		todoRepository.save(todo);
	}

	@Override
	public void delete(Long tno) {
		todoRepository.deleteById(tno);
	}
	
}
